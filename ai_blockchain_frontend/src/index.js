import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router , Route, Switch } from 'react-router-dom';
import './index.css';
import 'semantic-ui-css/semantic.min.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
// import NewHomePage from './components/NewHomePage';
import MedicalDataSharing from './components/MedicalDataSharing';
import FederatedLearning from './components/FederatedLearning';

ReactDOM.render(
    <Router>
        <div>
            <Switch>
                <Route exact path="/" component={App} />
                <Route path="/medical-data-sharing" component={MedicalDataSharing} />
                <Route path="/federated-learning" component={FederatedLearning} />
            </Switch>
        </div>
    </Router>,
    document.getElementById('root'));

serviceWorker.unregister();
