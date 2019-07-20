import React, { Component } from 'react';
import { Card, Feed } from 'semantic-ui-react';

import {
    Button
} from 'semantic-ui-react';

class Platform extends Component {
    render() {
        return (
            <Card>
                <Card.Content>
                    <Card.Header textAlign="left">{this.props.info["title"]}</Card.Header>
                </Card.Content>
                <Card.Content>
                    <Feed>
                        <Feed.Event>
                            <Feed.Label image={require('../images/avatar/small/elliot.jpg')} />
                            <Feed.Content>
                                <Feed.Date content='1分钟前' />
                                <Feed.Summary>
                                    患者老高（浙大第一附属医院）授权了北京协和医院小林医生的查看请求，平台已分发重加密钥匙给李医生，并把患者密文数据发送给小林医生。
                                </Feed.Summary>
                            </Feed.Content>
                        </Feed.Event>
                        <Feed.Event>
                            <Feed.Label image={require('../images/avatar/small/jenny.jpg')} />
                            <Feed.Content>
                                <Feed.Date content='10分钟前' />
                                <Feed.Summary>
                                    北京协和医院的医生小林申请查看患者老高（浙大第一附属医院）的病例数据。
                                </Feed.Summary>
                            </Feed.Content>
                        </Feed.Event>
                        <Feed.Event>
                            <Feed.Label image={require('../images/avatar/small/molly.png')} />
                            <Feed.Content>
                                <Feed.Date content='1天前' />
                                <Feed.Summary>
                                    患者小应（浙大第一附属医院）授权了北京协和医院李医生的查看请求，平台已分发重加密钥匙给李医生，并把患者密文数据发送给李医生。
                                </Feed.Summary>
                            </Feed.Content>
                        </Feed.Event>
                    </Feed>
                </Card.Content>
            </Card>
        )
    }
}

export default Platform;