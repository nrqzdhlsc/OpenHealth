import React, { Component } from 'react';

import {
    Image,
    Card
} from 'semantic-ui-react';

class DataSharingDesc extends Component {
    render() {
        return (
            <Card>
                <Card.Content textAlign="center">
                    数据共享描述。
                </Card.Content>
                
                <Image src={ require('../images/matthew.png') }/>
            </Card>
        )
    }
}

export default DataSharingDesc;
