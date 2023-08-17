import '../styles/UserHome.css'
import { Route, Routes } from 'react-router-dom';
import UserDashboard from './UserDashboard';
import Navbar from './Navbar';
import Footer from './Footer';

import EditUser from './EditUser';
import ViewOnCategory from './ViewOnCategory';
import UserViewAllProducts from './UserViewAllProducts';
import Cart from './Cart';
import WishList from './WishList';

function UserHome() {
  return (
    <div>
      <Navbar></Navbar>
   <Routes>
    <Route path='/' Component={UserDashboard}></Route>
    <Route path='/edituser' element={<EditUser></EditUser>}></Route>
    <Route path='/viewoncategory/:category' element={<ViewOnCategory/>}></Route>
    <Route path='/userviewallproducts' element={<UserViewAllProducts/>}></Route>
    <Route path='/cart' element={<Cart/>}></Route>
    <Route path='/wishlist' element={<WishList/>}></Route>
   </Routes>
   <Footer/>
    </div>
  )
}

export default UserHome


