/**
 * Test the HomePage
 */

import React from 'react';
import { shallow } from 'enzyme';

import { Login, mapDispatchToProps } from '../index';
import { LoginForm } from '../form';

describe('<Login />', () => {
  it('should render the Login', () => {
    const renderedComponent = shallow(<Login />);
    expect(renderedComponent.contains(<LoginForm />)).toEqual(true);
  });

  describe('mapDispatchToProps', () => {
    describe('login', () => {
      it('should be injected', () => {
        const dispatch = jest.fn();
        const result = mapDispatchToProps(dispatch);
        expect(result.login).toBeDefined();
      });
    });
  });

  it('should dispatch changeUsername when called', () => {
    const dispatch = jest.fn();
    const result = mapDispatchToProps(dispatch);
    result.login({});
    expect(dispatch).toHaveBeenCalledWith({ type: 'LOGIN', body: {} });
  });
});
