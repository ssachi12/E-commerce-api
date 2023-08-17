import React, { useEffect, useState } from 'react'
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import '../styles/merchantRegister.css'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';
function EditUser() {
  const[id,setId]=useState("")
  const [name,setName]=useState("");
  const [phone,setPhone]=useState("");
  const [email,setEmail]=useState("");
  const [password,setPassword]=useState("");
  let navigate=useNavigate();
  useEffect(()=>{
    let user=JSON.parse(localStorage.getItem("user"))
    setId(user.id)
  setName(user.name)
  setEmail(user.email)
  setPhone(user.phone)
  setPassword(user.password)
  },[])
  let handleSubmit=(e)=>{
    e.preventDefault();
    let updatedata={id,name,phone,email,password}
       axios.put(`http://localhost:8080/user`,updatedata).then((res)=>{
          console.log(res.data);
          console.log("user has been updated");
          let con=window.confirm("once you update. you should login back")
         if(con){
          navigate('/userlogin');
         }
     
       }).catch(()=>{
           console.log("something went wrong");
       })
  }
  return (
    <div>
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
 </div>
  )
}

export default EditUser
