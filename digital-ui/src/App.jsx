import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Login from "./Login";
import Register from "./user/Register";
import Home from "./user/Home";
import SendMoney from "./user/SendMoney";
// import SendToBank from "./user/SendToBank";
import { AddPocket } from './user/addPocket';


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/home" element={<Home />} />
        <Route path="/send" element={<SendMoney />} />
        {/* <Route path="/send-to-bank" element={<SendToBank />} /> */}
        <Route path="/add_pocket" element={<AddPocket />} />
        
      
      </Routes>
    </Router>
  );
}

export default App;