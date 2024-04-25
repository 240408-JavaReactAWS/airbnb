import useFetch from '../../data/useFetch';
import IRenter from '../../shared/interfaces/renter';
import Renter from './Renter';

function RentersContainer() {

  const renters: null | IRenter[] = useFetch("/renters");

  return (
    <>
      {renters && renters.map((renter) => (
          <Renter key={renter.userId} renter={renter} />
      ))}
    </>
  );
}

export default RentersContainer;