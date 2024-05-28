import IBooking from "../../shared/interfaces/booking";

function Booking(props: IBooking) {
  return (
    <div className="booking" key={props.bookingId}>
      <h2>{props.startDate} - {props.endDate}</h2>
      <h3>Status: {props.status}</h3>
    </div>
  );
}

export default Booking;