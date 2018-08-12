/**
 *
 * Ruta
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
import makeSelectRuta from './selectors';
import reducer from './reducer';
import saga from './saga';
import Img from '../../components/Img';
import Banner from '../../images/metro.png';

import RutaForm from './form';
import makeSelectLogin from '../Login/selectors';

/* eslint-disable react/prefer-stateless-function */
export class Ruta extends React.Component {
  componentDidMount() {
    if (!this.props.login.invitado) {
      this.props.salir();
    }
    this.props.limpiar();
  }

  submit = values => {
    this.props.calcular(values.toJS());
  };

  render() {
    const result = [];
    this.props.ruta.detalle.map(r =>
      result.push(<div>{JSON.stringify(r)} </div>),
    );
    return (
      <div>
        {this.props.login.admin && <button onClick={this.props.irHistorial}>Ir historial</button>}
        <button onClick={this.props.salir}>Salir</button>
        <h3>Mapa metro</h3>
        <Img src={Banner} alt="react-boilerplate - Logo" />
        <h3>Consultar ruta optima</h3>
        <RutaForm onSubmit={this.submit} />
        {this.props.ruta.detalle.length > 0 && <h3>Mejores rutas</h3>}
        {result}
      </div>
    );
  }
}

Ruta.propTypes = {
  login: PropTypes.object,
  ruta: PropTypes.object,
  salir: PropTypes.func,
  calcular: PropTypes.func,
};

const mapStateToProps = createStructuredSelector({
  ruta: makeSelectRuta(),
  login: makeSelectLogin(),
});

function mapDispatchToProps(dispatch) {
  return {
    calcular: body => dispatch({ type: 'CALCULAR', body }),
    limpiar: () => dispatch({ type: 'SAVE_DETALLE', value: [] }),
    salir: () => dispatch(push('/')),
    irHistorial: () => dispatch(push('/historial')),
  };
}

const withConnect = connect(
  mapStateToProps,
  mapDispatchToProps,
);

const withReducer = injectReducer({ key: 'ruta', reducer });
const withSaga = injectSaga({ key: 'ruta', saga });

export default compose(
  withReducer,
  withSaga,
  withConnect,
)(Ruta);
