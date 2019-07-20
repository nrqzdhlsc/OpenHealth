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

let pic1 = '/AI_Market/AIDetection.png'
let pic2 = '/AI_Market/Drug.png'
let pic3 = '/AI_Market/Record.png'

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
                            人工智能模型服务市场。
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
                                    <Card.Header as="h3" textAlign='center'>
                                        肺炎检测模型
                                    </Card.Header>
                                    <Card.Description>
                                        模型根据输入的图片判断是否为健康肺或者有肺炎症状。
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
                                    <Card.Header as="h3" textAlign='center'>
                                        临床报告阅读理解模型
                                    </Card.Header>
                                    <Card.Description>
                                        AI助手，可快速回复医生或一般用户的医学问题。
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
                                    <Card.Header as="h3" textAlign='center'>
                                        医嘱药物推荐模型
                                    </Card.Header>
                                    <Card.Description>
                                        可根据病情，以往病史，诊断结果给出医嘱药物组合推荐。
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