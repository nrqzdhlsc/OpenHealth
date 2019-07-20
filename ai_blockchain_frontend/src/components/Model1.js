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
    Grid,
    Header
} from 'semantic-ui-react';

var ContainerStyle = {
    background:"#032951"
}


class Model1 extends Component {
    constructor(props) {
        super(props);
    }
    render() {
        return (
            <div>
                
                <NavBar />
                <Header as="h1" textAlign="center" >AI模型计算
                </Header>
                <Container>
                    <Grid >
                        <Grid.Row centered>
                            <Grid.Column width={6} >
                                <Image
                                    src='https://react.semantic-ui.com/images/wireframe/image-text.png'
                                    as='a'
                                    target='_blank'
                                />
                            </Grid.Column>


                            <Grid.Column width={6}>
                                <Image
                                        src='https://react.semantic-ui.com/images/wireframe/image-text.png'
                                        as='a'
                                        target='_blank'
                                    />
                            </Grid.Column>
                            <Grid.Column width={2}>
                                检测结果
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
                </Container>
            </div>
        )
    }
}

export default Model1;