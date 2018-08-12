import React from 'react';
import PropTypes from 'prop-types';
import { Field, reduxForm } from 'redux-form/immutable';

export const renderField = ({
  input,
  label,
  type,
  meta: { touched, error, warning },
}) => (
  <div>
    {label}
    <div>
      <input {...input} type={type} placeholder={label} />
      {touched &&
        ((error && <span>{error}</span>) ||
          (warning && <span>{warning}</span>))}
    </div>
  </div>
);

renderField.propTypes = {
  input: PropTypes.object,
  label: PropTypes.any,
  type: PropTypes.string,
  meta: PropTypes.object,
  submitting: PropTypes.any,
};

const validate = values => {
  // IMPORTANT: values is an Immutable.Map here!
  const errors = {};
  if (!values.get('usuario')) {
    errors.usuario = 'Campo requerido';
  }
  if (!values.get('clave')) {
    errors.clave = 'Campo requerido';
  }
  return errors;
};

const LoginForm = props => {
  const { handleSubmit, pristine, reset, submitting } = props;
  return (
    <form onSubmit={handleSubmit(props.onSubmit)}>
      <p>
        puede acceder como invitado, ingresando como nombre de usuario y
        contraseña, su cédula.
      </p>
      <br />
      <Field
        name="usuario"
        type="text"
        component={renderField}
        label="Usuario"
      />
      <br />
      <Field
        name="clave"
        type="password"
        component={renderField}
        label="Contraseña"
      />
      <br />
      <div>
        <button type="submit" disabled={submitting}>
          Ingresar
        </button>
        <button type="button" disabled={pristine || submitting} onClick={reset}>
          Limpiar
        </button>
      </div>
    </form>
  );
};

LoginForm.propTypes = {
  handleSubmit: PropTypes.func,
  pristine: PropTypes.any,
  onSubmit: PropTypes.func,
  reset: PropTypes.func,
  submitting: PropTypes.any,
};

export default reduxForm({
  // a unique name for the form
  form: 'login',
  validate,
})(LoginForm);
