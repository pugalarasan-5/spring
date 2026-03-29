import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import "./register.css";

function Register() {
  const [form, setForm] = useState({
    userName: "",
    email: "",
    phone: "",
    password: "",
    walletBalance: 100,
    status: "ACTIVE",
    createdAt: new Date().toISOString()
  });

  const navigate = useNavigate();

  const handleRegister = async () => {
    try {
      const res = await axios.post("https://spring-1-3l6v.onrender.com/user/register", form);

      localStorage.setItem("user", JSON.stringify(res.data));
      navigate("/home", { state: { user: res.data } });

    } catch (err) {
      console.error(err);
      alert("Registration failed");
    }
  };

  return (
  <div className="register-container">
    <h2>Register</h2>

    <input
      placeholder="Name"
      value={form.userName}
      onChange={(e)=>setForm({...form, userName:e.target.value})}
    />

    <input
      type="email"
      placeholder="Enter email"
      value={form.email}
      onChange={(e)=>setForm({...form,email:e.target.value})}
    />

    <input 
      type="text"
      placeholder="Enter phone number"
      value={form.phone}
      onChange={(e)=>setForm({...form,phone:e.target.value})}
    />

    <input
      type="password"
      placeholder="Password"
      value={form.password}
      onChange={(e)=>setForm({...form,password:e.target.value})}
    />

    <button onClick={handleRegister}>Register</button>
  </div>
);
}

export default Register;