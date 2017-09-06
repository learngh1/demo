import {UserType} from "../model/Types";

export interface API {
    fetchUserDetails(id: number, callback: (user: UserType) => void): void
    fetchUserList(callback: (data: UserType[]) => void): void
}