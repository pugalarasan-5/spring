import { useState } from "react";
import { useNavigate, useLocation } from "react-router-dom";
import axios from "axios";
import "./sendMoney.css";

function SendMoney() {

  const [toUserId, setToUserId] = useState("");
  const [amount, setAmount] = useState("");

  const navigate = useNavigate();
  const location = useLocation();

  // Get logged user
  const user =
    location.state?.user ||
    JSON.parse(localStorage.getItem("user") || "null");
    
    //re-store after user get 
    
    const userId=user.id;
    console.log(typeof userId)


 

  const handleSend = async () => {
    try {
      console.log( user.id)
      if (!user) {
        alert("User not found. Please login again.");
        navigate("/");
        return;
      }

      if (!toUserId || !amount) {
        alert("Please enter Receiver ID and Amount");
        return;
      }

       let res=await axios.put(
        `https://spring-1-3l6v.onrender.com/user/sendMoney?senderId=${user.id}&receiverId=${toUserId}&amount=${amount}`
      );

      alert("Transaction Successful");
      

      // clear inputs
      localStorage.setItem("user", JSON.stringify(res.data));
      

    } catch (err) {
      console.log(err);
      alert("Transaction Failed");
    }
  };

 return (
  <div className="send-container">

    <h2>Send Money</h2>

    <input
      type="number"
      placeholder="Enter Receiver User ID"
      value={toUserId}
      onChange={(e) => setToUserId(e.target.value)}
    />

    <input
      type="number"
      placeholder="Enter Amount"
      value={amount}
      onChange={(e) => setAmount(e.target.value)}
    />

    <button onClick={handleSend}>Send</button>

    <button onClick={() => navigate("/add_pocket")}>
      Add Money To Wallet
    </button>

    <button onClick={() => navigate("/send-to-bank")}>
      Send Money To Bank
    </button>

    <button onClick={() => navigate("/home")}>
      Back
    </button>

  </div>
);
}

export default SendMoney;