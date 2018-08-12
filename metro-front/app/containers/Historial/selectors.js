import { createSelector } from 'reselect';
import { initialState } from './reducer';

/**
 * Direct selector to the historial state domain
 */

const selectHistorialDomain = state => state.get('historial', initialState);

/**
 * Other specific selectors
 */

/**
 * Default selector used by Historial
 */

const makeSelectHistorial = () =>
  createSelector(selectHistorialDomain, substate => substate.toJS());

export default makeSelectHistorial;
export { selectHistorialDomain };
