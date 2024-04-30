import IOwner from '../../shared/interfaces/owner';

interface IOwnerProps {
    owner: IOwner,
    key: number
}

function Owner(props: IOwnerProps) {
  return (
    <div key={props.owner.userId}>
        <h2>{props.owner.username}</h2>
        <p>{props.owner.email}</p>
        {props.owner.listings.map((listing) => (
            <div key={listing.listingId}>
                <h4>Name: {listing.name}</h4>
                <p>Address: {listing.address}</p>
                <p>City: {listing.city}</p>
                <p>State: {listing.state}</p>
                <p>Description: {listing.description}</p>
                <p>Bookings: {listing.bookings?.map((booking) =>(
                    <div key={booking.bookingId}>
                        <p>Start Date: {booking.startDate}</p>
                        <p>End Date: {booking.endDate}</p>
                    </div>
                ))}</p>
                <p>Photos: {listing.photos}</p>
            </div>
        ))}
    </div>
  );
}

export default Owner;