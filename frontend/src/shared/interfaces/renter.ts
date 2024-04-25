import IUser from './user';
import IBooking from './booking';

export default interface IRenter extends IUser {
    bookings: IBooking[];
}