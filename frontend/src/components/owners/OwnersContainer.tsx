import useFetch from '../../data/useFetch';
import IOwner from '../../shared/interfaces/owner';
import Owner from './Owner';

function OwnersContainer() {

  const owners: null | IOwner[] = useFetch("/owners");

  return (
    <>
        {owners && owners.map((owner) => (
            <Owner key={owner.userId} owner={owner} />
        ))}
    </>
  );
}

export default OwnersContainer;