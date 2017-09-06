import {API} from "./API";

import {User} from "../model/Types";

class APIStubImpl implements API {
    fetchUserDetails(id: number, callback: (user: User) => void): void {
        setTimeout(() => {
            let user = {id: id, name: 'name_' + id, desc: 'desc_' + id}
            callback(user);
        }, 1000);
    }

    fetchUserList(callback: (data: User[]) => void): void {
        setTimeout(() => {
            let data = [
                {id: 1, name: 'name_1', desc: 'desc_1'},
                {id: 2, name: 'name_2', desc: 'desc_2'}
            ]
            callback(data);
        }, 1000);
    }
}

export const STUB_API = new APIStubImpl()