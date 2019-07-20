import React, { Component } from 'react';
import NavBar from './NavBar';
import Background from '../images/federated-learning.png';
import Datasets from './Datasets';
import ModelServiceDemands from './ModelServiceDemands';
import Footer from './Footer';

import {
    Grid,
    Header,
    Checkbox
} from 'semantic-ui-react';

// 设置背景图
var sectionStyle = {
    width: "100%",
    height: "400px",
    // makesure here is String确保这里是一个字符串，以下是es6写法
    backgroundImage: `url(${Background})`
};

var headerStyle = {
    color: "#000000"
}
var gridStyle = {
    margin: "30px"
}
class FederatedLearning extends Component {
    render() {
        return (
            <div>
                <div>
                    <NavBar />
                    <div style={sectionStyle}>
                        <Header as="h1" className="data-sharing" style={headerStyle}>
                            联邦学习
                    </Header>
                        <Header as="h3" className="data-desc" style={headerStyle}>
                            联合建模服务，打破数据孤岛，实现价值流动。
                    </Header>
                    </div>
                    {/* 左右布局，左10 + 右6 */}
                    {/* 公开数据集 + 我的模型需求 */}
                    <Grid stackable style={gridStyle}>
                        <Grid.Row columns={16} >
                            <Grid.Column width={10}>
                                <Header as="h2" textAlign="center">
                                    公开数据集
                                </Header>
                                <br />
                                {/* 三个数据集 */}
                                <Datasets />
                            </Grid.Column>
                            <Grid.Column width={6}>
                                <Header as="h2" textAlign="center">
                                    我的模型需求
                                </Header>  
                                <br />
                                <ModelServiceDemands />
                            </Grid.Column>
                        </Grid.Row>
                    </Grid>
                    <h2></h2>
                </div>
                <Footer />
            </div>
        )
    }
}

export default FederatedLearning;