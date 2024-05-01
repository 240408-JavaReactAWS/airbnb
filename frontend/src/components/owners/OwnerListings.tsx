import IOwner from '../../shared/interfaces/owner';

// interface IOwnerProps {
//     owner: IOwner
// }

function OwnerListings() {
    const owner = localStorage.getItem("user");
    console.log(owner)
    return (
    <div className="owner">
        <h2>Owner Listings</h2>
        {/* <h2>{props.owner.username}</h2>
        <p>{props.owner.email}</p>
        {props.owner.listings.map((listing) => (
            <div key={listing.listingId}>
                <h4>Name: {listing.name}</h4>
                <p>Address: {listing.address}</p>
                <p>City: {listing.city}</p>
                <p>State: {listing.state}</p>
                <p>Description: {listing.description}</p>
                {listing.bookings.length > 0 && <p>Bookings: {listing.bookings?.map((booking) =>(
                    <div key={booking.bookingId}>
                        <p>Start Date: {booking.startDate}</p>
                        <p>End Date: {booking.endDate}</p>
                    </div>
                ))}</p>}
                {listing.photos.length > 0 && <p>Photos: {listing.photos}</p>}
            </div>
        ))} */}
    </div>
    );
}

export default OwnerListings;