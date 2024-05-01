import { useState } from "react"
import axios from "axios"
import { useNavigate } from "react-router-dom";

function LogoutButton() {
    const navigate = useNavigate();
    const [showError, setShowError] = useState(false);

    // returned obj has listings property -> Owner
    const handleLogout = async (event: any) => {
        // user is a renter
        let uri
        if (localStorage.getItem("role") === "renter"){
            uri = "http://localhost:8080/renters/logout"
        // user is an owner
        } else {
            uri = "http://localhost:8080/owners/logout"
        }
        try {
            let res = await axios.post(uri);
            if (res.status === 200) {
                localStorage.removeItem("user")
                localStorage.removeItem("role")
                console.log("Logged out")
                navigate('/')

            }
        } catch (error) {
            setShowError(true);
        }
        setShowError(false)
    }

    return (
        <button onClick={handleLogout}>Logout</button>
    )
}
export default LogoutButton