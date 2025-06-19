// src/pages/Register.js
import React, { useState, useContext } from 'react';
import { registerUser } from '../Services/api';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthContext';

export default function Register() {
  const [form, setForm] = useState({ name: '', email: '', password: '' });
  const navigate = useNavigate();
  const { login } = useContext(AuthContext);

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const res = await registerUser(form);
      login(res.data.user, res.data.token);
      navigate('/');
    } catch {
      alert('Registration failed.');
    }
  };

  return (
    <div className="max-w-md mx-auto mt-10 p-4 border rounded">
      <h2 className="text-2xl font-bold mb-4">Register</h2>
      <form onSubmit={handleSubmit} className="space-y-3">
        <input name="name" placeholder="Name" onChange={handleChange} required className="w-full border p-2 rounded" />
        <input name="email" type="email" placeholder="Email" onChange={handleChange} required className="w-full border p-2 rounded" />
        <input name="password" type="password" placeholder="Password" onChange={handleChange} required className="w-full border p-2 rounded" />
        <button type="submit" className="w-full bg-pink-500 text-white p-2 rounded">Sign Up</button>
      </form>
    </div>
  );
}
