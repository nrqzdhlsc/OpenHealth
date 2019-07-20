import React, { Component } from 'react';
import NavBar from './NavBar';
import HomepageHeading from './HomePageHeading';
import '../css/NewHomePage.css';

import Background from '../images/background.jpg';

import {
    Image,
    Header,
    Container
} from 'semantic-ui-react';

// 设置首页背景图
var sectionStyle = {
    width: "100%",
    height: "400px",
    // makesure here is String确保这里是一个字符串，以下是es6写法
    backgroundImage: `url(${Background})`
};

var headerStyle = {
    color: "#FFFFFF"
}
class NewHomePage extends Component {
    constructor(props) {
        super(props);
    }

    render() {
        let infos = [
            { 
                label: "医疗数据共享", 
                description: "以患者为中心的医疗数据共享平台"
            },
            {
                label: "联邦学习",
                description: "联邦学习简介"
            },
            {
                label: "AI市场",
                description: "AI市场描述"
            }
        ]
        return (
            <div>
                <NavBar />
                <div style={sectionStyle}>
                    <Header as="h1" className="data-sharing" style={headerStyle}>
                        数据共享
                    </Header>
                    <Header as="h3" className="data-desc" style={headerStyle}>
                        以患者为中心的医疗数据共享平台。
                    </Header>
                </div> 
                <div stackable className="solution-details">
                    <br/>
                    <Header as="h1" className="solution-bg">
                        方案全景图
                    </Header> 
                    <Header as="h3" className="solution-bg">
                       Mollit voluptate ea enim aute laboris sit eiusmod.
                       Sunt qui qui mollit in occaecat sint non ea.
                    </Header>
                    <Container>
                        <Image fluid src={require("../images/core-combine-frame.png")} />
                    </Container>
                </div>
                {/* { infos.map((item, index) => (<HomepageHeading key={index} info={item} />)) } */}
            </div>  
        )
    }
}

export default NewHomePage;