import React, { Component } from 'react';
import { Card, Feed } from 'semantic-ui-react';

import {
    Button
} from 'semantic-ui-react';

class Doctor extends Component {
    render() {
        return (
            <Card>
                <Card.Content>
                    <Card.Header>{ this.props.info["label"] }</Card.Header>
                </Card.Content>
                <Card.Content>
                    <Feed>
                        <Feed.Event>
                            <Feed.Label image={require('../images/avatar/small/jenny.jpg')} />
                            <Feed.Content>
                                <Feed.Date content='10分钟前' />
                                <Feed.Summary>
                                    病人A
                                    <p>姓名</p>
                                    <p>病情描述</p>
                                    <Button positive>查看</Button>
                                </Feed.Summary>  
                            </Feed.Content>
                        </Feed.Event>
                        <Feed.Event>
                            <Feed.Label image={require('../images/avatar/small/molly.png')} />
                            <Feed.Content>
                                <Feed.Date content='1天前' />
                                <Feed.Summary>
                                    病人B
                                    <p>姓名</p>
                                    <p>病情描述</p>
                                    <Button negative>请求查看</Button>
                                </Feed.Summary>
                            </Feed.Content>
                        </Feed.Event>

                        <Feed.Event>
                            <Feed.Label image={require('../images/avatar/small/elliot.jpg')} />
                            <Feed.Content>
                                <Feed.Date content='1周前' />
                                <Feed.Summary>
                                    病人C
                                </Feed.Summary>
                            </Feed.Content>
                        </Feed.Event>

                        <Feed.Event>
                            <Feed.Label image={require('../images/avatar/small/jenny.jpg')} />
                            <Feed.Content>
                                <Feed.Date content='10分钟前' />
                                <Feed.Summary>
                                    病人D
                                </Feed.Summary>
                            </Feed.Content>
                        </Feed.Event>
                        <Feed.Event>
                            <Feed.Label image={require('../images/avatar/small/jenny.jpg')} />
                            <Feed.Content>
                                <Feed.Date content='10分钟前' />
                                <Feed.Summary>
                                    病人E
                                </Feed.Summary>
                            </Feed.Content>
                        </Feed.Event>
                    </Feed>
                </Card.Content>
            </Card>
        )
    }
}

export default Doctor;