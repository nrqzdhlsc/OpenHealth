import React, { Component } from 'react';
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

const Datasets = () => (
    <Card.Group>
        <Card fluid>
            <Card.Content>
                <Feed>
                    <Feed.Event>
                        <Feed.Label image={require('../images/avatar/small/elliot.jpg')} />
                        <Feed.Content>
                            <Feed.Summary>
                                浙大第一附属医院肺炎X光片数据集
                                <p>Lorem minim excepteur deserunt nulla officia est consectetur laboris.</p>
                                <Checkbox style={checkBoxStyle} label={<label>选择</label>} />
                            </Feed.Summary>
                        </Feed.Content>
                    </Feed.Event>
                </Feed>
            </Card.Content>
        </Card>
        <Card fluid>
            <Card.Content>
                <Feed>
                    <Feed.Event>
                        <Feed.Label image={require('../images/avatar/small/molly.png')} />
                        <Feed.Content right>
                            <Feed.Summary>
                                北京协和医院肺炎X光片数据集
                                <p>Nostrud aliquip occaecat esse aliqua anim commodo.</p>
                                <Checkbox label={<label>选择</label>} />
                            </Feed.Summary>
                        </Feed.Content>
                    </Feed.Event>
                </Feed>
            </Card.Content>
        </Card>
        <Card fluid>
            <Card.Content>
                <Feed>
                    <Feed.Event>
                        <Feed.Label image={require('../images/avatar/small/jenny.jpg')} />
                        <Feed.Content>
                            <Feed.Summary>
                                上海复旦大学附属华山医院肺炎X光片数据集
                                <p>Sint enim mollit cillum veniam amet minim.</p>
                                <Checkbox style={checkBoxStyle} label={<label>选择</label>} />
                            </Feed.Summary>
                        </Feed.Content>
                    </Feed.Event>
                </Feed>
            </Card.Content>
        </Card>
    </Card.Group>
)

export default Datasets;