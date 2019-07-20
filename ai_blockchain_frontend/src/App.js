import React, { Component } from 'react';
import './App.css';
import NavMenu from './components/NavMenu';
import NewHomePage from './components/NewHomePage';
import Footer from './components/Footer';

class App extends Component {
  render() {
    return (
      <div className="App">
        <NewHomePage />
        <Footer />
      </div>
    );
  }
}

export default App;
