import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import Navigation from './ui/nav/Navigation';
import Home from './ui/home-page/index';
import OwnerListings from './ui/owners-page/OwnerListings';
import RenterRequestedListings from './ui/renters-page/RenterRequestedListings';
import LogoutButton from './ui/LogoutButton';
import ListingsContainer from './components/listings/ListingsContainer';
import RegisterLogin from './ui/register-login-page/RegisterLogin';

function App() {
  return (
    <>
      <BrowserRouter>
      <Navigation />
      {localStorage.hasOwnProperty("user") ? <LogoutButton /> : <></>}
      
      <Routes>
        <Route path='/' element={<Home />} />

        {(localStorage.hasOwnProperty("role") && localStorage.getItem("role") === "owner") && <Route path="/listings" element={<OwnerListings />} />}

        {(localStorage.hasOwnProperty("role") && localStorage.getItem("role") === "renter") && <Route path="/listings" element={<RenterRequestedListings />} />}
        
        {/* show register if no user logged in */}
        {!localStorage.hasOwnProperty("user") && <Route path='/register-login' element={<RegisterLogin />} />}
      
        <Route path='*' element={<h1>404 Not Found</h1>}></Route>
      </Routes>
      </BrowserRouter>
      <ListingsContainer />
    </>
  )
}

export default App;
