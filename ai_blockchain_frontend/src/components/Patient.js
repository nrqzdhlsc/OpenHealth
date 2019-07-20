import React, { Component } from 'react';
import { Card, Feed } from 'semantic-ui-react';

import {
    Button
} from 'semantic-ui-react';

let HOST = "http://localhost:8080"
let CONFIRM_REQUEST= HOST + '/agreeForRequest'

// 确认查看请求
const onClickConfirm = (requestId) => {
    console.log("确认")
    var details = {
        "account": "P_A",
        "requestId": requestId
    }
    var formBody = [];
    for (var property in details) {
        var encodedKey = encodeURIComponent(property);
        var encodedValue = encodeURIComponent(details[property]);
        formBody.push(encodedKey + "=" + encodedValue);
    }
    formBody = formBody.join("&");
    let response = fetch(CONFIRM_REQUEST, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        body:formBody
    }).then((response) => response.json())
    console.log(response);
    // window.location.href="http://localhost:3000/medical-data-sharing";
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
                                        {item.status === 0 ? <div>
                                            <Button positive onClick={() => onClickConfirm && onClickConfirm(item.requestId)}>确认</Button>
                                            <Button negative onClick={onClickReject}>拒绝</Button>
                                        </div>: item.status === 1 ?<div>
                                            <Button disabled>已确认</Button>
                                        </div>:<div>
                                            <Button disabled>已拒绝</Button>
                                        </div>}
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