import React, { Component } from 'react';
import { Card, Feed } from 'semantic-ui-react';

import {
    Button
} from 'semantic-ui-react';

// 确认查看请求
const onClickConfirm = () => {
    alert("确认")
}

// 拒绝查看请求
const onClickReject = () => {
    alert("拒绝")
}

class Patient extends Component {
    constructor(props) {
        super(props); 
        this.state = {
            "patients": [
                {"name": "小应", "message": "收到xxx"}
            ]
        }
    }
    render() {
        return (
            <Card>
                <Card.Content>
                    <Card.Header>{ this.props.info["title"] }</Card.Header>
                </Card.Content>
                <Card.Content>
                    <Feed>
                        <Feed.Event>
                            <Feed.Label image={require('../images/avatar/small/elliot.jpg')} />
                            <Feed.Content>
                                <Feed.Date content='1分钟前' />
                                <Feed.Summary>
                                    收到<a>小林医生（北京协和医院）</a> 查看病例请求。
                                    <br />
                                    <Button positive onClick={onClickConfirm}>确认</Button>
                                    <Button negative onClick={onClickReject}>拒绝</Button>
                                </Feed.Summary>
                            </Feed.Content>
                        </Feed.Event>

                        <Feed.Event>
                            <Feed.Label image={ require('../images/avatar/small/molly.png')} />
                            <Feed.Content>
                                <Feed.Date content='1天前' />
                                <Feed.Summary>
                                    收到<a>赵医生（上海复旦大学附属华山医院）</a>的查看病例请求。
                                    <br />
                                    <Button positive onClick={onClickConfirm}>确认</Button>
                                    <Button negative onClick={onClickReject}>拒绝</Button>
                                </Feed.Summary>
                            </Feed.Content>
                        </Feed.Event>

                        <Feed.Event>
                            <Feed.Label image={require('../images/avatar/small/elliot.jpg')} />
                            <Feed.Content>
                                <Feed.Date content='1周前' />
                                <Feed.Summary>
                                    收到<a>陈医生（华中科技大学同济医学院附属同济医院）</a>的查看病例请求。
                                    <br />
                                    <Button positive onClick={onClickConfirm}>确认</Button>
                                    <Button negative onClick={onClickReject}>拒绝</Button>
                                </Feed.Summary>
                            </Feed.Content>
                        </Feed.Event>
                    </Feed>
                </Card.Content>
            </Card>
        )
    }
}
export default Patient;