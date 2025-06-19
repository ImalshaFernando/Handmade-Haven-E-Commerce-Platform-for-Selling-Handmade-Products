// src/pages/Cart.js
import React, { useEffect, useState } from 'react';
import { fetchCart, updateCartItem, removeCartItem } from '../Services/api';

export default function Cart() {
  const [cartItems, setCartItems] = useState([]);

  const loadCart = () => {
    fetchCart().then((res) => setCartItems(res.data));
  };

  useEffect(() => {
    loadCart();
  }, []);

  const handleQuantityChange = async (id, quantity) => {
    if (quantity < 1) return;
    try {
      await updateCartItem(id, quantity);
      loadCart();
    } catch {
      alert('Failed to update item.');
    }
  };

  const handleRemove = async (id) => {
    try {
      await removeCartItem(id);
      loadCart();
    } catch {
      alert('Failed to remove item.');
    }
  };

  const total = cartItems.reduce((acc, item) => acc + item.product.price * item.quantity, 0);

  return (
    <div className="p-4">
      <h2 className="text-2xl font-bold mb-4">Your Cart</h2>

      {cartItems.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <>
          <div className="space-y-4">
            {cartItems.map(({ product, quantity }) => (
              <div key={product.id} className="flex items-center justify-between p-4 border rounded">
                <div>
                  <h3 className="font-semibold">{product.name}</h3>
                  <p>${product.price}</p>
                </div>
                <div className="flex items-center gap-2">
                  <input
                    type="number"
                    min="1"
                    value={quantity}
                    onChange={(e) => handleQuantityChange(product.id, parseInt(e.target.value))}
                    className="w-16 p-1 border rounded"
                  />
                  <button
                    onClick={() => handleRemove(product.id)}
                    className="text-red-600 hover:underline"
                  >
                    Remove
                  </button>
                </div>
              </div>
            ))}
          </div>

          <div className="mt-6 text-right">
            <p className="text-lg font-bold">Total: ${total.toFixed(2)}</p>
            <a
              href="/checkout"
              className="inline-block mt-2 px-6 py-2 bg-pink-500 text-white rounded hover:bg-pink-600"
            >
              Proceed to Checkout
            </a>
          </div>
        </>
      )}
    </div>
  );
}
