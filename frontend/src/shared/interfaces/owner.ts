import IUser from './user';
import IListing from './listing';

export default interface IOwner extends IUser {
    listings: IListing[];
}