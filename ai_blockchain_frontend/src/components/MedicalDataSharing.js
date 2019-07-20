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

let HOSTPITAL_URL = 'http://192.168.43.244:8080/getHospitalRecords'
let PATIENT_REQUEST_URL = 'http://192.168.43.244:8080/getPatientRequests'
let PATIENT_INFO_URL =  'http://192.168.43.244:8080/getPatientInfo'
let PLATFORM_URL = 'http://192.168.43.244:8080/getPlatformRecords'

var hospicals = {}
var doctors = {}
var patients = {}
var platforms = {}

class MedicalDataSharing extends Component {

    async componentWillMount() {
        console.log("组件即将加载")
        fetch(HOSTPITAL_URL, {
            method: 'GET'
        }).then((response) => {
            console.log("请求返回结果")
            alert("请求结果：", response)
            console.log("请求结果：", response)
            hospicals = response.json
            alert("hospitals info: ", hospicals)
        });
    }

    render() {
        let hospitals = [
            { label: "浙江大学第一附属" },
        ]
        let doctors = [
            { label: "医生小林（北京协和医院）" }
        ]
        let patients = [
            { label: "患者老高（浙大第一附属医院）" }
        ]

        let platforms = [
            { label: "OpenHealth平台" }
        ]
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
                        Sunt nostrud amet culpa deserunt dolore dolore cupidatat non excepteur sit non. Elit esse proident labore non amet ea deserunt minim magna laboris amet magna eu. Consequat pariatur et excepteur ad sint tempor sint ut.
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
                            <Hospital info={hospitals[0]} />
                        </Grid.Column>
                        <Grid.Column>
                            <Patient info={patients[0]} />
                        </Grid.Column>
                        <Grid.Column>
                            <Doctor info={doctors[0]} />
                        </Grid.Column>
                        <Grid.Column>
                            <Platform info={platforms[0]} />
                        </Grid.Column>
                    </Grid.Row>
                </Grid>
                <Footer />
            </div>
        )
    }
}

export default MedicalDataSharing;
