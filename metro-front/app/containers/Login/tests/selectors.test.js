import { fromJS } from 'immutable';

import makeSelectLogin from '../selectors';

describe('login state', () => {
  const loginSelector = makeSelectLogin();
  it('should select login state', () => {
    const loginState = {
      admin: false,
      invitado: false,
    };
    const mockedState = fromJS({
      login: loginState,
    });
    expect(loginSelector(mockedState)).toEqual(loginState);
  });
});
