import React, { Component } from 'react';
import { Card, Feed } from 'semantic-ui-react';

import {
    Button
} from 'semantic-ui-react';

class Hospital extends Component {
    render() {
        let messages = this.props.info.allInfo;
        console.log("allInfo: ", messages === undefined)
        return (
            <Card>
                <Card.Content>
                    <Card.Header>{this.props.info["title"]}</Card.Header>
                </Card.Content>
                <Card.Content>
                    <Feed>
                        { messages === undefined ? () => {} : (messages.map((item, index) => 
                            (<Feed.Event>
                                <Feed.Label image={require('../images/avatar/small/jenny.jpg')} />
                                <Feed.Content>
                                <Feed.Date content='5分钟前' />
                                <Feed.Summary>
                                   { item }
                                </Feed.Summary>
                            </Feed.Content>
                        </Feed.Event>))) }
                    </Feed>
                </Card.Content>
            </Card>
        )
    }
}

export default Hospital;