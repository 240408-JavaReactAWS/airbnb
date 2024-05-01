interface BookingType {
  bookingId: number;
  listingId: number;
  renterId: number;
  startDate: string;
  endDate: string;
  status: string;
}

function Booking(props: BookingType) {
  return (
    <div className="booking" key={props.bookingId}>
      <h2>{props.startDate} - {props.endDate}</h2>
      <h3>Status: {props.status}</h3>
    </div>
  );
}

export default Booking;