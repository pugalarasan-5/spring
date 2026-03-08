import { useLocation, useNavigate } from "react-router-dom";
import "./home.css";
const Home = () => {
  const location = useLocation();

  const user = location.state?.user.data || (JSON.parse(localStorage.getItem("user")).data) ;
  console.log("User on Home:", user);
  const navigate = useNavigate();
  const back=()=>{
    navigate("/")
    JSON.parse(localStorage.getItem("user")) && localStorage.removeItem("user");
  }
  const goToSendMoney = () => {
    navigate("/send", { state: { user } });
  }
  const goToViewBalance = () => {
    navigate("/view-balance", { state: { user } });
  }
  const goToTransactionHistory = () => {
    navigate("/transaction-history", { state: { user } });
  }
  const goToCreateBankAccount = () => {
    navigate("/create-account", { state: { user } });
  }

  return (
  <div className="home-container">

    <h1>Welcome {user.userName}</h1>

    <p className="wallet">Wallet: ₹{user.walletBalance}</p>

    <button className="send-btn" onClick={goToSendMoney}>
      Send Money
    </button>

    <button className="balance-btn" onClick={goToViewBalance}>
      View Balance
    </button>

    <button className="history-btn" onClick={goToTransactionHistory}>
      Transaction History
    </button>

    <button className="create-btn" onClick={goToCreateBankAccount}>
      Create New Bank Account
    </button>

    <button className="logout-btn" onClick={back}>
      Logout
    </button>

  </div>
);
};

export default Home;