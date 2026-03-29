import React, { useState, useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import "./sendToBank.css";

function SendToBank() {

  const location = useLocation();
  const navigate = useNavigate();

  // fallback for refresh
  const user =
    location.state?.user?.data || JSON.parse(localStorage.getItem("user"))?.data;

  const [banks, setBanks] = useState([]);
  const [selectedBank, setSelectedBank] = useState("");
  const [fromAccount, setFromAccount] = useState("");
  const [toAccount, setToAccount] = useState("");
  const [amount, setAmount] = useState("");
  const [pin, setPin] = useState("");
  const [balance, setBalance] = useState(null);
  const [message, setMessage] = useState("");

  // 🔹 Get bank accounts
  useEffect(() => {

    if (!user?.id) return;

    axios
      .get(`https://spring-1-3l6v.onrender.com/bank/getUserId?id=${user.id}`)
      .then((res) => {
        setBanks(res.data.data);
      })
      .catch((err) => console.log(err));

  }, [user?.id]);

  // 🔹 Filter accounts by bank
  const filteredAccounts = banks.filter(
    (acc) => acc.bankName === selectedBank
  );

  // 🔹 When account selected
  const handleAccountChange = (e) => {

    const accNo = Number(e.target.value);
    setFromAccount(accNo);

    const account = banks.find((b) => b.accountNo === accNo);

    if (account) {
      setBalance(account.balance);
    }
  };

  // 🔹 Send money
  const handleSend = async () => {

    if (!selectedBank) {
      alert("Please select bank");
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
      alert("Amount must be greater than zero");
      return;
    }

    if (Number(amount) > balance) {
      alert("Insufficient balance");
      return;
    }

    if (!pin) {
      alert("Enter PIN");
      return;
    }

    const account = banks.find((acc) => acc.accountNo === fromAccount);

    if (!account || account.pin !== pin) {
      alert("Invalid PIN");
      return;
    }

    try {

      await axios.post(`https://spring-1-3l6v.onrender.com/bank/sendMoney?senderAccountNo=${fromAccount}&receiverAccountNo=${Number(toAccount)}&amount=${Number(amount)}`);

      setMessage("✅ Transaction Successful");

      setAmount("");
      setPin("");
      setToAccount("");

    } catch (err) {
      console.error(err);
      setMessage("❌ Transaction Failed");
    }
  };

 return (
  <div className="bank-transfer-container">

    <h2>Send To Bank</h2>

    <select onChange={(e) => setSelectedBank(e.target.value)}>
      <option value="">Select Bank</option>

      {[...new Set(banks.map((b) => b.bankName))].map((name) => (
        <option key={name} value={name}>
          {name}
        </option>
      ))}
    </select>

    <select onChange={handleAccountChange}>
      <option value="">Select Account</option>

      {filteredAccounts.map((acc) => (
        <option key={acc.accountNo} value={acc.accountNo}>
          {acc.accountNo}
        </option>
      ))}
    </select>

    {balance !== null && (
      <p className="balance-text">
        Available Balance: ₹{balance}
      </p>
    )}

    <input
      type="text"
      placeholder="Receiver Account Number"
      value={toAccount}
      onChange={(e) => setToAccount(e.target.value)}
    />

    <input
      type="number"
      placeholder="Amount"
      value={amount}
      onChange={(e) => setAmount(e.target.value)}
    />

    <input
      type="password"
      placeholder="Enter PIN"
      value={pin}
      onChange={(e) => setPin(e.target.value)}
    />

    <button onClick={handleSend}>Send Money</button>

    {message && (
      <p className={message.includes("Successful") ? "success-msg" : "error-msg"}>
        {message}
      </p>
    )}

    <button onClick={() => navigate("/home", { state: { user } })}>
      Back
    </button>

  </div>
);
}

export default SendToBank;