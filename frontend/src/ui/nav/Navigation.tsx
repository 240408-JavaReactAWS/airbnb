import { Link } from 'react-router-dom';
import { useState } from 'react';
import RegisterLogin from '../register-login-page/RegisterLogin';
import './Navigation.css';

function Navigation() {
  const [isOpen, setIsOpen] = useState(false);

  const toggle = () => setIsOpen((prev) => !prev)

  return (
    <nav>
      <ul>
        <li><Link to="/">Home</Link></li>

        {/* show mylistings if logged in user is an owner */}
        {(localStorage.hasOwnProperty("role") && localStorage.getItem("role") === "owner") && <li><Link to="/listings" >My Listings</Link></li>}

        {/* show my-requested-listings if logged in user is a renter */}
        {(localStorage.hasOwnProperty("role") && localStorage.getItem("role") === "renter") && <li><Link to="/listings" >My Requests</Link></li>}
      </ul>
      <button onClick={toggle}>Register or Login</button>
      {isOpen && <RegisterLogin />}
    </nav>
  )
}

export default Navigation