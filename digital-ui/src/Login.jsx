import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

function Login() {
  const [data, setData] = useState({ email: "", password: "" });
  const navigate = useNavigate();

  const handleLogin = async () => {
    try {
      let res;

      // 👉 Check if input is email or phone
      if (data.email.includes("@")) {
        // Email login
        res = await axios.post(
          `http://localhost:8080/user/login`,
          {
            email: data.email,
            password: data.password
          }
        );
      } else if (!isNaN(data.email)) {
        // Phone login
        res = await axios.post(
          `http://localhost:8080/user/login`,
          {
            phoneNumber: data.email,
            password: data.password
          }
        );
      } else {
        alert("Invalid email or phone number format");
        return;
      }

      // Save user
      localStorage.setItem("user", JSON.stringify(res.data));

      // Navigate with data
      navigate("/home", { state: { user: res.data } });

    } catch (error) {
      console.error(error);
      alert("Login failed");
    }
  };

  // 👉 Separate function
  const goToRegister = () => {
    navigate("/register");
  };

  return (
    <div>
      <h2>Login</h2>

      <input
        placeholder="Email or Phone"
        onChange={(e) =>
          setData({ ...data, email: e.target.value })
        }
      />

      <input
        type="password"
        placeholder="Password"
        onChange={(e) =>
          setData({ ...data, password: e.target.value })
        }
      />

      <button onClick={handleLogin}>Login</button>

      <p onClick={goToRegister}>Create Account</p>
    </div>
  );
}

export default Login;