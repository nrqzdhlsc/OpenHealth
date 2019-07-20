import React from 'react'
import { Button, Header, Image, Modal } from 'semantic-ui-react';
let terminalPic = '/terminal/fl_fullScreen.png'

const FederatedLearningModal = (props) => (
    <Modal trigger={ props.showBtn }>
        <Modal.Header as="h3" textAlign="center">训练过程</Modal.Header>
        <Modal.Content image>
            <Image wrapped size='huge' src={terminalPic} />
        </Modal.Content>
    </Modal>
)

export default FederatedLearningModal;