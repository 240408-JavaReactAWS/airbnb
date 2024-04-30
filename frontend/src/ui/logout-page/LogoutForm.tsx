// import { useState } from "react"
// import axios from "axios"
// import { useNavigate } from "react-router-dom";

function LogoutForm() {
//     const navigate = useNavigate();
//     const [username, setUsername] = useState('')
//     const [password, setPassword] = useState('')
//     const [showError, setShowError] = useState(false);

//     // returned obj has listings property -> Owner
//     const handleLogout = async (event: any) => {
//         console.log(event)
//         // TODO: validate no one logged in
//         // user is a renter

//         let uri
//         if (event.target[2].checked) {
//             uri = "http://localhost:8080/renters/logout"
//         // user is an owner
//         } else {
//             uri = "http://localhost:8080/owners/logout"
//         }
//         try {
//             let res = await axios.post(uri, {
//                 username,
//                 password
//             }, {
//                 withCredentials: true
//             });
//             // Handle successful login
//             console.log(res);
//             if (res.status === 200) {
//                 // TODO: handle successful login
//                 // redirect user?
//                 // user is a renter
//                 if (uri === "http://localhost:8080/renters/login") {
//                     localStorage.setItem("role", "renter")
//                     console.log('Login successful!')
//                 // user is an owner
//                 } else {
//                     localStorage.setItem("role", "owner")
//                 }

//             }

//         } catch (error) {
//             setShowError(true);
//         }
//         setPassword('')
//         setUsername('')
//         setShowError(false)
//     }

//     return (
//         <button onClick={handleLogout}>Logout</button>
//     )
}
  
export default LogoutForm