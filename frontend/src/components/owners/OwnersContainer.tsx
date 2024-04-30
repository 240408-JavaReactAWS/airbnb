import useFetch from '../../data/useFetch';
import IOwner from '../../shared/interfaces/owner';
import Owner from './Owner';
import './OwnersContainer.css';

function OwnersContainer() {

  const owners: null | IOwner[] = useFetch("/owners");

  return (
    <div className="owners">
        {owners && owners.map((owner) => (
            <Owner key={owner.userId} owner={owner} />
        ))}
    </div>
  );
}

export default OwnersContainer;