// src/services/api.js
import axios from 'axios';

const API_BASE = 'http://localhost:8080/api'; // update to your Spring Boot base URL

export const fetchProducts = () => axios.get(`${API_BASE}/products`);
export const addToCart = (productId, quantity = 1) =>
  axios.post(`${API_BASE}/cart/add`, { productId, quantity });

export const fetchCart = () => axios.get(`${API_BASE}/cart`);
export const updateCartItem = (productId, quantity) =>
  axios.put(`${API_BASE}/cart/update`, { productId, quantity });
export const removeCartItem = (productId) =>
  axios.delete(`${API_BASE}/cart/remove/${productId}`);

export const placeOrder = (address) =>
axios.post(`${API_BASE}/orders`, { address });

export const registerUser = (user) => axios.post(`${API_BASE}/auth/register`, user);
export const loginUser = (credentials) => axios.post(`${API_BASE}/auth/login`, credentials);

// Add auth token to all requests
axios.interceptors.request.use((config) => {
  const token = localStorage.getItem('token');
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});


export const fetchUserProfile = () => axios.get(`${API_BASE}/users/me`);
export const fetchUserOrders = () => axios.get(`${API_BASE}/orders/me`);
