import { useState } from "react";
import { useNavigate } from "react-router-dom";


export const ViewBalance = () => {
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem("user") || "{}");
  const [balance, setBalance] = useState(0);
  const account=user.bankAccounts;
  let selectedAccountId = document.getElementById("accountType")?.value;

  const handleViewBalance = () => {
    const selectedAccount = account.find(acc => acc.accountId === parseInt(selectedAccountId));
    if (selectedAccount) {
      setBalance(selectedAccount.balance);
    } else {
      alert("Please select a valid account.");
    }
  };


  

  return (
    <>
      <h2>View Balance</h2>
      <select name="accountType" id="accountType" >
        <option value="">Select Account Type</option>
        {account.map((acc) => (
          <option key={acc.accountId} value={acc.name}>
            {acc.accountType} - ₹{acc.balance}
          </option>
        ))}
        
      </select>
      <input type="password" placeholder="Enter your pin"/>
      <button onClick={handleViewBalance}>View Balance</button>
      <p>Current Balance: ₹{balance}</p>
      <button onClick={() => navigate("/home")}>Back to Home</button>
    </>
  )
}
