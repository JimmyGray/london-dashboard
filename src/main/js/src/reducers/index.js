import { combineReducers } from 'redux'
import updates from './WebSocketUpdateReducer'

const todoApp = combineReducers({ updates })

export default todoApp