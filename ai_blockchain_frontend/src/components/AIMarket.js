import React, { Component } from 'react';
import NavBar from './NavBar';
import Background from '../images/ai-market.png';
import Datasets from './Datasets';
import ModelServiceDemands from './ModelServiceDemands';
import Footer from './Footer';
import Link from 'react-router-dom';

import {
    Grid,
    Header,
    Image,
    Card
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

let pic1 = '/AI_Market/AIDetection.jpeg'
let pic2 = '/AI_Market/Drug.jpeg'
let pic3 = '/AI_Market/Record.jpg'

let fixedSize = {
    height:"400px",
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
                            <Grid.Column width={16}>
                                <Header as="h2" textAlign="center">
                                    AI模型列表
                                </Header>
                            </Grid.Column>
                        </Grid.Row>
                        <Grid.Row centered>
                            <Grid.Column width={5}>
                                <Card style={fixedSize}>
                                    <Image
                                        src={pic1}
                                        size='medium'
                                        href='/model1'
                                        target='_blank'
                                    />
                                    <Card.Header as="h3">
                                        肺炎检测模型
                                    </Card.Header>
                                    <Card.Description>
                                        模型描述
                                    </Card.Description>
                                </Card>
                            </Grid.Column>
                            <Grid.Column width={5}>
                                <Card style={fixedSize}>
                                    <Image
                                        src={pic2}
                                        size='medium'
                                        target='_blank'

                                    />
                                    <Card.Header as="h3">
                                        临床报告阅读理解模型
                                    </Card.Header>
                                    <Card.Description>
                                        模型描述
                                    </Card.Description>
                                </Card>
                            </Grid.Column>
                            <Grid.Column width={5}>
                                <Card style={fixedSize}>
                                    <Image
                                        src={pic3}
                                        size='medium'
                                        target='_blank'
                                    />
                                    <Card.Header as="h3">
                                        医嘱药物推荐模型
                                    </Card.Header>
                                    <Card.Description>
                                        模型描述
                                    </Card.Description>
                                </Card>
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