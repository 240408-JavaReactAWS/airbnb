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
        {<li><Link to="/listings" >{(localStorage.getItem("role") === "owner") ? "My Listings" : "My Requests"}</Link></li>}
      </ul>
      {/* TODO: conditionally render Register or Login */}
      <button onClick={toggle}>Register or Login</button>
      {isOpen && <RegisterLogin />}
    </nav>
  )
}

export default Navigation