import useFetch from '../../data/useFetch';
import IListing from '../../shared/interfaces/listing';
import Listing from './listing/Listing';
import './ListingsContainer.css';

function ListingsContainer() {

  const listings: null | IListing[] = useFetch("/listings");

  return (
    <div className="listings">
      {listings && listings.map((listing) => (
        <Listing key={listing.listingId} listing={listing} />
      ))}
    </div>
  );
}

export default ListingsContainer;