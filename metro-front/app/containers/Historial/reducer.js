/*
 *
 * Historial reducer
 *
 */

import { fromJS } from 'immutable';

export const initialState = fromJS({
  consultas: [],
});

function historialReducer(state = initialState, action) {
  switch (action.type) {
    case 'SAVE_HISTORIAL':
      return state.set('consultas', action.value);
    default:
      return state;
  }
}

export default historialReducer;
