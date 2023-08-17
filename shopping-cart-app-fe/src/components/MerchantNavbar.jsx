import React, { useEffect, useRef, useState } from 'react'
import "../styles/Navbar.css"
import { Link, useNavigate } from 'react-router-dom'
import logo from "../Images/logo.png"


function MerchantNavbar() {
  let navigate=useNavigate();
  let merchant=JSON.parse(localStorage.getItem("merchant"))
  const [sliderOpen, setSliderOpen] = useState(false);
  const sliderRef = useRef(null)
  const toggleSlider = () => {
    setSliderOpen(!sliderOpen);
  }
 
  let handleEdit=()=>{
    setSliderOpen(false);
    navigate('/merchanthome/editmerchant')
  }
  const closeSlider = () => {
    setSliderOpen(false);
  }
  useEffect(() => {
    const handleClickOutside = (event) => {
      if (sliderRef.current && !sliderRef.current.contains(event.target)) {
        closeSlider();
      }
    };

    document.addEventListener('click', handleClickOutside);

    return () => {
     document.removeEventListener('click', handleClickOutside);
    };
  }, []);
  return (
    <div>
    <nav>
 <div>
 <img src={logo} className="logo" />
 </div>
 <div>
 <Link to={`/merchanthome/products`} className="additems">Products |</Link>
   <Link to={`/merchanthome/additems`} className="additems">Add Products |</Link>
   <Link class="profile" onClick={toggleSlider} ref={sliderRef}><i className="fa-solid fa-user"></i></Link>
   <input type="text" className="search-bar" placeholder="Search"/>
   <button className='btn btn-outline-success' id='button'>Search</button>
 </div>
 <div className={`slider ${sliderOpen ? 'open' : ''}`}>
        <div className="profile-info">
          <h2><img className='image' src="https://cdn-icons-png.flaticon.com/512/634/634012.png?w=740&t=st=1691601925~exp=1691602525~hmac=2f4021e786cb2d3db61d4091d1020d63cbf44e97c70c14fdb09e818f9d02e129" alt="" /></h2>
          <strong style={{ color:"white",marginLeft:"75px"}}> {merchant.name}</strong>
          <p style={{color:"white"}}>Phone: {merchant.phone}</p>
          <p style={{color:"white"}}>Email: {merchant.email}</p>
          <p style={{color:"white"}}>GST:  {merchant.gst}</p>
        </div>
        <div className="edit-profile">
          <button onClick={handleEdit} style={{backgroundColor:"white",marginLeft:"71px"}}>Edit Profile</button>
        </div>
      </div>
</nav>


 </div>
  )
}

export default MerchantNavbar
