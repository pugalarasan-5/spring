import { HashRouterRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Login";
import Register from "./user/Register";
import Home from "./user/Home";
import SendMoney from "./user/SendMoney";
import SendToBank from "./user/SendToBank";
import { AddPocket } from './user/addPocket';
import TransactionHistory from "./user/Transaction";
import CreateAccount from "./user/createAccount";
import ViewBalance from "./user/veiw_balance";
import "./App.css";



function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/home" element={<Home />} />
        <Route path="/send" element={<SendMoney />} />
        <Route path="/send-to-bank" element={<SendToBank />} />
        <Route path="/transaction-history" element={<TransactionHistory />} />
        <Route path="/add_pocket" element={<AddPocket />} />
        <Route path="/create-account" element={<CreateAccount />} />
        <Route path="/view-balance" element={<ViewBalance />} />
      </Routes>
    </Router>
  );
}

export default App;