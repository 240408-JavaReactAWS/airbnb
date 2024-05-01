import { useEffect, useState } from 'react';
import IListing from '../../shared/interfaces/listing';
import './Listing.css';
import ListingsPhotos from './ListingPhotos';
import axios from 'axios';

interface IListingProps {
  listing: IListing
}

interface BookingType {
  bookingId: number;
  listingId: number;
  renterId: number;
  startDate: string;
  endDate: string;
  status: string;
}

interface Renter {
  userId: number;
  username: string;
  email: string;
  bookings: BookingType[];
}

function Listing(props: IListingProps) {
  const [renter, setRenter] = useState<Renter | null>(null);

  const handleClick = () => {
    console.log(`Requesting ${props.listing.name}`);

    // Retrieve the renterId from localStorage
    let renterId = null;
    let renterUsername = null;
    if (localStorage.hasOwnProperty("user")) {
        const stringifiedOwner = localStorage.getItem("user");
        const parsedRenter = stringifiedOwner ? JSON.parse(stringifiedOwner) : null;
        renterId = parsedRenter ? parsedRenter["userId"] : null;
        renterUsername = parsedRenter ? parsedRenter["username"] : null;
    }

    // If renterId is null, exit early
    if (!renterId) return;

    axios.post("http://localhost:8080/bookings", {
      startDate: "04/25/24",
      endDate: "05/1/24",
      status: "pending",
      listingId: props.listing.listingId,
      renterId: 1
  }, { 
      withCredentials: true,
      headers: {
          'Content-Type': 'application/json',
          'renter': JSON.stringify(renterUsername)
      }
  })
  .then(response => {
    console.log("Successfully created Booking as Renter")
    console.log(response.data);
      setRenter(response.data);
  })
  .catch(error => {
      console.error(error);
  });
  }

  return (
    <div className="listing" key={props.listing.listingId}>
      <h2>{props.listing.name}</h2>
      <h3>{props.listing.address}</h3>
      <p>{props.listing.description}</p>
      <button onClick={handleClick}>Request</button><br/><br/>
      <ListingsPhotos listingId={props.listing.listingId} photos={props.listing.photos} alt={props.listing.name} />
    </div>
  );
}

export default Listing;