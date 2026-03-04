import { useLocation, useNavigate } from "react-router-dom";

const Home = () => {
  const location = useLocation();
  const user = location.state?.user.data;
  console.log("User on Home:", user);
  const navigate = useNavigate();
  const back=()=>{
    navigate("/login")
  }
  const goToSendMoney = () => {
    navigate("/send", { state: { user } });
  }
  const goToViewBalance = () => {
    navigate("/view_balance", { state: { user } });
  }
  const goToTransactionHistory = () => {
    navigate("/transaction_history", { state: { user } });
  }
  const goToCreateBankAccount = () => {
    navigate("/create_bank_account", { state: { user } });
  }

  return (
    <div>
      <h1>Welcome {user.userName}</h1>
      <p>Wallet: ₹{user.walletBalance}</p>


      <button onClick={goToSendMoney}>
        Send Money
      </button>
      <button onClick={goToViewBalance}>View Balance</button>
      <button onClick={goToTransactionHistory}>Transaction History</button>
      <button onClick={goToCreateBankAccount}>create new bank account</button>
      <button onClick={back}>Logout</button>
    </div>
  );
};

export default Home;