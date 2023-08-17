import React from 'react'
import { Route, Routes, useParams } from 'react-router-dom'
import MerchantDashBoard from './MerchantDashBoard'
import MerchantNavbar from './MerchantNavbar'
import Footer from './Footer'
import AddItems from './AddItems'
import ViewProducts from './ViewProducts'
import UpdateProduct from './UpdateProduct'
import EditMerchant from './EditMerchant'

function MerchantHome() {

  return (
    <div className='merchanthomepage'>
         <MerchantNavbar/> 
      <Routes>
     <Route path='/' Component={MerchantDashBoard}/>
     <Route path='/additems' element={<AddItems/>}></Route>
     <Route path='/products/*' element={<ViewProducts/>}/>
     <Route path='/products/updateproduct/:pid' Component={UpdateProduct}></Route>
     <Route path='/editmerchant' element={<EditMerchant/>}></Route>
      </Routes>
      <Footer/>
    </div>
  )
}

export default MerchantHome
