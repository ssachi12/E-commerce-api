import React from 'react'
import { Navigate } from 'react-router-dom';

function Protect({Child} ) {
    let verify=()=>{
        let x=localStorage.getItem("merchant");
        if(x==null){
            return false;
        }else{
            return true;
        }
    }
  return (
    <div>
      {verify() ? <Child/> :<Navigate to="/merchantlogin" />}
    </div>
  )
}

export default Protect
