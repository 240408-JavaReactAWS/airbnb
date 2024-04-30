import IListing from '../../../shared/interfaces/listing';
import './Listing.css';
import ListingsPhotos from './photos/ListingPhotos';

interface IListingProps {
  listing: IListing
}

function Listing(props: IListingProps) {
  return (
    <div className="listing" key={props.listing.listingId}>
      <h2>{props.listing.name}</h2>
      <h3>{props.listing.address}</h3>
      <p>{props.listing.description}</p>
      <ListingsPhotos listingId={props.listing.listingId} photos={props.listing.photos} alt={props.listing.name} />
    </div>
  );
}

export default Listing;