// src/pages/Products.js
import React, { useEffect, useState } from 'react';
import { fetchProducts, addToCart } from '../Services/api';

const categories = ['All', 'Resin Items', 'Flower Bouquets', 'Wishing Cards', 'Handmade Jewellery', 'Gift Bags', 'Bags'];

export default function Products() {
  const [products, setProducts] = useState([]);
  const [filterCategory, setFilterCategory] = useState('All');
  const [maxPrice, setMaxPrice] = useState(10000);

  useEffect(() => {
    fetchProducts().then((res) => setProducts(res.data));
  }, []);

  const filtered = products.filter((p) => {
    const matchCategory = filterCategory === 'All' || p.category === filterCategory;
    const matchPrice = p.price <= maxPrice;
    return matchCategory && matchPrice;
  });

  const handleAddToCart = async (id) => {
    try {
      await addToCart(id);
      alert('Item added to cart!');
    } catch (err) {
      alert('Error adding to cart');
    }
  };

  return (
    <div className="p-4">
      <h2 className="text-2xl font-bold mb-4">Browse Products</h2>

      <div className="mb-4 flex flex-col md:flex-row gap-4">
        <select
          onChange={(e) => setFilterCategory(e.target.value)}
          className="p-2 border rounded"
        >
          {categories.map((cat) => (
            <option key={cat}>{cat}</option>
          ))}
        </select>

        <input
          type="range"
          min="0"
          max="10000"
          step="100"
          value={maxPrice}
          onChange={(e) => setMaxPrice(e.target.value)}
          className="w-full md:w-1/2"
        />
        <span>Max Price: ${maxPrice}</span>
      </div>

      <div className="grid sm:grid-cols-2 md:grid-cols-3 gap-6">
        {filtered.map((product) => (
          <div key={product.id} className="border p-4 rounded shadow hover:shadow-md transition">
            <img src={product.imageUrl} alt={product.name} className="h-40 w-full object-cover mb-2 rounded" />
            <h3 className="text-lg font-semibold">{product.name}</h3>
            <p className="text-sm text-gray-600">{product.description}</p>
            <p className="text-pink-700 font-bold mt-1">${product.price}</p>
            <button
              onClick={() => handleAddToCart(product.id)}
              className="mt-2 px-4 py-2 bg-pink-500 text-white rounded hover:bg-pink-600"
            >
              Add to Cart
            </button>
          </div>
        ))}
      </div>
    </div>
  );
}
