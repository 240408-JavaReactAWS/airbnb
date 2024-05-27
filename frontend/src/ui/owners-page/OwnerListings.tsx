import { useState, useEffect } from 'react';
import NewListingForm from './NewListingForm';
import axios from 'axios';
import Listing from '../../components/listings/listing/Listing';
import IOwner from '../../shared/interfaces/owner';
import IListing from '../../shared/interfaces/listing';

function OwnerListings() {
    const [owner, setOwner] = useState<IOwner | null>(null);

    useEffect(() => {
        const source = axios.CancelToken.source();
    
        // Retrieve the ownerId from localStorage
        let ownerId = null;
        let ownerUsername = null;
        if (localStorage.hasOwnProperty("user")) {
            const stringifiedOwner = localStorage.getItem("user");
            const parsedOwner = stringifiedOwner ? JSON.parse(stringifiedOwner) : null;
            ownerId = parsedOwner ? parsedOwner["userId"] : null;
            ownerUsername = parsedOwner ? parsedOwner["username"] : null;
        }
    
        // If ownerId is null, exit early
        if (!ownerId) return;
    
        // Construct the URI with the ownerId
        const uri = `http://localhost:8080/owners/${ownerId}`;
    
        axios.get(uri, { 
            cancelToken: source.token,
            withCredentials: true,
            headers: {
                'Content-Type': 'application/json',
                'owner': JSON.stringify(ownerUsername)
            }
        })
        .then(response => {
            setOwner(response.data);
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
    }, [owner]);

    return (
    <div className="owner">
        <h2>Add New Listing</h2>
        <NewListingForm />
        {owner && owner.listings.map((l: IListing) => <Listing key={l.listingId} listing={l} />)}
    </div>
    );
}

export default OwnerListings;