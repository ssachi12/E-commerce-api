import React, { useEffect, useState } from 'react'
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import '../styles/merchantRegister.css'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

function EditMerchant() {
  const[id,setId]=useState("")
  const [name,setName]=useState("");
  const [phone,setPhone]=useState("");
  const [email,setEmail]=useState("");
  const [gst,setGst]=useState("");
  const [password,setPassword]=useState("");
  let navigate=useNavigate()
  useEffect(()=>{
    let merchant=JSON.parse(localStorage.getItem("merchant"))
    setId(merchant.id)
  setName(merchant.name)
  setEmail(merchant.email)
  setGst(merchant.gst)
  setPhone(merchant.phone)
  setPassword(merchant.password)
  },[])
  let handleSubmit=(e)=>{
    e.preventDefault();
    let updatedata={id,name,phone,email,gst,password}
       axios.put(`http://localhost:8080/merchant`,updatedata).then((res)=>{
          console.log(res.data);
          console.log("merchant has been updated");
          let con=window.confirm("once you update. you should login back")
         if(con){
          navigate('/merchantlogin');
         }
     
       }).catch(()=>{
           console.log("something went wrong");
       })
  }
  return (
    <div className="background">
    <Form className="registration-form"  autoComplete="off" style={{marginTop:"-25px"}}>
 
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
 
       <Form.Group controlId="gst"  autoComplete="off">
         
         <Form.Control
           type="text"
           name="gst"
           value={gst}
           onChange={(e)=>{setGst(e.target.value)}}
           placeholder='enter GST'
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
         Update
       </Button>
     </Form>
  </div>
  )
}

export default EditMerchant
