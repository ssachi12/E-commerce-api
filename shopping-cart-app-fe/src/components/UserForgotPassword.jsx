import axios from 'axios';
import React, { useState } from 'react'
import { Container, Form, Button } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';

function UserForgotPassword() {

  let [emailId,setEmailId]=useState("")
  let navigate=useNavigate();

  let sendMail=(e)=>{
e.preventDefault();
  axios.get(`http://localhost:8080/user/reset?email=${emailId}`).then(()=>{
   console.log("email sucessfully sent");
   let con=window.confirm("email has sent successfully kindly reset the password");
   if(con){
      navigate('/merchantlogin')
   }
  }).catch(()=>{
console.log("error has been occured");
  })
  }
  return (
    <div style={{width:"100%",background:"black"}}>
  <Container  className="d-flex align-items-center justify-content-center  text-primary" style={{ minHeight: '100vh' ,background:"black",}}>
      <div className="forgot-password-form p-4 rounded">
        <Form>
          <Form.Group controlId="formEmail">
            <Form.Control
              type="email"
              placeholder="Enter your email"
              value={emailId}
              onChange={(e)=>{setEmailId(e.target.value)}}
              className="rounded-pill py-3 px-4 mb-3"
            />
          </Form.Group>
          <div className="d-flex justify-content-center">
            <Button variant="primary" onClick={sendMail} className="rounded-pill px-4">
              Reset Password
            </Button>
          </div>
        </Form>
      </div>
    </Container>
    </div>
  )
}

export default UserForgotPassword
