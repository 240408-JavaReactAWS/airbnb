import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Navigation from './ui/nav/Navigation';
import Home from './ui/home-page/index';
import LoginPage from './ui/login-page';
import RegisterPage from './ui/register-page';
import OwnerListings from './ui/owners-page/OwnerListings';
import RenterRequestedListings from './ui/renters-page/RenterRequestedListings';
import LogoutButton from './ui/logout-page/LogoutButton';

function App() {
  return (
    <>
      {localStorage.hasOwnProperty("user") ? <LogoutButton /> : <></>}
      <BrowserRouter>
        <Navigation />
        <Routes>
          <Route path='/' element={<Home />} />

          {(localStorage.hasOwnProperty("role") && localStorage.getItem("role") === "owner") && <Route path="/listings" element={<OwnerListings />} />}

          {(localStorage.hasOwnProperty("role") && localStorage.getItem("role") === "renter") && <Route path="/listings" element={<RenterRequestedListings />} />}
          
          {/* show register if no user logged in */}
          {!localStorage.hasOwnProperty("user") && <Route path='/register' element={<RegisterPage />} />}

          {/* create login path when no user logged in */}
          {!localStorage.hasOwnProperty("user") && <Route path='/login' element={<LoginPage />} />}
        
          <Route path='*' element={<h1>404 Not Found</h1>}></Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
