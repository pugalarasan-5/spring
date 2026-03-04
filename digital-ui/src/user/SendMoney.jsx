import { useState } from "react";
import { useNavigate,useLocation } from "react-router-dom";
import axios from "axios";

function SendMoney() {
  const [toUserId, setToUserId] = useState("");
  const [amount, setAmount] = useState("");
  const navigate = useNavigate();

  const location = useLocation();
  const user = location.state?.user;
  const [pin, setPin] = useState("");
  const handleSend = async () => {
    try {
      if(!user){
        alert("User not found. Please login again.");
        navigate("/");
        return;
      }
      
    // 👉 Validate PIN
    if (user.pin !== pin) {
      alert("Invalid PIN");
      return;
    }
      await axios.post(`http://localhost:8080/transaction/send?senderId=${user.userId}&receiverId=${toUserId}&amount=${amount}`);

      alert("Transaction Successful");

    // eslint-disable-next-line no-unused-vars
    } catch (err) {
      alert("Transaction Failed");
    }
  };

  return (
    <div>
      <h2>Send Money</h2>
      <input placeholder="To User ID" onChange={(e)=>setToUserId(e.target.value)} />
      <input placeholder="Amount" onChange={(e)=>setAmount(e.target.value)} />
      <input type="password" placeholder="PIN" onChange={(e)=>setPin(e.target.value)} />
      <button onClick={handleSend}>Send</button>
      <button onClick={() => navigate("/add_pocket")}>add money on my pocket</button>
      <button onClick={() => navigate("/send_to_account")}>send money to account</button>
      <button onClick={() => navigate("/home", { state: { user } })}>Back</button>
    </div>
  );
}

export default SendMoney;