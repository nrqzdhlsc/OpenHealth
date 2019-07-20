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
    Header,
    Dropdown
} from 'semantic-ui-react';

var ContainerStyle = {
    background:"#032951"
}

let urlInput = '/pneumonia_input/NORMAL1.jpeg'
let urlOutput = '/pneumonia_output/img.png'

const photoOptions = [
  {
    key: 'Photo1',
    text: 'Photo1',
    value: '/pneumonia_input/NORMAL1.jpeg',
  },
  {
    key: 'Photo2',
    text: 'Photo2',
    value: '/pneumonia_input/NORMAL2.jpeg',
  },
  {
    key: 'Photo3',
    text: 'Photo3',
    value: '/pneumonia_input/NORMAL3.jpeg',
  },
  {
    key: 'Photo4',
    text: 'Photo4',
    value: '/pneumonia_input/virus1.jpeg',
  },
  {
    key: 'Photo5',
    text: 'Photo5',
    value: '/pneumonia_input/virus2.jpeg',
  },
  {
    key: 'Photo6',
    text: 'Photo6',
    value: '/pneumonia_input/virus3.jpeg',
  }
]

console.log(process.env.PUBLIC_URL)

class Model1 extends Component {
    constructor(props) {
        super(props);
        this.state={
            picInput:'/pneumonia_input/NORMAL1.jpeg',
            picOutput:'/pneumonia_output/img.png'
        }
    }

    onClickDropDown = (event,data) => {
        // this.setState({activeNum : 0}, () => { console.log(this.state.activeNum) });
        // alert("主页...")
        
        urlInput = data.value;
        console.log(urlInput);
        this.setState({picInput:urlInput});
        
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
                            <Grid.Column width={7} >
                                <img
                                    src={this.state.picInput}
                                />
                            </Grid.Column>


                            <Grid.Column width={7}>
                                <img
                                    src={urlOutput}
                                    />

                            </Grid.Column>

                        </Grid.Row>

                        <Grid.Row  centered columns={10}>
                            <Grid.Column width={8}>
                                    <Dropdown
                                        placeholder='Select Photo'
                                        fluid
                                        selection
                                        options={photoOptions}
                                        onChange={this.onClickDropDown}
                                      />

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