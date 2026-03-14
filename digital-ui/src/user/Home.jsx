import { useLocation, useNavigate } from "react-router-dom";
import "./home.css";

const Home = () => {

  const location = useLocation();
  const navigate = useNavigate();

  const user =
    location.state?.user?.data ||
    JSON.parse(localStorage.getItem("user"))?.data;

  console.log("User on Home:", user);

  const logout = () => {
    localStorage.removeItem("user");
    navigate("/digital-ui/");
  };

  return (
    <div className="home-container">

      <div className="card">

        <h1 className="welcome">Welcome {user.userName} 👋<span>{user.status}</span></h1>

        <div className="wallet-card">
          <h3>Wallet Balance</h3>
          <h2>₹{user.walletBalance}</h2>
        </div>

        <div className="btn-group">

          <button className="btn send"
            onClick={() => navigate("/send", { state: { user } })}>
            Send Money
          </button>

          <button className="btn balance"
            onClick={() => navigate("/view-balance", { state: { user } })}>
            View Balance
          </button>

          <button className="btn history"
            onClick={() => navigate("/transaction-history", { state: { user } })}>
            Transaction History
          </button>

          <button className="btn create"
            onClick={() => navigate("/create-account", { state: { user } })}>
            Create Bank Account
          </button>

        </div>

        <button className="logout-btn" onClick={logout}>
          Logout
        </button>

      </div>

    </div>
  );
};

export default Home;