import { fromJS } from 'immutable';
import rutaReducer from '../reducer';

describe('rutaReducer', () => {
  it('returns the initial state', () => {
    expect(rutaReducer(undefined, {})).toEqual(fromJS({}));
  });
});
