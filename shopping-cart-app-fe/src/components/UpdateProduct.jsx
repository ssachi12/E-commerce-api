import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Alert, Button, Col, Form, Row } from 'react-bootstrap';
import "../styles/AddItems.css"
import { useParams } from 'react-router-dom'

function UpdateProduct() { 
  const [showAlert, setShowAlert] = useState(false);
  const [name,setName]=useState("");
  const [model,setModel]=useState("");
  const [category,setCategory]=useState("");
  const [brand,setBrand]=useState("");
  const [description,setDescription]=useState("");
  const [cost,setCost]=useState("");
  const [image,setImage]=useState("");
  const[id,setId]=useState("")
  let {pid}=useParams();
 let handleSubmit=(e)=>{
  e.preventDefault()
  let merchant=JSON.parse(localStorage.getItem("merchant"));
  let merchant_id=merchant.id;
  let data={name, model, brand, category, description, cost, image, pid}
  axios.put(`http://localhost:8080/product/${merchant_id}/${pid}`,data).then(()=>{
    setShowAlert(true);
   })
   .catch(()=>{
    alert("error");
   })
 }
 useEffect(()=>{
  axios.get(`http://localhost:8080/product/fetch/${pid}`).then((res)=>{
    console.log(res.data.body);
    setName(res.data.body.name);
    setModel(res.data.body.model);
    setDescription(res.data.body.description)
    setBrand(res.data.body.brand)
    setCategory(res.data.body.category)
    setImage(res.data.body.image)
    setCost(res.data.body.cost)
    setId(res.data.body.id)
  })
 },[])
  return (
    <div className='form-container'>
    <Form onSubmit={handleSubmit} className='form'>
   {showAlert && (
     <Alert variant="success" onClose={() => setShowAlert(false)} dismissible>
       product updated successfully.
     </Alert>
   )}
   <Form.Group as={Row} className='form-group'>
     <Form.Label column sm={2}>
       Name
     </Form.Label>
     <Col sm={10}>
       <Form.Control
         type="text"
         value={name}
         onChange={(e)=>{setName(e.target.value)}}
         placeholder="Enter product name"
       />
     </Col>
   </Form.Group>

   <Form.Group as={Row}  className='form-group'>
     <Form.Label column sm={2}>
       Model
     </Form.Label>
     <Col sm={10}>
       <Form.Control
         type="text"
         value={model}
         onChange={(e)=>{setModel(e.target.value)}}
         placeholder="Enter Model"
       />
     </Col>
   </Form.Group>

   <Form.Group as={Row}  className='form-group'>
     <Form.Label column sm={2}>
      Brand
     </Form.Label>
     <Col sm={10}>
       <Form.Control
         type="text"
         value={brand}
         onChange={(e)=>{setBrand(e.target.value)}}
         placeholder="Enter Brand"
       />
     </Col>
   </Form.Group>
   <Form.Group as={Row}  className='form-group'>
     <Form.Label column sm={2}>
      category
     </Form.Label>
     <Col sm={10}>
       <Form.Control
         type="text"
         value={category}
         onChange={(e)=>{setCategory(e.target.value)}}
         placeholder="Enter category"
       />
     </Col>
   </Form.Group>
 
   <Form.Group  className='form-group'>
     <Form.Label>description</Form.Label>
     <Form.Control
       as="textarea"
       value={description}
       onChange={(e)=>{setDescription(e.target.value)}}
       rows={4}
       placeholder="Enter product description"
     />
   </Form.Group>
   <Form.Group as={Row}  className='form-group'>
     <Form.Label column sm={2}>
      price
     </Form.Label>
     <Col sm={10}>
       <Form.Control
         type="number"
         value={cost}
         onChange={(e)=>{setCost(e.target.value)}}
         placeholder="Enter price"
       />
     </Col>
   </Form.Group>
   <Form.Group as={Row}  className='form-group'>
     <Form.Label column sm={2}>
      Image
     </Form.Label>
     <Col sm={10}>
       <Form.Control
         type="text"
         value={image}
         onChange={(e)=>{setImage(e.target.value)}}
         placeholder="upload image address"
       />
     </Col>
   </Form.Group>
   <Button variant="primary" type="submit">
    Update Product
   </Button>
 </Form> 
 </div>
  )
}

export default UpdateProduct
