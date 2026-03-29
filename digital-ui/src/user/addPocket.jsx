import React, { useState, useEffect } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import axios from "axios";
import "./addPocket.css";
export const AddPocket = () => {

  const location = useLocation();
  const navigate = useNavigate();
  const userE = JSON.parse(localStorage.getItem("user"));

  const user = JSON.parse(localStorage.getItem("user"))?.data || location.state?.res;

  const [banks, setBanks] = useState([]);
  const [selectedAccount, setSelectedAccount] = useState("");
  const [amount, setAmount] = useState("");
  const [pin, setPin] = useState("");
  

  // 🔹 Get bank accounts from API
  useEffect(() => {

    if(!user?.id) return;

    axios
      .get(`https://spring-1-3l6v.onrender.com/bank/getUserId?id=${user.id}`)
      .then((response) => {
        setBanks(response.data.data);
      })
      .catch((err) => console.log(err));

  }, [user?.id]);


  const handleSend = async () => {

    if (!user) {
      alert("User not found. Please login again.");
      navigate("/");
      return;
    }

    if (!selectedAccount) {
      alert("Please select account");
      return;
    }

    if (!amount || amount <= 0) {
      alert("Amount must be greater than zero.");
      return;
    }

    if (!pin) {
      alert("Please enter your PIN.");
      return;
    }

    // 🔹 find selected account
    const account = banks.find(
      (acc) => acc.accountNo === Number(selectedAccount)
    );

    // 🔹 check pin
    if (!account || account.pin !== pin) {
      alert("Invalid PIN");
      return;
    }

    try {

      const response = await axios.post(
        `https://spring-1-3l6v.onrender.com/user/addMoney?userId=${Number(user.id)}&accountNo=${Number(selectedAccount)}&amount=${Number(amount)}`
      );

      
        localStorage.setItem("user", JSON.stringify(response.data));

      alert("Transaction Successful");
      localStorage.setItem("user", JSON.stringify(response.data));

      setAmount("");
      setPin("");

    } catch (err) {
      console.error(err);
      alert("Transaction Failed");
      localStorage.setItem("user", JSON.stringify(userE));
    }
  };


 return (
  <div className="pocket-container">

    <h2>Add Money To Pocket</h2>

    <select onChange={(e) => setSelectedAccount(e.target.value)}>
      <option value="">Select Account</option>

      {banks.map((acc) => (
        <option key={acc.accountNo} value={acc.accountNo}>
          {acc.bankName} - {acc.accountNo}
        </option>
      ))}
    </select>

    <br/><br/>

    <input
      type="number"
      placeholder="Amount"
      value={amount}
      onChange={(e) => setAmount(e.target.value)}
    />

    <br/><br/>

    <input
      type="password"
      placeholder="PIN"
      value={pin}
      onChange={(e) => setPin(e.target.value)}
    />

    <br/><br/>

    <button onClick={handleSend}>Add Money</button>

    <br/><br/>

    <button onClick={() => navigate("/home")}>
      Back
    </button>

  </div>
);
};