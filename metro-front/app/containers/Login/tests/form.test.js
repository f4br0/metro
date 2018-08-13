import React from 'react';
import { shallow } from 'enzyme';

import LoginForm from '../form';

describe('<LoginForm />', () => {
  it('should render fetch the form on mount ', () => {
    // const submitSpy = jest.fn();
    shallow(<LoginForm onSubmit={() => {}} />);
    // expect(submitSpy).toHaveBeenCalled();
  });
});
