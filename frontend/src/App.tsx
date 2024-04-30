import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import OwnersPage from './ui/owners-page/index';
import RentersPage from './ui/renters-page';
import Navigation from './ui/nav/Navigation';
import Home from './ui/home-page/index';
import LoginPage from './ui/login-page/login';
import RegisterPage from './ui/register-page/register';

function App() {
  return (
    <>
      <BrowserRouter>
        <Navigation />
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/register' element={<RegisterPage />} />
          <Route path='/login' element={<LoginPage />} />
          <Route path='/owners' element={<OwnersPage />} />
          <Route path='/renters' element={<RentersPage />} />
          <Route path='*' element={<h1>404 Not Found</h1>}></Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
