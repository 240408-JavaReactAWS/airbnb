export default interface IBooking {
    bookingId: number;
    listingId: number;
    renterId: number;
    startDate: string;
    endDate: string;
    status: string;
}