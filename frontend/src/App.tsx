import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
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
  // TODO: encapsulate in single state object
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [currentUser, setCurrentUser] = useState<{} | null>(null); // TODO: leverage IUser + IRenter OR IOwner
  const [role, setRole] = useState<String | null>(null);

  // TODO: lift useEffect to grab current user surrounding App component; useContext?
  useEffect(() => {
    let asyncCall = async () => {
      let validateSession = await validateLogin.validateSession();
      if (validateSession) {
        setIsLoggedIn(true);
        let user = localStorage.getItem("user")
        if (user) {
          setCurrentUser(JSON.parse(user))
          setRole(localStorage.getItem("role"))
          setIsLoggedIn(true)
        }
      } else {
        setIsLoggedIn(false)
      }
    }
    asyncCall();
  }, []); // TODO: add dependencies such that app properly re-renders post user login

  return (
    <>
      <Router>
        <Navigation />
        {currentUser ? <LogoutButton /> : <></>}
        
        <Routes>
          <Route path="/" element={<Home />} />
          {currentUser && <Route path="/listings" element={ role === "owner" ? <OwnerListings /> : <RenterRequestedListings />} />} {/* TODO: understand why no re-render post user logout */}
          <Route path="*" element={<h1>404 Not Found</h1>} />
        </Routes>
      </Router>
      <ListingsContainer />
    </>
  )
}

export default App;
