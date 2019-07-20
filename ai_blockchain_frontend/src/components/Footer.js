import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import PropTypes from 'prop-types';
import {
    Button,
    Container,
    Menu,
    Responsive,
    Segment,
    Visibility,
    Icon,
    Header
} from 'semantic-ui-react';

const getWidth = () => {
    const isSSR = typeof window === 'undefined'
    return isSSR ? Responsive.onlyTablet.minWidth : window.innerWidth
}
var footStyle = {
    padding: "15px"
}

class Footer extends Component {
    render() {
        return (
            <Responsive getWidth={getWidth} minWidth={Responsive.onlyTablet.minWidth}>

                    <Segment
                        inverted
                        textAlign='center'
                        style={{ minHeight: 70, padding: '1em 0em' }}
                        vertical
                    >
                    <Header as="h3" style={footStyle}>
                           Copyright © 2019.7 ZJU Bithacks.  All Rights Reserved.
                        </Header>
                    </Segment>
                    
            </Responsive>
        )
    }
}


export default Footer;