import { useState } from "react"
import axios from "axios"

function LoginForm() {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [showError, setShowError] = useState(false);

    // returned obj has listings property -> Owner
    const handleLogin = async (event: any) => {
        event.preventDefault()
        let uri
        // user is a renter
        if (event.target[2].checked) {
            uri = "http://localhost:8080/renters/login"
        // user is an owner
        } else {
            uri = "http://localhost:8080/owners/login"
        }
        try {
            let res = await axios.post(uri, {
                username,
                password
            }, {
                withCredentials: true
            });
            console.log(res);
            // Handle successful login
            if (res.status === 200) {
                localStorage.setItem("user", JSON.stringify(res.data))
                // user is a renter
                if (uri === "http://localhost:8080/renters/login") {
                    localStorage.setItem("role", "renter")
                // user is an owner
                } else {
                    localStorage.setItem("role", "owner")
                }
                console.log("Logged in")
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
                <label htmlFor="typeUserRenter">Login as Renter</label>
                <input
                    id="typeUserRenter"
                    type="radio"
                    name="typeUser"
                    value="renter"
                    required
                />< br/>
                <label htmlFor="typeUserOwner">Login as Owner</label>
                <input
                    id="typeUserOwner"
                    type="radio"
                    name="typeUser"
                    value="owner"
                /><br/>
                <button type="submit">Login</button>
            </form>
            {showError ? <p className='error-message'>Username or password incorrect!</p> : null}
        </div>
    )
}
  
export default LoginForm