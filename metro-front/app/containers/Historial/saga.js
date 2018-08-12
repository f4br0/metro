/**
 * Gets the repositories of the user from Github
 */

import { call, put, takeLatest } from 'redux-saga/effects';

import request from 'utils/request';
import { ERROR_MESSAGE } from '../App/constants';

/**
 * Github repos request/response handler
 */
export function* historial() {
  // Select username from store
  const requestURL = `http://localhost:8080/consultar-hitorial`;

  try {
    // Call our request helper (see 'utils/request')
    const response = yield call(request, requestURL);
    yield put({ type: 'SAVE_HISTORIAL', value: response });
  } catch (err) {
    yield put({ type: 'SAVE_HISTORIAL', value: ERROR_MESSAGE });
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
  yield takeLatest('HISTORIAL', historial);
}
