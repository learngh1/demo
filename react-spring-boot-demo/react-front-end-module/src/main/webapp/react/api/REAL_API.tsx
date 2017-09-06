import {API} from "./API";
import {UserType} from "../model/Types";

class APIImpl implements API {
    fetchUserDetails(id: number, callback: (user: UserType) => void): void {
        throw new Error('Not implemented');//TODO: implement
    }

    fetchUserList(callback: (data: UserType[]) => void): void {
        throw new Error('Not implemented');//TODO: implement
    }
}

export const REAL_API = new APIImpl()