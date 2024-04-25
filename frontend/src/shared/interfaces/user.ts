export default interface IUser {
    userId: number;
    username: string;
    email: string;
    password: string;
    token: null | string;
}