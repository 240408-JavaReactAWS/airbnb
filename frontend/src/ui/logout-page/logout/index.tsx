// import LogoutForm from "./LogoutForm"

import { useParams } from "react-router-dom";

function LogoutPage() {
  const {id} = useParams();
  console.log(id)
  return (
    <div>
      <h1>Logout</h1>
      {/* <LogoutForm /> */}
    </div>
  )
}

export default LogoutPage