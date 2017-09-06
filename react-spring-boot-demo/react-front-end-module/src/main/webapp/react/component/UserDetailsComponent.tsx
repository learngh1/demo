import * as React from 'react'

import { connect } from 'react-redux'
import './UserDetailsComponent.less';
import {AllTypesCombined, User} from "../model/Types";

type OwnProps = {}

type ConnectedState = {
    userDetailsStore: {selectedUserId: number, user: User, isFetching: boolean}
}

const mapStateToProps = (state: AllTypesCombined, ownProps: OwnProps): ConnectedState => ({
    userDetailsStore: state.userDetails
})

export const UserDetailsComp = connect(mapStateToProps, {})((props: ConnectedState & OwnProps) => {
    let {isFetching, selectedUserId, user} = props.userDetailsStore;
    return (
        <div className="userDetailsContainer">
            {isFetching ?
                <div>
                    Loading user details for id={selectedUserId}...
                </div>
                :
                <div>
                    {user != null ?
                        <div>
                            loaded user=id={user.id}, name={user.name}
                        </div>
                        :
                        <div>
                            select user to view details
                        </div>
                    }
                </div>
            }
        </div>
    )
})