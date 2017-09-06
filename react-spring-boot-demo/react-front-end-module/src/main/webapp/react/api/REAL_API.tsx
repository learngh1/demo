import {API} from "./API";
import {User} from "../model/Types";
import * as fetch from 'isomorphic-fetch'

class APIImpl implements API {
    fetchUserDetails(id: number, callback: (user: User) => void): void {
        fetch('http://localhost:8080/get_user_details?id=' + id) //TODO: move address to constants
            .then(res => res.json())
            .then(res => { callback(res) })
    }

    fetchUserList(callback: (data: User[]) => void): void {
        fetch('http://localhost:8080/get_user_list') //TODO: move address to constants
            .then(res => res.json())
            .then(res => { callback(res) })
    }
}

export const REAL_API = new APIImpl()