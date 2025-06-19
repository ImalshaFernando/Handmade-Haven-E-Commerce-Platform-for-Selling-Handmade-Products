// src/pages/Dashboard.js
import React, { useEffect, useState } from 'react';
import { fetchUserProfile, fetchUserOrders } from '../Services/api';

export default function Dashboard() {
  const [user, setUser] = useState(null);
  const [orders, setOrders] = useState([]);

  useEffect(() => {
    fetchUserProfile().then((res) => setUser(res.data)).catch(() => alert("Error loading user profile"));
    fetchUserOrders().then((res) => setOrders(res.data)).catch(() => alert("Error loading orders"));
  }, []);

  return (
    <div className="p-4 max-w-4xl mx-auto">
      <h2 className="text-2xl font-bold mb-4">User Dashboard</h2>

      {user && (
        <div className="mb-6 bg-gray-100 p-4 rounded shadow">
          <h3 className="text-lg font-semibold">👤 Profile</h3>
          <p>Name: {user.name}</p>
          <p>Email: {user.email}</p>
        </div>
      )}

      <div className="bg-gray-100 p-4 rounded shadow">
        <h3 className="text-lg font-semibold mb-2">🧾 Order History</h3>
        {orders.length === 0 ? (
          <p>No past orders yet.</p>
        ) : (
          <ul className="space-y-4">
            {orders.map((order) => (
              <li key={order.id} className="border-b pb-2">
                <p className="font-semibold">Order ID: {order.id}</p>
                <p>Date: {new Date(order.date).toLocaleDateString()}</p>
                <p>Total: ${order.total}</p>
                <ul className="ml-4 mt-1 text-sm text-gray-600">
                  {order.items.map((item) => (
                    <li key={item.productId}>- {item.productName} × {item.quantity}</li>
                  ))}
                </ul>
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
}
