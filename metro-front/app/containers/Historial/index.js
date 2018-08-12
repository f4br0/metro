/**
 *
 * Historial
 *
 */

import React from 'react';
import PropTypes from 'prop-types';
import { connect } from 'react-redux';
import { createStructuredSelector } from 'reselect';
import { compose } from 'redux';
import { push } from 'react-router-redux';

import injectSaga from 'utils/injectSaga';
import injectReducer from 'utils/injectReducer';
import makeSelectHistorial from './selectors';
import reducer from './reducer';
import saga from './saga';
import makeSelectLogin from '../Login/selectors';

/* eslint-disable react/prefer-stateless-function */
export class Historial extends React.Component {
  componentDidMount() {
    if (this.props.login.admin) {
      this.props.init();
    } else {
      this.props.salir();
    }
  }

  render() {
    const result = [];
    this.props.historial.consultas.map(r =>
      result.push(<div>{JSON.stringify(r)} </div>),
    );
    return (
      <div>
        <button onClick={this.props.regresar}>Ir ruta</button>
        <button onClick={this.props.salir}>Salir</button>
        <br />
        <h1>Historial metro</h1>
        {result}
      </div>
    );
  }
}

Historial.propTypes = {
  historial: PropTypes.object,
  login: PropTypes.object,
  init: PropTypes.func,
};

const mapStateToProps = createStructuredSelector({
  historial: makeSelectHistorial(),
  login: makeSelectLogin(),
});

function mapDispatchToProps(dispatch) {
  return {
    init: () => dispatch({ type: 'HISTORIAL' }),
    salir: () => dispatch(push('/')),
    regresar: () => dispatch(push('/ruta')),
  };
}

const withConnect = connect(
  mapStateToProps,
  mapDispatchToProps,
);

const withReducer = injectReducer({ key: 'historial', reducer });
const withSaga = injectSaga({ key: 'historial', saga });

export default compose(
  withReducer,
  withSaga,
  withConnect,
)(Historial);
