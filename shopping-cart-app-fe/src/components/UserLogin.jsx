import React, { useState } from 'react';
import { Form, Button, Card, Container} from 'react-bootstrap';
import '../styles/userlogin.css'
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

function UserLogin() {

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate=useNavigate();
  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };
  const handleSubmit = (event) => {
    event.preventDefault();
    axios.post(`http://localhost:8080/user/login-em?email=${email}&password=${password}`)
    .then((res)=>{
   console.log(res.data);
   localStorage.setItem("user",JSON.stringify(res.data.body))
   navigate('/userhome');
    })
    .catch(()=>{
     console.log("error has been occured");
    })
    
  };

  const handleForgotPassword = () => {
   navigate('/userforgotpassword')
  };
  const handleSignUp=()=>{
    navigate('/userregister')
  }
  return (
    <div className='userlogin'> 
      <div className="input_group">  
     <Container className=" min-vh-100">
      <Card className="p-4 shadow-lg" style={{ width: '400px',transform:"translateX(0px)" }}>
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
              <a href="#!" onClick={handleForgotPassword} className="text-decoration-none" style={{color:"black" , fontSize:"12px", cursor:"pointer"}}>
                Forgot Password?
              </a>
            </div>
            <Button variant="outline-secondary" className='signin' style={{marginLeft:"0px"}} onClick={handleSignUp}>sign up</Button>
          </Form>
        </Card.Body>
      </Card>
    </Container>
      </div>
    </div>
  )
}

export default UserLogin
