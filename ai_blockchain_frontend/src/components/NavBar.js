import React, { Component } from 'react';
import PropTypes from 'prop-types';
import {
    Button,
    Container,
    Menu,
    Responsive,
    Segment,
    Visibility,
} from 'semantic-ui-react';

const getWidth = () => {
    const isSSR = typeof window === 'undefined'
    return isSSR ? Responsive.onlyTablet.minWidth : window.innerWidth
}

/* Heads up!
 * Neither Semantic UI nor Semantic UI React offer a responsive navbar, however, it can be implemented easily.
 * It can be more complicated, but you can create really flexible markup.
 */
class NavBar extends Component {
    state = {}

    hideFixedMenu = () => this.setState({ fixed: false })
    showFixedMenu = () => this.setState({ fixed: true })

    // 数据共享
    onClickMedicalDataSharing = () => {
        alert("数据共享页面...")
    }

    // 联邦学习
    onClickFTL = () => {
        alert("联邦学习页面...")
    }

    // AI市场
    onClickAIMarket = () => {
        alert("AI市场页面...")
    }

    // 登录
    onLogin = () => {
        alert("登录...")
    }

    // 注册
    onRegister = () => {
        alert("注册...")
    }

    // 主页
    onClickMainPage = () => {
        alert("主页...")
    }

    render() {
        const { children } = this.props
        const { fixed } = this.state

        return (
            <Responsive getWidth={getWidth} minWidth={Responsive.onlyTablet.minWidth}>
                <Visibility
                    once={false}
                    onBottomPassed={this.showFixedMenu}
                    onBottomPassedReverse={this.hideFixedMenu}
                >
                    <Segment
                        inverted
                        textAlign='center'
                        style={{ minHeight: 70, padding: '1em 0em' }}
                        vertical
                    >
                        <Menu
                            fixed={fixed ? 'top' : null}
                            inverted={!fixed}
                            pointing={!fixed}
                            secondary={!fixed}
                            size='large'
                        >
                            <Container>
                                <Menu.Item as='a' active onClick={this.onClickMainPage}> 主页 </Menu.Item>
                                <Menu.Item as='a' onClick={this.onClickMedicalDataSharing} >数据共享</Menu.Item>
                                <Menu.Item as='a' onClick={this.onClickFTL}>联邦学习</Menu.Item>
                                <Menu.Item as='a' onClick={this.onClickAIMarket}>AI市场</Menu.Item>
                                
                                <Menu.Item position='right'>
                                    <Button as='a' inverted={!fixed} onClick={this.onLogin}>
                                        登录
                                    </Button>
                                    <Button as='a' inverted={!fixed} onClick={this.onRegister} primary={fixed} style={{ marginLeft: '0.5em' }}>
                                        注册
                                    </Button>
                                </Menu.Item>
                            </Container>
                        </Menu>
                    </Segment>
                </Visibility>
                {children}
            </Responsive>
        )
    }
}

// 生产环境下可删除
NavBar.propTypes = {
    children: PropTypes.node,
}

export default NavBar;