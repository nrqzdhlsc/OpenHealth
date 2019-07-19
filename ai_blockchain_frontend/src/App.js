import React, { Component } from 'react';
import './App.css';
import NavMenu from './components/NavMenu';
import Homepage from './components/HomePage';
import TabExampleAttachedFalse from './components/Tab';

class App extends Component {
  render() {
    return (
      <div className="App">
        <Homepage />
        {/* <NavMenu /> */}
        {/* <TabExampleAttachedFalse /> */}
      </div>
    );
  }
}

export default App;
