import useFetch from '../../data/useFetch';
import IListing from '../../shared/interfaces/listing';
import Listing from './Listing';

function ListingsContainer() {

  const listings: null | IListing[] = useFetch("/listings");

  return (
    <>
        {listings && listings.map((listing) => (
            <Listing key={listing.listingId} listing={listing} />
        ))}
    </>
  );
}

export default ListingsContainer;