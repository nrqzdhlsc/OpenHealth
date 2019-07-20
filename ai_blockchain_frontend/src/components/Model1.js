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
    Dropdown,
    Label
} from 'semantic-ui-react';

var ContainerStyle = {
    background:"#032951"
}

let urlInput = '/pneumonia_input/NORMAL1.jpeg'
let urlOutput = '/pneumonia_output/NORMAL1.jpeg'

let inputList = ['/pneumonia_input/NORMAL1.jpeg','/pneumonia_input/NORMAL2.jpeg',
                '/pneumonia_input/Virus1.jpeg','/pneumonia_input/Virus2.jpeg']

let outputList = ['/pneumonia_output/NORMAL1.jpeg','/pneumonia_output/NORMAL2.jpeg',
'/pneumonia_output/Virus1.jpeg','/pneumonia_output/Virus2.jpeg']

const photoOptions = [
  {
    key: 'Normal',
    text: 'X-Ray Normal Photo1',
    value: 0,
  },
  {
    key: 'Normal',
    text: 'X-Ray Normal Photo2',
    value: 1,
  },
  {
    key: 'Pneumonia',
    text: 'X-Ray Pneumonia Photo3',
    value: 2,
  },
  {
    key: 'Pneumonia',
    text: 'X-Ray Pneumonia Photo4',
    value: 3,
  }
]


console.log(process.env.PUBLIC_URL)

class Model1 extends Component {
    constructor(props) {
        super(props);
        this.state={
            picInput:'/pneumonia_input/NORMAL1.jpeg',
            picOutput:'/pneumonia_output/img.png',
            picLabel:'X-Ray Normal Photo1',
            picInputStatus:'Normal',
            picOutputStatus:'Normal',
            isOutputShow:false
        }
    }

    onClickDropDown = (event,data) => {
        // this.setState({activeNum : 0}, () => { console.log(this.state.activeNum) });
        // alert("主页...")
        console.log(data);
        urlInput = inputList[data.value];
        
        console.log(urlInput);
        this.setState({picInput:urlInput});
        this.setState({picOutput:outputList[data.value]})
        this.setState({picLabel:photoOptions[data.value].text});
        this.setState({picOutputStatus:photoOptions[data.value].key})
        this.setState({isOutputShow:false});
        
    }

    onClickButton = () => {
        this.setState({isOutputShow:true});
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
                                    src={this.state.picInput}
                                    label={{ as: 'a', color: 'blue', content: this.state.picLabel, ribbon: true }}
                                />
                                
                            </Grid.Column>


                            <Grid.Column width={7} >
                                <Image
                                    src={this.state.picOutput}
                                    label={{ as: 'a', color: 'red', content: this.state.picOutputStatus, ribbon: true }}
                                    
                                    hidden={!this.state.isOutputShow}

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
                                    <Button primary onClick={this.onClickButton}>检测</Button>
                            </Grid.Column>

                        </Grid.Row>     
                    </Grid>
                </Container>
            </div>
        )
    }
}

export default Model1;