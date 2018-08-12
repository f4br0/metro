/**
 * Gets the repositories of the user from Github
 */

import { call, put, takeLatest } from 'redux-saga/effects';
import { push } from 'react-router-redux';

import request from 'utils/request';

/**
 * Github repos request/response handler
 */
export function* login(req) {
  const requestURL = `http://localhost:8080/login`;
  try {
    // Call our request helper (see 'utils/request')
    const response = yield call(request, requestURL, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ ...req.body }),
    });
    yield put({ type: 'SAVE_ADMIN', value: response });
    yield put({ type: 'SAVE_INVITADO', value: true });
    yield put(push('/ruta'));
  } catch (err) {
    yield put({ type: 'SAVE_ADMIN', value: false });
  }
}

/**
 * Root saga manages watcher lifecycle
 */
export default function* githubData() {
  // Watches for LOAD_REPOS actions and calls getRepos when one comes in.
  // By using `takeLatest` only the result of the latest API call is applied.
  // It returns task descriptor (just like fork) so we can continue execution
  // It will be cancelled automatically on component unmount
  yield takeLatest('LOGIN', login);
}
