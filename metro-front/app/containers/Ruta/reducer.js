/*
 *
 * Ruta reducer
 *
 */

import { fromJS } from 'immutable';

export const initialState = fromJS({
  detalle: [],
});

function rutaReducer(state = initialState, action) {
  switch (action.type) {
    case 'SAVE_DETALLE':
      return state.set('detalle', action.value);
    default:
      return state;
  }
}

export default rutaReducer;
