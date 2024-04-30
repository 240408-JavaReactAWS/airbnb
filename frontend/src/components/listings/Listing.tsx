import IListing from '../../shared/interfaces/listing';

interface IListingProps {
    listing: IListing,
    key: number
}

function Listing(props: IListingProps) {
  return (
    <div key={props.listing.listingId}>
        <h2>Name: {props.listing.name}</h2>
        <p>Address: {props.listing.address}</p>
        <p>Address: {props.listing.description}</p>
    </div>
  );
}

export default Listing;