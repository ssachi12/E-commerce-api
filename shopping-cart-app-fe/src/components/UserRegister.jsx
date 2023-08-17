import React, { useState } from 'react'
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import '../styles/merchantRegister.css'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
function UserRegister() {
    const [name,setName]=useState("");
    const [phone,setPhone]=useState("");
    const [email,setEmail]=useState("");
    const [password,setPassword]=useState("");
    let navigate=useNavigate();
    const handleSubmit = (event) => {
     event.preventDefault();
     let user={name,phone,email,password}
     axios.post("http://localhost:8080/user",user).then(()=>{
      alert("registered sucessfully");
      navigate('/userlogin')
      console.log("data has been added");
     })
     .catch(()=>{
      console.log("error has been occured");
     })

      };
  return (
    <div>
       <div className="background" style={{height:"100vh"}}>
   <Form className="registration-form"  autoComplete="off">

  <Form.Group controlId="name"  autoComplete="off">
    
    <Form.Control
      type="text"
      name="name"
      value={name}
      onChange={(e)=>{setName(e.target.value)}}
      placeholder='enter your name'
      required
    />
  </Form.Group>

  <Form.Group controlId="phone"  autoComplete="off">
  
    <Form.Control
      type="tel"
      name="phone"
      value={phone}
      onChange={(e)=>{setPhone(e.target.value)}}
      placeholder='enter phone number'
      required
    />
  </Form.Group>
  <Form.Group controlId="email" >
        
        <Form.Control
          type="email"
          name="email"
          autoComplete="off"
          value={email}
          onChange={(e)=>{setEmail(e.target.value)}}
          placeholder='enter email'
          required
        />
      </Form.Group>
      <Form.Group controlId="password"  autoComplete="off">
        
        <Form.Control
          type="password"
          name="password"
          value={password}
          onChange={(e)=>{setPassword(e.target.value)}}
          placeholder='enter password'
          required
        />
      </Form.Group>

      <Button variant="primary" type="submit" onClick={handleSubmit}>
        Register
      </Button>
    </Form>
 </div>
    </div>
  )
}

export default UserRegister
