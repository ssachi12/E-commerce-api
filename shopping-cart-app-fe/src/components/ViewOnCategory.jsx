import axios from 'axios';
import React, { useEffect, useState } from 'react'
import { useParams } from 'react-router-dom'
import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import '../styles/viewOnCategory.css'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faHeart } from '@fortawesome/free-solid-svg-icons';
import { ToastContainer, toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
function ViewOnCategory() {
  let { category } = useParams();
  let [product, setProduct] = useState([]);

  let user = JSON.parse(localStorage.getItem("user"))
  let uid = user.id;
  useEffect(() => {
    let fetchData = () => {
      axios.get(`http://localhost:8080/product/category/${category}`).then((res) => {
        console.log(res.data);
        setProduct(res.data.body)
        console.log(product);
      })
    }
    fetchData()
  }, [])
  // eslint-disable-next-line
  let handleCart = (pid) => {
    axios.post(`http://localhost:8080/product/cart/${pid}/${uid}`).then(() => {

      toast.success("product added to cart")
    })
  }
  let handleWish=(pid)=>{
    axios.post(`http://localhost:8080/product/wish/${pid}/${uid}`).then(() => {

      toast.success("product added to wishlist")
    }).catch((error)=>{
      console.log(error);
    })
  }
  return (
    <div className='product-container'>
      {product.map((products) => {
        return (
          <div className='product-item'>
            <Card style={{ width: '18rem' }} >
              <Card.Img variant="top" src={products.image} className='image' />
              <Card.Body>
                <Card.Title>{products.brand} {products.name}</Card.Title>
                <Card.Text>
                  <strike >Price: {products.cost}</strike> <br />
                  <label>Today's special deal</label><br />
                  <h6>Price: ₹ {products.cost - (12 / 100) * products.cost}</h6>
                </Card.Text>
                <div className='button-container'>
                  <Button variant="primary" onClick={() => { handleCart(products.id) }}>Add to cart</Button>
                  <Button variant="light" onClick={()=> {handleWish(products.id)}}>
                  <i class="fa-regular fa-heart" ></i>
                  </Button>
                </div>
              </Card.Body>
            </Card>
          </div>
        )
      })}
       <ToastContainer />
    </div>
  )
}

export default ViewOnCategory
