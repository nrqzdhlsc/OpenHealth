import React, { Component } from 'react';
import NavBar from './NavBar';
import Background from '../images/ai-market.png';
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
    color: "#FFFFFF"
}
var gridStyle = {
    margin: "20px"
}
class FederatedLearning extends Component {
    render() {
        return (
            <div>
                <div>
                    <NavBar />
                    <div style={sectionStyle}>
                        <Header as="h1" className="data-sharing" style={headerStyle}>
                            AI市场
                    </Header>
                        <Header as="h3" className="data-desc" style={headerStyle}>
                            Pariatur nisi culpa minim aliquip consectetur.。
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
                                {/* 三个数据集 */}
                                <Datasets />
                            </Grid.Column>
                            <Grid.Column width={6}>
                                <Header as="h2" textAlign="center">
                                    我的模型需求
                                </Header>
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