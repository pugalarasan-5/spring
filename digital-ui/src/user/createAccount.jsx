import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./createAccount.css";
function CreateAccount() {

  const navigate = useNavigate();

  const user = JSON.parse(localStorage.getItem("user"))?.data;

  const [bank, setBank] = useState({
    accountNo: "",
    userName: "",
    bankName: "",
    status: "ACTIVE",
    phone: "",
    email: "",
    address: "",
    balance: "1000",
    createdAt: new Date().toISOString().split("T")[0],
    pin: "",
    userId: user?.id
  });

  const handleChange = (e) => {
    setBank({
      ...bank,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {

      await axios.post("http://localhost:8080/bank", bank);

      alert("Bank Account Created Successfully ✅");

      setBank({
        accountNo: "",
        userName: "",
        bankName: "",
        status: "ACTIVE",
        phone: "",
        email: "",
        address: "",
        balance: "1000",
        createdAt: new Date().toISOString().split("T")[0],
        pin: "",
        userId: user?.id
      });

      navigate("/home");

    } catch (error) {
      console.error(error);
      alert("Failed to create bank account ❌");
    }
  };

  return (
  <div className="account-container">

    <h2>Create Bank Account</h2>

    <form onSubmit={handleSubmit}>

      <input
        type="text"
        name="accountNo"
        placeholder="Account Number"
        value={bank.accountNo}
        onChange={handleChange}
      />

      <input
        type="text"
        name="userName"
        placeholder="User Name"
        value={bank.userName}
        onChange={handleChange}
      />

      <input
        type="text"
        name="bankName"
        placeholder="Bank Name"
        value={bank.bankName}
        onChange={handleChange}
      />

      <input
        type="text"
        name="phone"
        placeholder="Phone"
        value={bank.phone}
        onChange={handleChange}
      />

      <input
        type="email"
        name="email"
        placeholder="Email"
        value={bank.email}
        onChange={handleChange}
      />

      <input
        type="text"
        name="address"
        placeholder="Address"
        value={bank.address}
        onChange={handleChange}
      />

      <input
        type="number"
        name="balance"
        placeholder="Balance"
        value={bank.balance}
        onChange={handleChange}
      />

      <input
        type="password"
        name="pin"
        placeholder="PIN"
        value={bank.pin}
        onChange={handleChange}
      />

      <button type="submit">Create Account</button>

      <button type="button" onClick={() => navigate("/home")}>
        Cancel
      </button>

    </form>

  </div>
);
}

export default CreateAccount;