import React from 'react';
import { addUpdate } from "./actions/WebSocketAction";
import ReactDOM from 'react-dom';
import { createStore } from 'redux'
import { Provider } from 'react-redux'
import reducer from './reducers/WebSocketUpdateReducer'
import App from './components/App.jsx'
import _ from 'lodash'
import { Map } from 'immutable';
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';

var SockJS = require('sockjs-client');
var Stomp = require('stompjs');
var sock = new SockJS('http://localhost:8080/updates/');

const registrations = [
    "financeRequest",
    "newsRequest",
    "tflTubeStatusRequest",
    "tflTrainStatusRequest",
    "twitterRequest",
    "weatherRequest"];

function register() {
    var socket = SockJS('/updates');
    var stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        registrations.forEach(function (registration) {
            stompClient.subscribe(`/topic/${registration}`, onmessage);
        });
    });
}


const onmessage = function(message) {
    const url = message.headers.destination.replace(/\/topic\//g,'');
    const updatedMap = Map();
    const updated = updatedMap.set(url, JSON.parse(message.body));
    store.dispatch(addUpdate(updated));
}

sock.onopen = () => {
    fetch('http://localhost:8080/updates/')
        .then(
            function(response) {
                if (response.status !== 200) {
                    console.log('Looks like there was a problem. Status Code: ' +
                        response.status);
                    return;
                }
                response.json().then(function(data) {
                    _.forIn(data, (value, key) => {
                        const updatedMap = Map();
                        const updated = updatedMap.set(key, value);
                        store.dispatch(addUpdate(updated));
                    })
                });
            }
        )
        .catch(function(err) {
            console.log('Fetch Error :-S', err);
        });
};

register();
const store = createStore(reducer, window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__());
ReactDOM.render(
    <Provider store={store}>
        <MuiThemeProvider>
            <App />
        </MuiThemeProvider>
    </Provider>,
    document.getElementById('content')
);