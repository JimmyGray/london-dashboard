/*
 * action types
 */

export const ADD_UPDATE = 'ADD_UPDATE'

export const addUpdate = (incomingUpdate) => {
    return {
        type: ADD_UPDATE,
        payload: incomingUpdate
    }
}