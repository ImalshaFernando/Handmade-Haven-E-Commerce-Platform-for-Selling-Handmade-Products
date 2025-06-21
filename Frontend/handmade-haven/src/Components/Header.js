import React from 'react';
import { Link } from 'react-router-dom';

const Header = () => {
  return (
    <header className="bg-success text-white p-3">
      <div className="container d-flex justify-content-between align-items-center">
        <h1 className="h4 m-0">Handmade Haven</h1>
        <nav>
          <Link to="/" className="text-white mx-2">Home</Link>
          <Link to="/products" className="text-white mx-2">Products</Link>
          <Link to="/about" className="text-white mx-2">About</Link>
          <Link to="/profile" className="text-white mx-2">Profile</Link>
        </nav>
      </div>
    </header>
  );
};

export default Header;
