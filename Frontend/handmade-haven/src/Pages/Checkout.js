// src/pages/Checkout.js
import React, { useState } from 'react';
import { placeOrder } from '../Services/api';
import { useNavigate } from 'react-router-dom';

export default function Checkout() {
  const [address, setAddress] = useState('');
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();

  const handlePlaceOrder = async () => {
    if (!address.trim()) {
      alert('Please enter your address.');
      return;
    }

    setLoading(true);
    try {
      await placeOrder(address);
      alert('Order placed successfully!');
      navigate('/dashboard'); // redirect to user dashboard
    } catch {
      alert('Failed to place order.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="max-w-xl mx-auto p-4">
      <h2 className="text-2xl font-bold mb-4">Checkout</h2>

      <label className="block mb-2 font-medium">Shipping Address</label>
      <textarea
        value={address}
        onChange={(e) => setAddress(e.target.value)}
        className="w-full p-2 border rounded h-24"
        placeholder="Enter your full address"
      />

      <button
        onClick={handlePlaceOrder}
        disabled={loading}
        className="mt-4 px-6 py-2 bg-pink-500 text-white rounded hover:bg-pink-600 disabled:opacity-50"
      >
        {loading ? 'Placing Order...' : 'Place Order'}
      </button>
    </div>
  );
}
