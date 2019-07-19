import React from 'react';
import { Tab } from 'semantic-ui-react';

const panes = [
    { menuItem: '数据共享', render: () => <Tab.Pane ui centered ={false}>数据共享简介</Tab.Pane> },
    { menuItem: '联邦学习', render: () => <Tab.Pane attached={false}>联邦学习简介</Tab.Pane> },
    { menuItem: 'AI市场', render: () => <Tab.Pane attached={false}>AI市场简介</Tab.Pane> },
]

const TabExampleAttachedFalse = () => <Tab menu={{ attached: false }} panes={panes} />

export default TabExampleAttachedFalse;