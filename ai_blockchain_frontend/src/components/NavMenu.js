import React, { Component } from 'react';
import NavLink from './NavLink';
import '../css/NavLink.css';

class NavMenu extends Component {
    render() {
        let links = [
            { label: "数据共享", url: "#data_sharing" },
            { label: "联邦学习", url: "#federal_transfer_learning" },
            { label: "AI市场", url: "#ai_market" }
        ]
        return (
            <nav className="main-nav">
                { links.map((item, index) => (<NavLink key={index} info={item} />)) }
            </nav>
        )
    }
}

export default NavMenu;
