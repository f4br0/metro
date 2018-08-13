import loginReducer, { initialState } from '../reducer';
import { fromJS } from '../../../../node_modules/immutable';

describe('loginReducer', () => {
  let state;
  beforeEach(() => {
    state = fromJS({
      admin: false,
      invitado: false,
    });
  });

  it('returns the initial state', () => {
    expect(loginReducer(undefined, {})).toEqual(initialState);
  });

  it('should handle the saveAdmin action correctly', () => {
    const expectedResult = state.set('admin', true);
    expect(loginReducer(state, { type: 'SAVE_ADMIN', value: true })).toEqual(
      expectedResult,
    );
  });
  it('should handle the saveInvitado action correctly', () => {
    const expectedResult = state.set('invitado', true);
    expect(loginReducer(state, { type: 'SAVE_INVITADO', value: true })).toEqual(
      expectedResult,
    );
  });
});
