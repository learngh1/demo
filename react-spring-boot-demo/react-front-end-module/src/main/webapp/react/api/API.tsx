import {User} from "../model/Types";

export interface API {
    fetchUserDetails(id: number, callback: (user: User) => void): void
    fetchUserList(callback: (data: User[]) => void): void
}