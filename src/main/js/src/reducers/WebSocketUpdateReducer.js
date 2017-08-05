import { List, Map } from 'immutable';
import { ADD_UPDATE } from "../actions/WebSocketAction";
const intitialState = Map();

export default function reducer(state = intitialState, action) {
    switch (action.type) {
        case ADD_UPDATE:
            return state.setIn(action.payload.keySeq(), action.payload.valueSeq());
        default:
            return state
    }
}