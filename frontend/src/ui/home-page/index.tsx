import ListingsContainer from "../../components/listings/ListingsContainer";
import LoginForm from "../login/LoginForm";

function Home() {
  return (
    <>
      <h1>Home</h1>
      <LoginForm />
      <ListingsContainer />
    </>
  );
}

export default Home;