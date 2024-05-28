import { useState, useEffect } from 'react';
import axios from 'axios';
import Booking from './Booking';
import IRenter from '../../shared/interfaces/renter';
import IBooking from '../../shared/interfaces/booking';

function RenterRequestedListings() {
    const [renter, setRenter] = useState<IRenter | null>(null);

    useEffect(() => {
        const source = axios.CancelToken.source();
    
        // Retrieve the renterId from localStorage
        let renterId = null;
        let renterUsername = null;
        if (localStorage.hasOwnProperty("user")) {
            const stringifiedRenter = localStorage.getItem("user");
            const parsedRenter = stringifiedRenter ? JSON.parse(stringifiedRenter) : null;
            renterId = parsedRenter ? parsedRenter["userId"] : null;
            renterUsername = parsedRenter ? parsedRenter["username"] : null;
        }
    
        // If renterId is null, exit early
        if (!renterId) return;
    
        // Construct the URI with the renterId
        const uri = `http://localhost:8080/renters/${renterId}`;
    
        axios.get(uri, { 
            cancelToken: source.token,
            withCredentials: true,
            headers: {
                'Content-Type': 'application/json',
                'renter': JSON.stringify(renterUsername)
            }
        })
        .then(response => {
            setRenter(response.data);
        })
        .catch(error => {
            if (axios.isCancel(error)) {
                console.log('Request cancelled');
            } else {
                console.error(error);
            }
        });
    
        return () => {
            source.cancel();
        };
    }, [renter]);

    if (renter && renter.bookings.length === 0) return (<div>No bookings found</div>);

    return (
    <div className="renter">
        {renter && renter.bookings.map((b: IBooking) => (
            <Booking
                bookingId={b.bookingId}
                listingId={b.listingId}
                renterId={b.renterId}
                startDate={b.startDate}
                endDate={b.endDate}
                status={b.status}
            />))}
    </div>
    );
}

export default RenterRequestedListings;