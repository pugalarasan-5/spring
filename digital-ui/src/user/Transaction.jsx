import React, { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "./transaction.css";

function TransactionHistory() {
  const navigate = useNavigate();
  const [transactions, setTransactions] = useState([]);

  useEffect(() => {
    axios
      .get("http://localhost:8080/transaction/ByUser?id=" + JSON.parse(localStorage.getItem("user")).data.id)
      .then((res) => {

        // Sort by date (latest first)
        const sorted = res.data.sort(
          (a, b) => new Date(b.date) - new Date(a.date)
        );

        setTransactions(sorted);
      })
      .catch((err) => console.log(err));
  }, []);

  return (
  <div className="history-container">

    <h2>Transaction History</h2>

    <table>
      <thead>
        <tr>
          
          <th>Amount</th>
          <th>Bank</th>
          <th>Type</th>
          <th>Date</th>
        </tr>
      </thead>

      <tbody>
        {transactions.map((t) => (
          <tr key={t.id}>
            
            <td>{t.amount}</td>
            <td>{t.bankName}</td>
            <td>{t.type}</td>
            <td>{new Date(t.date).toLocaleString()}</td>
          </tr>
        ))}
      </tbody>
    </table>

    <button onClick={() => navigate("/home")}>
      Back to Home
    </button>

  </div>
);
}

export default TransactionHistory;