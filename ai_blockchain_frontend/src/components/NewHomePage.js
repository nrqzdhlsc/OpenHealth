import React, { Component } from 'react';
import NavBar from './NavBar';
import HomepageHeading from './HomePageHeading';

class NewHomePage extends Component {
    render() {
        let infos = [
            { 
                label: "医疗数据共享", 
                description: "以患者为中心的医疗数据共享平台"
            },
            {
                label: "联邦学习",
                description: "联邦学习简介"
            },
            {
                label: "AI市场",
                description: "AI市场描述"
            }
        ]
        return (
            <div>
                <NavBar />
                { infos.map((item, index) => (<HomepageHeading key={index} info={item} />)) }
            </div>  
        )
    }
}

export default NewHomePage;