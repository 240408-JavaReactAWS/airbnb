import IBooking from "./booking";

export default interface IListing {
    listingId: number;
    address: string;
    city: string;
    state: string;
    description: string;
    photos: string[]; // photos: Array<string>;
    name: string;
    bookings: IBooking[];
}