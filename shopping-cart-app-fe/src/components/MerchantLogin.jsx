import React, { useState } from 'react';
import '../styles/merchantlogin.css'
import { Form, Button, Card, Container } from 'react-bootstrap';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
function MerchantLogin() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  let navigate=useNavigate();
  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };
  const handleSubmit = (event) => {
    event.preventDefault();
     axios.post(`http://localhost:8080/merchant/login-em?email=${email}&password=${password}`)
     .then((res)=>{
    console.log(res.data);
    console.log(res.data.body.id);
    localStorage.setItem("merchant",JSON.stringify(res.data.body))
    navigate(`/merchanthome`)
     })
     .catch(()=>{
      console.log("error has been occured");
     })
  };

  const handleForgotPassword = () => {
   
         navigate('/merchantforgotpassword')

  };

  const handleSignup=()=>{
       navigate('/merchantregister')
  }
  return (
    <div className='userlogin'> 
      <div className="input_group">  
    <Container className="d-flex justify-content-center align-items-center min-vh-100">
      <Card className="p-4 shadow-lg" style={{ width: '400px' }}>
        <Card.Body>
          <h3 className="text-center mb-4">Login</h3>
          <Form onSubmit={handleSubmit}>
            <Form.Group controlId="formBasicEmail">
              <Form.Label>Email address</Form.Label>
              <Form.Control
                type="email"
                placeholder="Enter email"
                value={email}
                onChange={handleEmailChange}
                required
              />
            </Form.Group>

            <Form.Group controlId="formBasicPassword">
              <Form.Label>Password</Form.Label>
              <Form.Control
                type="password"
                placeholder="Password"
                value={password}
                onChange={handlePasswordChange}
                required
              />
            </Form.Group>

            <Button variant="primary" type="submit" style={{marginTop:"5px"}} className="w-100 mb-3">
              Login
            </Button>
            <div className="text-center">
              <a  onClick={handleForgotPassword} className="text-decoration-none" style={{color:"black" , fontSize:"12px", cursor:"pointer"}}>
                Forgot Password?
              </a>
            </div>
            <Button variant="outline-primary"  onClick={handleSignup} style={{marginLeft:"0px"}} >sign up</Button>
          </Form>
        </Card.Body>
      </Card>
    </Container>
      </div>
    </div>
  )
}

export default MerchantLogin
