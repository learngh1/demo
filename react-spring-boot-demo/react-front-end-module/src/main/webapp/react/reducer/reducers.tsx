import { combineReducers } from 'redux'

import {Action, finishedLoadUserListAction, finishedLoadUserDetailsAction} from '../action/actions'
import {STUB_API} from "../api/STUB_API";
import {REAL_API} from "../api/REAL_API";
import {API} from "../api/API";
import {UserDetailsStore, User, UserListStore, AllTypesCombined} from "../model/Types";


let api : API = REAL_API;

if (process.env.NODE_ENV == 'dev') {
    api = STUB_API;
}

//TODO: move each reducer to separate files
function userDetailsReducer(state: UserDetailsStore = {selectedUserId: null, user: null, isFetching: false}, action: Action) : UserDetailsStore {
    switch (action.type) {
        case 'START_LOAD_USER_DETAILS_ACTION':

            let selectedUserId = action.selectedUserId;
            api.fetchUserDetails(selectedUserId, (user: User) => action.dispatch(finishedLoadUserDetailsAction(user)))

            return  {...state, selectedUserId: selectedUserId, isFetching: true}
        case 'FINISHED_LOAD_USER_DETAILS_ACTION':
            //TODO: isF =true & send req to server , then finished
            return {...state, user: action.user, isFetching: false}
        default:
            return state
    }
}

function userListReducer(state: UserListStore = {data: [], isFetching: false}, action: Action) : UserListStore {
    switch (action.type) {
        case 'FINISHED_LOAD_USER_LIST_ACTION':
            return {data: action.data, isFetching: false}
        case 'START_LOAD_USER_LIST_ACTION':
            api.fetchUserList((data: User[]) => action.dispatch(finishedLoadUserListAction(data)))
            return {data: state.data, isFetching: true}
        default:
            return state
    }
}

export const reducers = combineReducers<AllTypesCombined>({
    userList: userListReducer,
    userDetails: userDetailsReducer
})

export default reducers