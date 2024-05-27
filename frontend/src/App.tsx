import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { validateLogin } from './shared/utils/ValidateLogin';
import Navigation from './ui/nav/Navigation';
import Home from './ui/home-page/index';
import OwnerListings from './ui/owners-page/OwnerListings';
import RenterRequestedListings from './ui/renters-page/RenterRequestedListings';
import LogoutButton from './ui/LogoutButton';
import ListingsContainer from './components/listings/ListingsContainer';
import './App.css';

function App() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [currentUser, setCurrentUser] = useState();

  useEffect(() => {
    let asyncCall = async () => {
      let validateSession = await validateLogin.validateSession();
      if (validateSession) {
        setIsLoggedIn(true);
        setCurrentUser
      } else {
        setIsLoggedIn(false);
      }
    }
    asyncCall();
  }, [localStorage.getItem("user")]);

  return (
    <>
      <BrowserRouter>
      <Navigation />
      {localStorage.hasOwnProperty("user") ? <LogoutButton /> : <></>}
      
      <Routes>
        <Route path='/' element={<Home />} />

        {(localStorage.hasOwnProperty("role") && localStorage.getItem("role") === "owner") && <Route path="/listings" element={<OwnerListings />} />}

        {(localStorage.hasOwnProperty("role") && localStorage.getItem("role") === "renter") && <Route path="/listings" element={<RenterRequestedListings />} />}
      
        <Route path='*' element={<h1>404 Not Found</h1>}></Route>
      </Routes>
      </BrowserRouter>
      <ListingsContainer />
    </>
  )
}

export default App;
