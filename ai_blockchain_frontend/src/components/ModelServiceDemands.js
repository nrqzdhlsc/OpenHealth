import React, { Component } from 'react';
import FederatedLearningModal from './FederatedLearningModal';

import {
    Card,
    Image,
    Icon,
    Menu,
    Feed,
    Button,
    Checkbox,
    Divider
} from 'semantic-ui-react';

var checkBoxStyle = {
    textAlign: "center"
}

const onClickModel1 = () => {
    alert("使用肺炎检测模型")
}

const ModelServiceDemands = () => (
    <Card.Group>
        <Card fluid>
            <Card.Content>
                {/* <Image floated='right' size='mini' src='https://react.semantic-ui.com/images/avatar/large/steve.jpg' /> */}
                <FederatedLearningModal showBtn={<Button primary floated="right">
                    <Icon name="hand point up" />
                    选择
                </Button>}/>
                <Card.Header>X光片检测肺炎AI模型</Card.Header>
                <Card.Meta>肺炎检测</Card.Meta>
                <Card.Description>
                    直接通过肺部X光图片判断出是否为健康或肺炎状态。
                </Card.Description>
            </Card.Content>
        </Card>
        <Card fluid>
            <Card.Content>
                <Button primary disabled floated="right" onClick={onClickModel1}>
                    <Icon name="hand point up" />
                    选择
                </Button>
                <Card.Header>临床报告阅读理解模型</Card.Header>
                <Card.Meta>内容理解</Card.Meta>
                <Card.Description>
                    AI助手，可快速回复医生或一般用户的医学问题。
                </Card.Description>
            </Card.Content>
        </Card>
        <Card fluid>
            <Card.Content>
                <Button primary disabled floated="right" onClick={onClickModel1}>
                    <Icon name="hand point up" />
                    选择
                </Button>
                <Card.Header>医嘱药物推荐模型</Card.Header>
                <Card.Meta>推荐模型</Card.Meta>
                <Card.Description>
                    可根据病情，以往病史，诊断结果给出医嘱药物组合推荐。
                </Card.Description>
            </Card.Content>
        </Card>
    </Card.Group>
)

export default ModelServiceDemands;