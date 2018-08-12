import React from 'react';
import PropTypes from 'prop-types';
import { Field, reduxForm } from 'redux-form/immutable';
import { renderField } from '../Login/form';

function estacionValida(estacion) {
  const estacionNum = Number(estacion);
  return estacionNum > 0 && estacionNum < 16;
}

const validate = values => {
  // IMPORTANT: values is an Immutable.Map here!
  const errors = {};
  if (!values.get('origen')) {
    errors.origen = 'Required';
  } else if (!estacionValida(values.get('origen'))) {
    errors.origen = 'estacion no valida';
  }
  if (!values.get('destino')) {
    errors.destino = 'Required';
  } else if (!estacionValida(values.get('destino'))) {
    errors.destino = 'estacion no valida';
  }

  return errors;
};

const RutaForm = props => {
  const { handleSubmit, pristine, reset, submitting } = props;
  return (
    <form onSubmit={handleSubmit(props.onSubmit)}>
      <Field
        name="origen"
        type="text"
        component={renderField}
        label="Estacion origen"
      />
      <br />
      <Field
        name="destino"
        type="text"
        component={renderField}
        label="Estacion destino"
      />
      <br />
      <div>
        <button type="submit" disabled={submitting}>
          Consultar
        </button>
        <button type="button" disabled={pristine || submitting} onClick={reset}>
          Limpiar
        </button>
      </div>
    </form>
  );
};

RutaForm.propTypes = {
  handleSubmit: PropTypes.func,
  pristine: PropTypes.any,
  onSubmit: PropTypes.func,
  reset: PropTypes.func,
  submitting: PropTypes.any,
};

export default reduxForm({
  // a unique name for the form
  form: 'ruta',
  validate,
})(RutaForm);
