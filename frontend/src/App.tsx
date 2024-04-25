import { BrowserRouter, Route, Routes } from 'react-router-dom';
import './App.css';
import OwnersPage from './ui/owners-page/index';
import RentersPage from './ui/renters-page';
import Navigation from './ui/nav/Navigation';
import Home from './ui/home-page/index';

function App() {
  return (
    <>
      <BrowserRouter>
        <Navigation />
        <Routes>
          <Route path='/' element={<Home />} />
          <Route path='/owners' element={<OwnersPage />} />
          <Route path='/renters' element={<RentersPage />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
