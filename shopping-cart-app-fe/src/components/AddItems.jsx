import axios from 'axios';
import React, { useState } from 'react'
import { Alert, Button, Col, Form, Row } from 'react-bootstrap';
import "../styles/AddItems.css"
function AddItems() {
  const [showAlert, setShowAlert] = useState(false);
  const [name,setName]=useState("");
  const [model,setModel]=useState("");
  const [category,setCategory]=useState("");
  const [brand,setBrand]=useState("");
  const [description,setDescription]=useState("");
  const [cost,setCost]=useState("");
  const [image,setImage]=useState("");
  
  let merchant=JSON.parse(localStorage.getItem("merchant"));//using local storage getting id from one component to another component
  const handleSubmit = (event) => {
    event.preventDefault();
   let data={name,model,brand,category,description,cost,image}
   axios.post(`http://localhost:8080/product/${merchant.id}`,data)
   .then(()=>{
    setShowAlert(true);
   })
   .catch(()=>{
    alert("error");
   })
  };
  return (
    <div className='form-container'>
       <Form onSubmit={handleSubmit} className='form'>
      {showAlert && (
        <Alert variant="success" onClose={() => setShowAlert(false)} dismissible>
          product added successfully.
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
       Add Product
      </Button>
    </Form> 
    </div>
  )
}

export default AddItems
