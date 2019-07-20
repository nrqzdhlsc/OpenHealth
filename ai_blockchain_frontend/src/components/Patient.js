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
        let messages = this.props.info.requests;
        return (
            <Card>
                <Card.Content>
                    <Card.Header>{ this.props.info["title"] }</Card.Header>
                </Card.Content>
                <Card.Content>
                    <Feed>
                        {messages === undefined ? () => { } : (messages.map((item, index) =>
                            (<Feed.Event>
                                <Feed.Label image={require('../images/avatar/small/jenny.jpg')} />
                                <Feed.Content>
                                    <Feed.Date content='5分钟前' />
                                    <Feed.Summary>
                                        { item.requestInfo }
                                        <br />
                                        {/* 2：已拒绝， 1：已确认，0：两个按钮都存在 */}
                                        { item.status === 2 ? () => (
                                            <div>
                                                <Button positive onClick={onClickConfirm}>确认</Button>
                                                <Button negative onClick={onClickReject}>拒绝</Button>
                                            </div>
                                        ) : () => {} }
                                        
                                    </Feed.Summary>
                                </Feed.Content>
                            </Feed.Event>)))}

                    </Feed>
                </Card.Content>
            </Card>
        )
    }
}
export default Patient;