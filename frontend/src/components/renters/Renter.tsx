import IRenter from '../../shared/interfaces/renter';

interface IRenterProps {
  renter: IRenter,
  key: number
}

function Renter(props: IRenterProps) {
  return (
    <div key={props.renter.userId}>
      <h2>Username: {props.renter.username}</h2>
      <h2>Email: {props.renter.email}</h2>
      {props.renter.bookings.length > 0 ? <h2>Bookings:</h2> : <h2>No bookings</h2>}
      <p>
        {props.renter.bookings.map(({bookingId, startDate, endDate}) => (
          <div key={bookingId}>
            <p>{startDate}</p>
            <p>{endDate}</p>
          </div>
        ))}
      </p>
    </div>
  );
}

export default Renter;