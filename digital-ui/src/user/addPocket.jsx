import React, { useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import axios from "axios";

export const AddPocket = () => {
  const location = useLocation();
  const navigate = useNavigate();

  const user = location.state?.user;

  const [selectedAccount, setSelectedAccount] = useState("");
  const [amount, setAmount] = useState("");
  const [pin, setPin] = useState("");

  const bank = user?.bank || [];

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

    if (amount <= 0) {
      alert("Amount must be greater than zero.");
      return;
    }

    if (!pin) {
      alert("Please enter your PIN.");
      return;
    }

    // 👉 Find selected account
    const account = bank.find(acc => acc.accountNo === selectedAccount);

    // 👉 Validate PIN
    if (!account || account.pin !== pin) {
      alert("Invalid PIN");
      return;
    }

    try {
      await axios.post("http://localhost:8080/transaction/send", {
        senderId: selectedAccount,   // from selected bank
        accountNo: user.userId,      // to pocket/user
        amount: amount,
      });

      alert("Transaction Successful");
    } catch (err) {
      console.error(err);
      alert("Transaction Failed");
    }
  };

  return (
    <>
      <h2>Add money to pocket</h2>

      {/* ✅ Select Bank Account */}
      <select onChange={(e) => setSelectedAccount(e.target.value)}>
        <option value="">Select Account</option>
        {bank.map((acc) => (
          <option key={acc.accountNo} value={acc.accountNo}>
            {acc.accountNo} - {acc.bankName}
          </option>
        ))}
      </select>

      {/* ✅ Amount */}
      <input
        placeholder="Amount"
        type="number"
        onChange={(e) => setAmount(e.target.value)}
      />

      {/* ✅ PIN */}
      <input
        type="password"
        placeholder="PIN"
        onChange={(e) => setPin(e.target.value)}
      />

      <button onClick={handleSend}>Add Money</button>

      <button onClick={() => navigate("/home", { state: { user } })}>
        Back
      </button>
    </>
  );
};