import React from 'react'
import { Navigate } from 'react-router-dom';

function ProtectUser({Child}) {
    let verifyUser=()=>{
      let x=  localStorage.getItem("user");
      if(x==null){
        return false;
      }
      else{
        return true;
      }
    }
  return (
    <div>
      {verifyUser() ? <Child/>:<Navigate to='/userlogin'/>}
    </div>
  )
}

export default ProtectUser
