/**
 *
 * Login
 *
 */

import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { createStructuredSelector } from 'reselect';
import { compose } from 'redux';

import injectSaga from 'utils/injectSaga';
import injectReducer from 'utils/injectReducer';
import makeSelectLogin from './selectors';
import reducer from './reducer';
import saga from './saga';

import LoginForm from './form';

/* eslint-disable react/prefer-stateless-function */
export class Login extends React.Component {
  submit = values => {
    this.props.login(values.toJS());
  };

  render() {
    return (
      <div>
        <h1>Sistema metro</h1>
        <LoginForm onSubmit={this.submit} />
      </div>
    );
  }
}

Login.propTypes = {
  login: PropTypes.func,
};

const mapStateToProps = createStructuredSelector({
  login: makeSelectLogin(),
});

export function mapDispatchToProps(dispatch) {
  return {
    login: body => dispatch({ type: 'LOGIN', body }),
  };
}

const withConnect = connect(
  mapStateToProps,
  mapDispatchToProps,
);

const withReducer = injectReducer({ key: 'login', reducer });
const withSaga = injectSaga({ key: 'login', saga });

export default compose(
  withReducer,
  withSaga,
  withConnect,
)(Login);
