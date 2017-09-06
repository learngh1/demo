export type UserDetailsStore = {selectedUserId: number; user: User; isFetching: boolean}

export type User = {
    id: number;
    name: string;
    desc: string;
}

export type UserListStore = {data: User[]; isFetching: boolean}

export type AllTypesCombined = {
    userList: UserListStore,
    userDetails: UserDetailsStore
}