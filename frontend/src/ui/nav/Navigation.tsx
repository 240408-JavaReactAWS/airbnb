import { Link } from 'react-router-dom'
import './Navigation.css'

function Navigation() {
  return (
    <nav>
        <ul>
            <li><Link to="/">Home</Link></li>
            <li><Link to="/owners">Owners</Link></li>
            <li><Link to="/renters">Renters</Link></li>
            <li><Link to="/register">Register</Link></li>
            <li><Link to="/login">Login</Link></li>
        </ul>
    </nav>
  )
}

export default Navigation