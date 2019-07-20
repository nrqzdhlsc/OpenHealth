import React, { Component } from 'react';
import './App.css';
import NavMenu from './components/NavMenu';
import Homepage from './components/HomePage';
import TabExampleAttachedFalse from './components/Tab';
import MedicalDataSharing from './components/MedicalDataSharing';
import NewHomePage from './components/NewHomePage';


class App extends Component {
  render() {
    return (
      <div className="App">
        {/* <Homepage /> */}
        <NewHomePage />
        {/* <NavMenu /> */}
        {/* <TabExampleAttachedFalse /> */}
      </div>
    );
  }
}

export default App;
