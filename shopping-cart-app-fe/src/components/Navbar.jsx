import React, { useEffect, useRef, useState } from 'react'
import "../styles/Navbar.css"
import logo from "../Images/logo.png"
import { Link, useNavigate } from 'react-router-dom'
function Navbar() {
  let navigate=useNavigate();
  let user=JSON.parse(localStorage.getItem("user"))
  const [sliderOpen, setSliderOpen] = useState(false);
  const sliderRef = useRef(null)
  const toggleSlider = () => {
    setSliderOpen(!sliderOpen);
  }
 
  let handleEdit=()=>{
    setSliderOpen(false);
    navigate('/userhome/edituser')
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

let handlelogout=()=>{
  localStorage.clear();
  navigate('/userlogin');
}

  return (
    <div>
       <nav>
    <div>
    <img src={logo} className="logo" />
    </div>
    <div>
    <Link to='/userhome/cart' className="wishlist" >Cart |</Link>
      <Link to='/userhome/wishlist' className="wishlist">Wishlist |</Link>
      <Link className="profile profile-option" onClick={toggleSlider} ref={sliderRef}><i className="fa-solid fa-user"></i></Link>
      <input type="text" className="search-bar" placeholder="Search"/>
      <button className='btn btn-outline-success' id='button'>Search</button>
    </div>
    </nav>
      <div className={`slider ${sliderOpen ? 'open' : ''}`}>
        <div className="profile-info">
          <h2><img className='image' src="https://cdn-icons-png.flaticon.com/512/634/634012.png?w=740&t=st=1691601925~exp=1691602525~hmac=2f4021e786cb2d3db61d4091d1020d63cbf44e97c70c14fdb09e818f9d02e129" alt="" /></h2>
          <strong style={{ color:"white",marginLeft:"75px"}}> {user.name}</strong>
          <p style={{color:"white"}}>Phone:{user.phone}</p>
          <p style={{color:"white"}}>Email: {user.email}</p>
        </div>
        <div className="edit-profile">
          <button onClick={handleEdit} style={{backgroundColor:"white",marginLeft:"71px",borderRadius:"10px"}}>Edit Profile</button>
        </div>
        <div>
          <button onClick={handlelogout} className='logout'>logout</button>
        </div>
      </div>
    </div>
  )
}

export default Navbar
