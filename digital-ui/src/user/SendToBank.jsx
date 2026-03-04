import React, { useState } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";

export const SendToBank = () => {
  const location = useLocation();
  const navigate = useNavigate();

  // ✅ fallback for refresh
  const user =
    location.state?.user ;

  const bank = user?.bank || [];

  const [fromAccount, setFromAccount] = useState(""); // your bank
  const [toAccount, setToAccount] = useState("");     // receiver bank
  const [amount, setAmount] = useState("");
  const [pin, setPin] = useState("");

  const handleSend = async () => {
    if (!user) {
      alert("User not found. Please login again.");
      navigate("/");
      return;
    }

    if (!fromAccount) {
      alert("Please select your account");
      return;
    }

    if (!toAccount) {
      alert("Enter receiver account number");
      return;
    }

    if (Number(amount) <= 0) {
      alert("Amount must be greater than zero.");
      return;
    }

    if (!pin) {
      alert("Please enter your PIN.");
      return;
    }

    // 👉 Find selected account
    const account = bank.find(acc => acc.accountNo === fromAccount);

    // 👉 Validate PIN
    if (!account || account.pin !== pin) {
      alert("Invalid PIN");
      return;
    }

    try {
      await axios.post("http://localhost:8080/transaction/send", {
        senderId: fromAccount,
        accountNo: toAccount,
        amount: Number(amount),
      });

      alert("Transaction Successful");

      // optional reset
      setAmount("");
      setPin("");
      setToAccount("");

    } catch (err) {
      console.error(err);
      alert("Transaction Failed");
    }
  };

  return (
    <>
      <h2>Send To Bank</h2>

      {/* ✅ Select Your Account */}
      <select onChange={(e) => setFromAccount(e.target.value)}>
        <option value="">Select Your Account</option>
        {bank.map((acc) => (
          <option key={acc.accountNo} value={acc.accountNo}>
            {acc.accountNo} - {acc.bankName}
          </option>
        ))}
      </select>

      {/* ✅ Receiver Account */}
      <input
        type="text"
        placeholder="Receiver Account Number"
        onChange={(e) => setToAccount(e.target.value)}
      />

      {/* ✅ Amount */}
      <input
        type="number"
        placeholder="Amount to Send"
        onChange={(e) => setAmount(e.target.value)}
      />

      {/* ✅ PIN */}
      <input
        type="password"
        placeholder="PIN"
        onChange={(e) => setPin(e.target.value)}
      />

      <button onClick={handleSend}>Send</button>

      <button onClick={() => navigate("/home", { state: { user } })}>
        Back
      </button>
    </>
  );
};