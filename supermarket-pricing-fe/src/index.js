import React from 'react';
import ReactDOM from 'react-dom';
import App from './components/App';
import registerServiceWorker from './registerServiceWorker';
import {Provider} from 'react-redux'
import {applyMiddleware, createStore} from 'redux'
import purchaseReducer from './reducers/purchaseReducer'
import thunk from "redux-thunk";
import {composeWithDevTools} from 'redux-devtools-extension';
import promise from "redux-promise-middleware"


const store = createStore(purchaseReducer, {
    cart: [],
    pricing: new Map(),
    products: {}
}, composeWithDevTools(applyMiddleware(thunk, promise())));

ReactDOM.render(
    <Provider store={store}>
        <App/>
    </Provider>,
    document.getElementById('root')
);
registerServiceWorker();
