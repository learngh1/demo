import {API} from "./API";

import * as fetch from 'isomorphic-fetch'
import {User} from "../model/Types";

class APIStubImpl implements API {
    fetchUserDetails(id: number, callback: (user: User) => void): void {
        console.log('apiSTUB: fetchUD id=' + id);

        fetch('http://swapi.co/api/people/1/')
            .then(res => res.json())
            .then(res => {
                setTimeout(() => {
                    let user = {id: id, name: 'name_' + id, desc: 'desc_' + id}
                    callback(user);
                }, 1000);
            })
    }

    fetchUserList(callback: (data: User[]) => void): void {
        console.log('apiSTUB: fetchUserList');

        fetch('http://swapi.co/api/people/1/')
            .then(res => res.json())
            .then(res => {
                console.log('finished fetching')
                setTimeout(() => {}, 1000);

                let data = [
                    {id: 1, name: 'name_1', desc: 'desc_1'},
                    {id: 2, name: 'name_2', desc: 'desc_2'}
                ]

                callback(data);
            });
    }
}

export const STUB_API = new APIStubImpl()