import React from 'react'
import NavBar from "./NavBar";

import { Button, Form, Grid, Header, Image, Message, Segment } from 'semantic-ui-react'

const Login = () => (
    <Grid textAlign='center' style={{ height: '100vh' }} verticalAlign='middle'>
        <Grid.Column style={{ maxWidth: 450 }}>
            <Header as='h2' color='teal' textAlign='center'>
                <Image src='/logo.png' /> Log-in to your account
      </Header>
            <Form size='large'>
                <Segment stacked>
                    <Form.Input fluid icon='user' iconPosition='left' placeholder='E-mail address' />
                    <Form.Input
                        fluid
                        icon='lock'
                        iconPosition='left'
                        placeholder='Password'
                        type='password'
                    />

                    <Button color='teal' fluid size='large'>
                        登录
          </Button>
                </Segment>
            </Form>
            <Message>
                New to us? <a href='#'>注册</a>
            </Message>
        </Grid.Column>
    </Grid>
)

export default Login;
