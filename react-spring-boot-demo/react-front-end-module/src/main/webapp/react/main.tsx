import * as React from "react";
import * as ReactDOM from "react-dom";

import { createStore, applyMiddleware, compose} from 'redux'
import { Provider } from 'react-redux'
import thunk from 'redux-thunk'
import { reducers} from './reducer/reducers'

import { UserListComp } from "./component/UserListComponent";
import {UserDetailsComp} from "./component/UserDetailsComponent";
import {AllTypesCombined} from "./model/Types";

let devtools: any = (window as any)['devToolsExtension'] ? (window as any)['devToolsExtension']() : (f:any)=>f;
let store = applyMiddleware(thunk)(devtools(createStore))(reducers, {} as AllTypesCombined);

(window as any).renderTestComp = (node: any, param: any, param2: any) => {
    ReactDOM.render(
        <div>
            NODE_ENV={process.env.NODE_ENV}
            <Provider store={store}>
                <span>
                    <UserListComp />
                    <br/>
                    <UserDetailsComp />
                </span>
            </Provider>
        </div>, node
    );
}
