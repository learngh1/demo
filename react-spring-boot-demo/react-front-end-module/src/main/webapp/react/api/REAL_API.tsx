import {API} from "./API";
import {User} from "../model/Types";

class APIImpl implements API {
    fetchUserDetails(id: number, callback: (user: User) => void): void {
        throw new Error('Not implemented');//TODO: implement
    }

    fetchUserList(callback: (data: User[]) => void): void {
        throw new Error('Not implemented');//TODO: implement
    }
}

export const REAL_API = new APIImpl()