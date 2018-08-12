import { createSelector } from 'reselect';
import { initialState } from './reducer';

/**
 * Direct selector to the ruta state domain
 */

const selectRutaDomain = state => state.get('ruta', initialState);

/**
 * Other specific selectors
 */

/**
 * Default selector used by Ruta
 */

const makeSelectRuta = () =>
  createSelector(selectRutaDomain, substate => substate.toJS());

export default makeSelectRuta;
export { selectRutaDomain };
