import IBooking from "./booking";

export default interface IListing {
    listingId: number;
    address: string;
    city: string;
    state: string;
    description: string;
    photos: String[];
    name: string;
    bookings: IBooking[];
}