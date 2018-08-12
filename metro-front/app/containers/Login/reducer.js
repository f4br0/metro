/*
 *
 * Login reducer
 *
 */

import { fromJS } from 'immutable';

export const initialState = fromJS({
  admin: false,
  invitado: false,
});

function loginReducer(state = initialState, action) {
  switch (action.type) {
    case 'SAVE_ADMIN':
      return state.set('admin', action.value);
    case 'SAVE_INVITADO':
      return state.set('invitado', action.value);
    default:
      return state;
  }
}

export default loginReducer;
