import React from 'react';
import { Link } from 'react-router-dom';

export default function Home() {
  return (
    <div className="container mt-4">
      <div className="p-5 mb-4 bg-light rounded-3 shadow-sm">
        <div className="container-fluid py-5">
          <h1 className="display-5 fw-bold text-primary">Welcome to Handmade Haven</h1>
          <p className="col-md-8 fs-4">Explore our collection of handcrafted gifts made with love 💝</p>
          <Link to="/products" className="btn btn-primary btn-lg">Start Shopping</Link>
        </div>
      </div>

      <h3 className="mt-5 mb-3">Categories</h3>
      <div className="row g-3">
        {['Resin Items', 'Flower Bouquets', 'Wishing Cards', 'Handmade Jewellery', 'Gift Bags', 'Bags'].map((cat, i) => (
          <div className="col-md-4" key={i}>
            <div className="card shadow-sm h-100">
              <div className="card-body">
                <h5 className="card-title">{cat}</h5>
                <p className="card-text">Beautifully crafted {cat.toLowerCase()}.</p>
                <Link to={`/products?category=${cat}`} className="btn btn-outline-primary">View</Link>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
}
