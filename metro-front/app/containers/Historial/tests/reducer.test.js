import { fromJS } from 'immutable';
import historialReducer from '../reducer';

describe('historialReducer', () => {
  it('returns the initial state', () => {
    expect(historialReducer(undefined, {})).toEqual(fromJS({}));
  });
});
