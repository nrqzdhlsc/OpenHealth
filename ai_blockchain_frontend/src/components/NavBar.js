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
    Icon
} from 'semantic-ui-react';

const getWidth = () => {
    const isSSR = typeof window === 'undefined'
    return isSSR ? Responsive.onlyTablet.minWidth : window.innerWidth
}

let flag = 0;

/* Heads up!
 * Neither Semantic UI nor Semantic UI React offer a responsive navbar, however, it can be implemented easily.
 * It can be more complicated, but you can create really flexible markup.
 */
class NavBar extends Component {
    constructor(props) {
        super(props);
        this.state = {
            activeNum: 0
        };
    }

    hideFixedMenu = () => this.setState({ fixed: false })
    showFixedMenu = () => this.setState({ fixed: true })

    // 主页
    onClickMainPage = () => {
        // this.setState({activeNum : 0}, () => { console.log(this.state.activeNum) });
        // alert("主页...")
        flag = 0;
    }

    // 数据共享
    onClickMedicalDataSharing = () => {
        // this.setState({activeNum : 1}, () => {
        //     console.log(this.state.activeNum)
        // });
        flag = 1;
        
        // alert("数据共享页面...")
        // this.style.className = "active"
    }

    // 联邦学习
    onClickFTL = () => {
        // this.setState({activeNum : 2}, () => {
        //     console.log("FTL",this.state.activeNum)
        // });
        flag = 2;
        
        // alert("联邦学习页面...")
    }

    // AI市场
    onClickAIMarket = () => {
        // this.setState({activeNum : 3}, () => {
        //     console.log(this.state.activeNum);
        // });
        flag = 3;
        
        // alert("AI市场页面...")
    }

    // 登录
    onLogin = () => {
        // alert("登录...")
    }

    // 注册
    onRegister = () => {
        // alert("注册...")
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
                                <Menu.Item> 
                                    <Icon name="cloud" size="large" color="blue"></Icon>
                                </Menu.Item>
                                <Menu.Item as={Link} to="/" className={flag === 0 ? "active nav-link" : "inactive nav-link"} onClick={this.onClickMainPage} >主页</Menu.Item>
                                <Menu.Item as={Link} to="/medical-data-sharing" className={ flag === 1 ? "active nav-link" : "inactive nav-link"} id="sharing"  onClick={this.onClickMedicalDataSharing} >数据共享</Menu.Item>
                                <Menu.Item as={Link} to="/federated-learning" className={ flag === 2 ? "active nav-link" : "inactive nav-link"} onClick={this.onClickFTL}>联邦学习</Menu.Item>
                                <Menu.Item as={Link} to="/ai-market" className={ flag === 3 ? "active nav-link" : "inactive nav-link"} onClick={this.onClickAIMarket}>AI市场</Menu.Item>
                                
                                {/* <Menu.Item position='right'>
                                    <Button as={Link} to="/login" display={"none"} inverted={!fixed} onClick={this.onLogin}>
                                        登录
                                    </Button>
                                    <Button as={Link} to="/register" inverted={!fixed} onClick={this.onRegister} primary={fixed} style={{ marginLeft: '0.5em' }}>
                                        注册
                                    </Button>
                                </Menu.Item> */}
                            </Container>
                        </Menu>
                    </Segment>
                </Visibility>
                { children }
            </Responsive>
        )
    }
}

// 生产环境下可删除
NavBar.propTypes = {
    children: PropTypes.node,
}

export default NavBar;