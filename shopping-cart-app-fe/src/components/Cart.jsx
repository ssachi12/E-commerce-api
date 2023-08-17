import React, { useEffect, useState } from 'react'
import "../styles/Cart.css"
import axios from 'axios'
function Cart() {
let user=JSON.parse(localStorage.getItem("user"));
let [products,setProducts]=useState([]);
  useEffect(()=>{
    axios.get(`http://localhost:8080/user/cart/${user.id}`).then((res)=>{
         console.log(res.data);
         setProducts(res.data.body)
    }).catch(()=>{
        console.log("error");
    })
  },[])
  const subtotal = products.reduce((total, product) => total + product.cost, 0);
  const shipping = 50.00; // Sample shipping cost
  const total = subtotal + shipping;
  const discount=total-(12/100*total);
  return (
    <div className="cart-view">
      <div className="product-list">
        {products.map((product) => (
          <div key={product.id} className="product">
            <img src={product.image} alt={product.name} />
            <div className="product-details">
              <h3>{product.name}</h3>
              <p>Price: ₹{product.cost.toFixed(2)}</p>
            </div>
          </div>
        ))}
      </div>
      {/* Cart summary */}
      <div className="cart-summary">
        <p>Subtotal: ₹{subtotal.toFixed(2)}</p>
        <p>Shipping: ₹{shipping.toFixed(2)}</p>
        <p>Total: ₹{total.toFixed(2)}</p>
        <p>Discount: ₹{discount.toFixed(2)}</p>
      </div>
      {/* Checkout button */}
      <button className="checkout-button">Proceed to Checkout</button>
    </div>
  )
}

export default Cart
