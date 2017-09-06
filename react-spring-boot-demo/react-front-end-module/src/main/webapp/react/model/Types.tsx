export type UserDetailsType = {selectedUserId: number; user: UserType; isFetching: boolean}

export type UserType = {
    id: number;
    name: string;
    desc: string;
}

export type UserListStore = {data: UserType[]; isFetching: boolean}

export type AllTypesCombined = {
    userList: UserListStore,
    userDetails: UserDetailsType
}