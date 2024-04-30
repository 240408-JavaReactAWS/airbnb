import { useState } from "react"
import axios from "axios"
import { useNavigate } from "react-router-dom";

function RegisterForm() {
    const navigate = useNavigate();
    const [username, setUsername] = useState('')
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')
    const [showError, setShowError] = useState(false);

    // owne
    const handleSubmit = async (event: any) => {
        event.preventDefault()
        // user is a renter
        let uri
        if (event.target[3].checked) {
            uri = "http://localhost:8080/renters/register"
        // user is an owner
        } else {
            uri = "http://localhost:8080/owners/register"
        }

        try {
            let res = await axios.post(uri, {
                username,
                email,
                password
            }, {
                withCredentials: true
            });

            // Handle successful login
            console.log(res);
            if (res.status === 201) {
                // TODO: redirect user somewhere
                // navigate('/plans');
            }

        } catch (error) {
            setShowError(true);
        }
        setEmail('')
        setPassword('')
        setUsername('')
        setShowError(false)
    }

    return (
        <>
            <form onSubmit={handleSubmit}>
                <label htmlFor="username">Username: </label>< br/>
                <input
                    id="username"
                    type="text"
                    value={username}
                    placeholder="Enter a username"
                    onChange={({ target }) => setUsername(target.value)}
                />< br/>
                <label htmlFor="email">Email: </label>< br/>
                <input
                    id="email"
                    type="text"
                    value={email}
                    placeholder="Enter your email address"
                    onChange={({ target }) => setEmail(target.value)}
                />< br/>
                <label htmlFor="password">Password: </label>< br/>
                <input
                    id="password"
                    type="password"
                    value={password}
                    placeholder="Enter a strong password"
                  onChange={({ target }) => setPassword(target.value)}
                />< br/>
                <label htmlFor="typeUserRenter">Register as Renter</label>
                <input
                    id="typeUserRenter"
                    type="radio"
                    name="typeUser"
                    value="renter"
                    required
                />< br/>
                <label htmlFor="typeUserOwner">Register as Owner</label>
                <input
                    id="typeUserOwner"
                    type="radio"
                    name="typeUser"
                    value="owner"
                /><br/>
                <button name="renter" type="submit">Register</button>
            </form>
            {showError ? <p className='error-message'>Username or password incorrect!</p> : null}
        </>
    )
}
  
export default RegisterForm