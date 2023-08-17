import axios from 'axios';
import React, { useEffect, useState } from 'react'
import "../styles/ProductView.css"
import { Button, Card, Container, ListGroup, Row } from 'react-bootstrap';
import { toast } from 'react-toastify';
function WishList() {
  let user=JSON.parse(localStorage.getItem("user"));
let [products,setProducts]=useState([]);
let uid = user.id;
  useEffect(()=>{
    axios.get(`http://localhost:8080/user/wish/${user.id}`).then((res)=>{
         console.log(res.data);
         setProducts(res.data.body)
    }).catch(()=>{
        console.log("error");
    })
  },[])
  let handleCart = (pid) => {
    axios.post(`http://localhost:8080/product/cart/${pid}/${uid}`).then(() => {

      toast.success("product added to cart")
    })
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
                <Button variant="primary"  onClick={() => { handleCart(product.id) }} >Add to cart</Button>
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

export default WishList
