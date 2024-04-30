import LogoutButton from "./LogoutButton"
import { useParams } from "react-router-dom";

function LogoutPage() {
  const {id} = useParams();

  // user is not logged in
  if (!localStorage.hasOwnProperty("user")) {
    return (
      <div>
        <h1>Logout</h1>
        <h2>You are not logged in!</h2>
      </div>
    )
  }

  // user is logged in
  return (
    <div>
      <h1>Logout</h1>
      <LogoutButton /> 
    </div>
  )
}

export default LogoutPage