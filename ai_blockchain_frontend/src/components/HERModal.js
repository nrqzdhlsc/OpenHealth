import React from 'react'
import { Button, Header, Image, Modal } from 'semantic-ui-react';
let terminalPic = '/terminal/fl_fullScreen.png'
let newPic = 'http://img.sher.vip/return_example.jpeg';

const HERModal = (props) => (
    <Modal trigger={ props.showBtn }>
        <Modal.Header as="h3" textAlign="center">电子病例</Modal.Header>
        <Modal.Content image>
            <Image wrapped size='huge' src={newPic} />
        </Modal.Content>
    </Modal>
)

export default HERModal;