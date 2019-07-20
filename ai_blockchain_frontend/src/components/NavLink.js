import React, { Component } from 'react';
import '../css/NavLink.css';

class NavLink extends Component {
    render() {
        return (
            < a href={this.props.info.url} className="main-link" > {this.props.info.label}</a >
        )
    }
}

export default NavLink;