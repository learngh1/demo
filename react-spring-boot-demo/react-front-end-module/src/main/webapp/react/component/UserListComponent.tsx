import * as React from 'react'
import * as redux from 'redux'
import { connect } from 'react-redux'

import {UserListItemComponent} from "./UserListItemComponent";
import {
    startLoadUserListAction,
    startLoadUserDetailsAction
} from '../action/actions'

import './UserListComponent.less'
import {AllTypesCombined} from "../model/Types";

type OwnProps = {}

type ConnectedState = {
    userListStore: {data: {id: number, name: string, desc: string}[], isFetching: boolean}
}

type ConnectedDispatch = {
    startLoadUserListActionHandler: () => void
    startLoadUserDetailsActionHandler: (id: number) => void
}

const mapStateToProps = (state: AllTypesCombined, ownProps: OwnProps): ConnectedState => ({
    userListStore: state.userList
})

const mapDispatchToProps = (dispatch: redux.Dispatch<AllTypesCombined>): ConnectedDispatch => ({
    startLoadUserListActionHandler: () => dispatch(startLoadUserListAction(dispatch)),
    startLoadUserDetailsActionHandler: (id: number) => dispatch(startLoadUserDetailsAction(dispatch, id))
})

class UserListComponent extends React.Component<ConnectedState & ConnectedDispatch & OwnProps, {}> {

    componentDidMount() {
        this.props.startLoadUserListActionHandler();
    }

    onSelectUserClickHandler(id: number) {
        this.props.startLoadUserDetailsActionHandler(id);
    }

    render () {
        let _parent = this;
        return (
            <div className="userListContainer">
                {this.props.userListStore.isFetching ?
                    <div>
                        Loading user list...
                    </div>
                    :
                    <div>
                        List of users:
                        <table>
                            <thead>
                                <tr>
                                    <td>user.id</td>
                                    <td>user.name</td>
                                    <td>user.desc</td>
                                </tr>
                            </thead>
                            <tbody>
                            {this.props.userListStore.data.map(function(user){
                                    return <UserListItemComponent
                                        key={user.id}
                                        id={user.id}
                                        name={user.name}
                                        desc={user.desc}
                                        onShowDetailsClickHandler={(id: number) => _parent.onSelectUserClickHandler(id)}
                                    />
                                })}
                            </tbody>
                        </table>
                        <button onClick={(e: React.SyntheticEvent<HTMLButtonElement>) => this.props.startLoadUserListActionHandler()}>reload</button>
                    </div>
                }
            </div>
        )
    }
}

export const UserListComp: React.ComponentClass<OwnProps> = connect(mapStateToProps, mapDispatchToProps)(UserListComponent)