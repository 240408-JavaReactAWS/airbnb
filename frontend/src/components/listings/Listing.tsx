import IListing from '../../shared/interfaces/listing';
import './Listing.css';

interface IListingProps {
    listing: IListing,
    key: number
}

function Listing(props: IListingProps) {
  return (
    <div className="listing" key={props.listing.listingId}>
      <h2>{props.listing.name}</h2>
      <h3>{props.listing.address}</h3>
      <p>{props.listing.description}</p>
      <div className="listing-photos">
        {props.listing.photos.length > 0 && props.listing.photos.map((photo) => (
          <img src={photo} alt={props.listing.name} />
        ))}
      </div>
    </div>
  );
}

export default Listing;