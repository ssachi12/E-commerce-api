import axios from 'axios'
import React, { useEffect, useState } from 'react'
import '../styles/userDashboard.css'
import { useNavigate } from 'react-router-dom'
function UserDashboard() {
let [select,setSelect]=useState("")
let navigate=useNavigate();
 let handleClick=(e)=>{
  e.preventDefault();
     navigate(`/userhome/viewoncategory/${select}`)
 }
 let tour=()=>{
  navigate("/userhome/userviewallproducts")
 }
  return (
   <div className='dash'>
     <div className='outboard'>
<div className='block'>
<button className="card card1" value="Mobile" onMouseEnter={(e)=>{setSelect(e.target.value)}} onClick={handleClick} ></button>
<button className="card card2"  value="clothings" onMouseEnter={(e)=>{setSelect(e.target.value)}} onClick={handleClick}></button>
<button className="card card3"  value="Accessaries" onMouseEnter={(e)=>{setSelect(e.target.value)}} onClick={handleClick}></button>
<button className="card card4"  value="Food and groceries" onMouseEnter={(e)=>{setSelect(e.target.value)}} onClick={handleClick}></button>
</div>
</div>
<div className='bottampanel' >
<button onClick={tour} >TAKE A RANDAM TOUR</button>
</div>
   </div>
   
  )
}

export default UserDashboard
