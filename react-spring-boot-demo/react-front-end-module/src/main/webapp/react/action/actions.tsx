import {User} from "../model/Types";

//TODO: use constants instead of strings as action.type
export type Action =
        | {type: 'START_LOAD_USER_LIST_ACTION', dispatch: any}
        | {type: 'FINISHED_LOAD_USER_LIST_ACTION', data: User[]}
        | {type: 'START_LOAD_USER_DETAILS_ACTION', dispatch: any, selectedUserId: number}
        | {type: 'FINISHED_LOAD_USER_DETAILS_ACTION', user: User}


export const startLoadUserListAction = (dispatch: any): Action => ({type: 'START_LOAD_USER_LIST_ACTION', dispatch: dispatch})
export const finishedLoadUserListAction = (data: User[]): Action => ({type: 'FINISHED_LOAD_USER_LIST_ACTION', data: data})

export const startLoadUserDetailsAction = (dispatch: any, id: number): Action => ({type: 'START_LOAD_USER_DETAILS_ACTION', dispatch: dispatch, selectedUserId: id})
export const finishedLoadUserDetailsAction = (user: User): Action => ({type: 'FINISHED_LOAD_USER_DETAILS_ACTION', user: user})