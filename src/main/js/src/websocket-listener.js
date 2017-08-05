'use strict';
var SockJS = require('sockjs-client');
var Stomp = require('stompjs');
import { addUpdate } from './actions/WebSocketAction';

var sock = new SockJS('http://localhost:8080/updates/');

const registrations = [
    "financeRequest",
    "newsRequest",
    "tflTubeStatusRequest",
    "tflTrainStatusRequest",
    "twitterRequest",
    "weatherRequest"];

sock.onopen = () => {
    console.log('opened websocket connection');
    register();
    console.log("adding to redux");
    addUpdate("ass");
};
sock.onmessage = (e) => {
    console.log('Message received!!!!');
    addUpdate(e.data);
    addUpdate("ass");
};
sock.onclose = () => {
    console.log('close');
};

function register() {
    var socket = SockJS('updates');
    var stompClient = Stomp.over(socket);
    stompClient.connect({}, function () {
        registrations.forEach(function (registration) {
            stompClient.subscribe(`/topic/${registration}`);
        });
    });
}

module.exports.register = register;