import React, { Component } from 'react';
import NavBar from './NavBar';
import DropdownExampleSelection from './DropDownBox';
import {
    Button,
    Container,
    Menu,
    Responsive,
    Segment,
    Visibility,
    Image,
    Icon,
    Grid
} from 'semantic-ui-react';

class Model1 extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <div>
                <NavBar />
                <Grid >
                    <Grid.Row centered>
                        <Grid.Column width={8} >
                            <Image
                                src='https://react.semantic-ui.com/images/wireframe/image-text.png'
                                as='a'
                                href='/model1'
                                target='_blank'
                            />

                        </Grid.Column>
                    </Grid.Row>

                    <Grid.Row  centered columns={10}>
                        <Grid.Column width={8}>
                                <DropdownExampleSelection/>

                        </Grid.Column>

                        <Grid.Column>
                                <Button primary>检测</Button>
                        </Grid.Column>

                    </Grid.Row>     
                </Grid>
            </div>
        )
    }
}

export default Model1;