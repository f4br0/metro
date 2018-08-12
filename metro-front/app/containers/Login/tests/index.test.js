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
  // it('should dispatch changeUsername when called', () => {
  //   const dispatch = jest.fn();
  //   const result = mapDispatchToProps(dispatch);
  //   const username = 'mxstbr';
  //   result.onChangeUsername({ target: { value: username } });
  //   expect(dispatch).toHaveBeenCalledWith(changeUsername(username));

  //   describe('onSubmitForm', () => {
  //     it('should be injected', () => {
  //       const dispatch = jest.fn();
  //       const result = mapDispatchToProps(dispatch);
  //       expect(result.onSubmitForm).toBeDefined();
  //     });

  //     it('should dispatch loadRepos when called', () => {
  //       const dispatch = jest.fn();
  //       const result = mapDispatchToProps(dispatch);
  //       result.onSubmitForm();
  //       expect(dispatch).toHaveBeenCalledWith(loadRepos());
  //     });

  //     it('should preventDefault if called with event', () => {
  //       const preventDefault = jest.fn();
  //       const result = mapDispatchToProps(() => { });
  //       const evt = { preventDefault };
  //       result.onSubmitForm(evt);
  //       expect(preventDefault).toHaveBeenCalledWith();
  //     });
  //   });
  // });
});
