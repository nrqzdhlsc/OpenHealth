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
                <FederatedLearningModal showBtn={<Button primary disabled floated="right">
                    <Icon name="hand point up" />
                    选择
                </Button>}/>
                <Card.Header>X光片检测肺炎AI模型1</Card.Header>
                <Card.Meta>肺炎检测</Card.Meta>
                <Card.Description>
                    Et deserunt aliqua commodo in pariatur commodo excepteur culpa non sit magna.
                </Card.Description>
            </Card.Content>
        </Card>
        <Card fluid>
            <Card.Content>
                <Button primary disabled floated="right" onClick={onClickModel1}>
                    <Icon name="hand point up" />
                    选择
                </Button>
                <Card.Header>X光片检测肺炎AI模型2</Card.Header>
                <Card.Meta>肺炎检测</Card.Meta>
                <Card.Description>
                    Excepteur consequat eiusmod pariatur ex sint in exercitation labore eiusmod eu amet officia amet.
                </Card.Description>
            </Card.Content>
        </Card>
        <Card fluid>
            <Card.Content>
                <Button primary disabled floated="right" onClick={onClickModel1}>
                    <Icon name="hand point up" />
                    选择
                </Button>
                <Card.Header>X光片检测肺炎AI模型3</Card.Header>
                <Card.Meta>肺炎检测</Card.Meta>
                <Card.Description>
                    Velit dolor qui consequat veniam sit consectetur.
                </Card.Description>
            </Card.Content>
        </Card>
    </Card.Group>
)

export default ModelServiceDemands;