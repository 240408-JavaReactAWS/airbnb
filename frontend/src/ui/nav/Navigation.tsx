import { Link, useNavigate } from 'react-router-dom'
import './Navigation.css'
import { useEffect, useState } from 'react';
import { validateLogin } from '../../shared/utils/ValidateLogin';

function Navigation() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const navigate = useNavigate();
  const loggedInUser = localStorage.getItem("user");

  useEffect(() => {
      let asyncCall = async () => {
          let validateSession = await validateLogin.validateSession();
          if (validateSession) {
            setIsLoggedIn(true);
          } else{
            setIsLoggedIn(false);
          }
      }
      asyncCall();
  }, [window.location.pathname]);

  return (
    <nav>
        <ul>
            <li><Link to="/">Home</Link></li>
            <li><Link to="/owners">Owners</Link></li>
            <li><Link to="/renters">Renters</Link></li>
            <li><Link to="/register">Register</Link></li>
            <li><Link to="/login">Login</Link></li>
            <li><Link to="/logout">Logout</Link></li>
        </ul>
    </nav>
  )
}

export default Navigation