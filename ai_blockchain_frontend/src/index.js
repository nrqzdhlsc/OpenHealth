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
import AIMarket from './components/AIMarket';
import Login from './components/Login';
import Register from './components/Register';
import Model1 from './components/Model1';
import ErrorPage from './components/ErrorPage';

ReactDOM.render(
    <Router>
        <div>
            <Switch>
                <Route exact path="/" component={App} />
                <Route path="/medical-data-sharing" component={MedicalDataSharing} />
                <Route path="/federated-learning" component={FederatedLearning} />
                <Route path="/ai-market" component={AIMarket}/>
                <Route path="/login" component={Login} />
                <Route path="/register" component={Register} />
                <Route path="/model1" component={Model1} />
                <Route path="/error" component={ErrorPage} />
            </Switch>
        </div>
    </Router>,
    document.getElementById('root'));

serviceWorker.unregister();
