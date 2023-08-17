import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { Container, Row, Col, Button, Card, Image, ListGroup } from 'react-bootstrap';
import "../styles/ProductView.css"
import { useNavigate } from 'react-router-dom';
const ViewProducts = () => {
    let [products,setProducts]=useState([]);
    let navigate=useNavigate();
 let merchant=JSON.parse(localStorage.getItem("merchant"));
useEffect(()=>{
axios.get(`http://localhost:8080/product/${merchant.id}`).then((res)=>{
    localStorage.setItem("product",JSON.stringify(res.data.body));
      console.log(res.data);
    setProducts(res.data.body)
    console.log(products);
}).catch(()=>{
    console.log("error has been occured");
})
},[])

let deleteProduct=(pid)=>{
  let con=window.confirm("are you sure want to delete this product");
if(con){
    axios.delete(`http://localhost:8080/product/${pid}`).then(()=>{
        alert("product is deleted")
        window.location.reload();
     }).catch(()=>{
        alert('something went wrong');
     })
}
}
let updateProduct=(pid)=>{
   navigate(`/merchanthome/products/updateproduct/${pid}`);
}
  return (
    <div className='container'>
   <Container>
    <Row  xs={1} sm={2} md={3} lg={4} className='card-row'>
    {
        products.map((product)=>{
            return(
                <Card style={{ width: '18rem',display:"flex" }} className='card'>
                <Card.Img variant="top" src={product.image} className='card-image' />
                <Card.Body>
                  <Card.Title>{product.brand} {product.name}</Card.Title>
                  <Card.Text className='card-text'>
                    {product.description}
                  </Card.Text>
                </Card.Body>
                <ListGroup className="list-group-flush card-group">
                  <ListGroup.Item>PRICE: {product.cost}</ListGroup.Item>
                  <ListGroup.Item>RATINGS: {product.rating}</ListGroup.Item>
                  <ListGroup.Item>CATEGORY: {product.category}</ListGroup.Item>
                </ListGroup>
                <Card.Body>
                  <Button variant='primary' onClick={()=>{updateProduct(product.id)}} >Update</Button>
                  <Button variant='danger' onClick={()=>{deleteProduct(product.id)}}>Delete</Button>
                </Card.Body>
              </Card>
            )
        })
    }
    </Row>
   </Container>
    </div>
  )
}

export default ViewProducts
