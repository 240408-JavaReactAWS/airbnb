import { useState } from "react"
import axios from "axios"
import { useNavigate } from "react-router-dom";

function LoginForm() {
    const navigate = useNavigate();
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [showError, setShowError] = useState(false);

    const handleLogin = async (event: any) => {
        event.preventDefault()
        try {
            let res = await axios.post("http://localhost:8080/renters/login", {
                username,
                password
            }, {
                withCredentials: true
            });
            // Handle successful login
            console.log(res);
            if (res.status === 200) {
                // TODO: handle successful login
                // redirect user?
                console.log('Login successful!')
            }

        } catch (error) {
            setShowError(true);
        }
        setPassword('')
        setUsername('')
        setShowError(false)
    }

    return (
        <div>
            <form onSubmit={handleLogin}>
                <label htmlFor="username">Username: </label>< br/>
                <input
                    id="username"
                    type="text"
                    value={username}
                    onChange={({ target }) => setUsername(target.value)}
                />< br/>
                <label htmlFor="password">Password: </label>< br/>
                <input
                    id="password"
                    type="password"
                    value={password}
                  onChange={({ target }) => setPassword(target.value)}
                />< br/>
                <button type="submit">Login</button>
            </form>
            {showError ? <p className='error-message'>Username or password incorrect!</p> : null}
        </div>
    )
}
  
export default LoginForm