import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import LandingPage from './components/LandingPage'
import UserLogin from './components/UserLogin'
import MerchantLogin from './components/MerchantLogin'
import 'bootstrap/dist/css/bootstrap.min.css';
import MerchantRegister from './components/MerchantRegister'
import UserRegister from './components/UserRegister'
import Error from './components/Error'
import UserHome from './components/UserHome'
import MerchantForgotPassword from './components/MerchantForgotPassword'
import MerchantHome from './components/MerchantHome'
import Protect from './components/Protect'
import UserForgotPassword from './components/UserForgotPassword'
import ProtectUser from './components/ProtectUser'
function App() {
  return (
    <div>
     <BrowserRouter>
     <Routes>
     <Route Component={LandingPage} path='/'></Route>
     <Route Component={UserLogin} path='/userlogin'></Route>
     <Route Component={MerchantLogin} path='/merchantlogin'></Route>
     <Route Component={MerchantRegister} path='/merchantregister'></Route>
     <Route Component={UserRegister} path='/userregister'></Route>
     <Route element={<ProtectUser Child={UserHome}/>} path='/userhome/*'></Route>
     <Route path='/userforgotpassword' Component={UserForgotPassword}></Route>
     <Route path='*' Component={Error}></Route>
     <Route path='/merchantforgotpassword' Component={MerchantForgotPassword}></Route>
     <Route path='/merchanthome/*' element={<Protect Child={MerchantHome}/>}></Route>
     </Routes>
     </BrowserRouter>

    </div>
  )
}

export default App
