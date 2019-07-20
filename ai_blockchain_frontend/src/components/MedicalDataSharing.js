import React, { Component } from 'react';
import { Grid, Header } from 'semantic-ui-react'
import NavBar from './NavBar';
import DataSharingDesc from './DataSharingDesc';
import Background from '../images/data-sharing.jpeg';
import Footer from './Footer';

import Patient from './Patient';
import Doctor from './Doctor';
import Platform from './Platform';
import Hospital from './Hospital';

import {
    Image,
    Container
} from 'semantic-ui-react';

// 设置背景图
var sectionStyle = {
    width: "100%",
    height: "400px",
    // makesure here is String确保这里是一个字符串，以下是es6写法
    backgroundImage: `url(${Background})`
};

var headerStyle = {
    color: "#FFFFFF"
}

let HOST = "http://10.0.0.117:8080"

let HOSTPITAL_URL = HOST + '/getHospitalRecords'
let PATIENT_REQUEST_URL = HOST + '/getPatientRequests'
let PATIENT_INFO_URL = HOST + '/getPatientInfo'
let PLATFORM_URL = HOST + '/getPlatformRecords'

var hospitals = []
var doctors = []
var patients = []
var platforms = []

class MedicalDataSharing extends Component {

    constructor(props) {
        super(props);
        this.state = {
            hospitals: hospitals,
            doctors: doctors,
            patients: patients,
            platforms: platforms
        };
    }

    componentWillMount() {
        console.log("组件即将加载")
        // 医院视角下的数据流
        fetch(HOSTPITAL_URL, {
            method: 'GET'
        }).then((response) => response.json())
        .then((data) => {
            console.log("data: ", data)  
            console.log(this.state.hospitals)
            this.setState({
               hospitals:data.data
            },function(){
                console.log("in");
                console.log(this.state.hospitals)
            })
        });
        
        // 患者消息列表
        fetch(PATIENT_REQUEST_URL, {
            method: 'GET'
        }).then((response) => response.json())
        .then((data) => {
            console.log("data: ", data)  
            console.log(this.state.patients)
            this.setState({
               patients:data.data
            },function(){
                console.log("in");
                console.log(this.state.patients)
            })
        });
        // 异步方式，待DEBUG
        /*
        patients = await fetch(PATIENT_REQUEST_URL, {
            method: 'GET'
        }).then((response) => response.json());
        console.log("患者消息：", patients);

        // 医生可查看所有患者列表
        doctors = await fetch(PATIENT_INFO_URL, {
            method: 'GET'
        }).then((response) => response.json());
        console.log("医生可查看所有患者列表：", doctors);


        // 平台可查看信息
        platforms = await fetch(PLATFORM_URL, {
            method: 'GET'
        }).then((response) => response.json());
        console.log("平台可查看信息：", platforms);
        */

        fetch(PATIENT_INFO_URL, {
            method: 'GET'
        }).then((response) => response.json())
        .then((data) => {
            console.log("data: ", data)  
            console.log(this.state.doctors)
            this.setState({
               doctors: data.data
            }, function(){
                console.log("in");
                console.log(this.state.doctors)
            })
        });

        fetch(PLATFORM_URL, {
            method: 'GET'
        }).then((response) => response.json())
        .then((data) => {
            console.log("data: ", data)  
            console.log(this.state.platforms)
            this.setState({
               platforms:data.data
            }, function(){
                console.log("in");
                console.log(this.state.platforms)
            })
        });
    }
    render() {
        return (
            <div>
                <NavBar />
                <div style={sectionStyle}>
                    <Header as="h1" className="data-sharing" style={headerStyle}>
                        数据共享
                    </Header>
                    <Header as="h3" className="data-desc" style={headerStyle}>
                        以患者为中心的医疗数据共享平台。
                    </Header>
                </div> 
                <Header as="h1" textAlign='center'>
                    功能说明
                </Header>
                <Container>
                    <p>
                        {/* Sunt nostrud amet culpa deserunt dolore dolore cupidatat non excepteur sit non. Elit esse proident labore non amet ea deserunt minim magna laboris amet magna eu. Consequat pariatur et excepteur ad sint tempor sint ut. */}
                    </p>
                    <br />
                    <Image src={require('../images/core-combine-frame.png')} />
                </Container>
                
                {/* <DataSharingDesc /> */}
                <Header as="h1" textAlign='center'>
                            功能演示
                        </Header>
                <Grid columns='equal' celled>
                    <Grid.Row columns='equal'>
                        <Grid.Column>
                            {/* "title": "xxx", "allInfo": "xxx" */}
                            <Hospital info={this.state.hospitals} />
                        </Grid.Column>
                        <Grid.Column>
                            <Patient info={this.state.patients} />
                        </Grid.Column>
                        <Grid.Column>
                            <Doctor info={this.state.doctors} />
                        </Grid.Column>
                        <Grid.Column>
                            <Platform info={this.state.platforms} />
                        </Grid.Column>
                    </Grid.Row>
                </Grid>
                <Footer />
            </div>
        )
    }
}

export default MedicalDataSharing;
