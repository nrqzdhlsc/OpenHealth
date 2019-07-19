import React, { Component } from 'react';
import PropTypes from 'prop-types';

import {
    Button,
    Container,
    Header,
    Icon
} from 'semantic-ui-react';

const onClickStart = () => {
    alert("开始进入数据共享页...")
}

class HomePageHeading extends Component {
    render() {
        return (
            <Container text>
                <Header
                    as='h1'
                    content={this.props.info.label}
                    style={{
                        fontSize: '2em',
                        fontWeight: 'normal',
                        marginBottom: 0,
                        marginTop: '2em'
                    }}
                />
                <Header
                    as='h2'
                    content={this.props.info.description}
                    style={{
                        fontSize: '1.5em',
                        fontWeight: 'normal',
                        marginTop: '1.5em'
                    }}
                />
                <Button primary size='huge' onClick={onClickStart}>
                    开始
                    <Icon name='right arrow' />
                </Button>
            </Container>
        )
    }
} 

export default HomePageHeading;
