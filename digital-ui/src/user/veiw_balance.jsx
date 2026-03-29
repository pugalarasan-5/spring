import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./viewBalance.css"

function ViewBalance() {
    const navigate = useNavigate();
    const user= JSON.parse(localStorage.getItem("user"))?.data;
  const [banks, setBanks] = useState([]);
  const [selectedBank, setSelectedBank] = useState(null);
  const [pin, setPin] = useState("");
  const [showDetails, setShowDetails] = useState(false);
  const [error, setError] = useState("");

  useEffect(() => {
    axios
      .get(`https://spring-1-3l6v.onrender.com/bank/getUserId?id=${user?.id}`)
      .then((res) => {
        setBanks(res.data.data);
      })
      .catch((err) => console.log(err));
  }, [user?.id]);

  const handleSelect = (e) => {
    const accountNo = e.target.value;
    const bank = banks.find((b) => b.accountNo == accountNo);
    setSelectedBank(bank);
    setShowDetails(false);
    setError("");
  };

  const checkPin = () => {
    if (!selectedBank) {
      setError("Please select bank");
      return;
    }

    if (pin === selectedBank.pin) {
      setShowDetails(true);
      setError("");
    } else {
      setError("Invalid PIN");
      setShowDetails(false);
    }
  };

  return (
  <div className="balance-container">

    <h2>Select Bank</h2>

    <select onChange={handleSelect}>
      <option value="">Select Bank</option>

      {banks.map((bank) => (
        <option key={bank.accountNo} value={bank.accountNo}>
          {bank.bankName}
        </option>
      ))}
    </select>

    <input
      type="password"
      placeholder="Enter PIN"
      value={pin}
      onChange={(e) => setPin(e.target.value)}
    />

    <button onClick={checkPin}>Check PIN</button>

    <p className="error-text">{error}</p>

    {showDetails && selectedBank && (
      <div className="bank-details">
        <h3>Bank Details</h3>
        <p><b>Account No:</b> {selectedBank.accountNo}</p>
        <p><b>User Name:</b> {selectedBank.userName}</p>
        <p><b>Bank Name:</b> {selectedBank.bankName}</p>
        <p><b>Balance:</b> ₹{selectedBank.balance}</p>
        <p><b>Phone:</b> {selectedBank.phone}</p>
        <p><b>Email:</b> {selectedBank.email}</p>
        <p><b>Address:</b> {selectedBank.address}</p>
      </div>
    )}

    <button onClick={() => navigate("/home")}>
      Back
    </button>

  </div>
);
}

export default ViewBalance;