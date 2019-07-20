import React, { Component } from 'react';
import { Card, Feed } from 'semantic-ui-react';
import HERModal from './HERModal';

import {
    Button
} from 'semantic-ui-react';

let HOST = "http://localhost:8080";
let CREATEREQUEST = HOST +"/requestForData";


class Doctor extends Component {

    state = {
        buttonType:-1
    }

    onClickRequest = (dataId) => {
        console.log("onClickRequest调用:",dataId)
        var details = {
            "account": "D_B",
            "dataId": dataId
        }
        var formBody = [];
        for (var property in details) {
            var encodedKey = encodeURIComponent(property);
            var encodedValue = encodeURIComponent(details[property]);
            formBody.push(encodedKey + "=" + encodedValue);
        }
        formBody = formBody.join("&");
        let response = fetch(CREATEREQUEST, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
            },
            body:formBody
        }).then((response) => response.json())
        console.log(response)
        this.setState({
            buttonType:0
        }, () => {
            console.log("this...", this.state)
        })
        window.location.href="http://localhost:3000/medical-data-sharing";
    }

    render() {
        let messages = this.props.info.patientInfos;
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
                                        <p>{ item.name }{index==0? '(浙大第一附属医院)':'(上海复旦大学附属华山医院)'}</p>
                                        <p>病例数据</p>
                                        {item.status === 0 ? <div>
                                            <Button onClick={() => this.onClickRequest && this.onClickRequest(item.dataId)}>{this.state.buttonType == 0 && index ==0? '查看':'请求查看'}</Button>
                                        </div>: <div>
                                        <HERModal showBtn = {<Button positive>查看</Button>} />
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

export default Doctor;