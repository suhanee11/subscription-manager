import { useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "./service/api";

function App() {

  const navigate = useNavigate();

  const [isLogin, setIsLogin] = useState(true);

  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const handleAuth = async () => {

    try {

      const endpoint = isLogin
        ? "/api/auth/login"
        : "/api/auth/signup";

      const response = await API.post(
        endpoint,
        {
          email,
          password,
        }
      );

      console.log(response.data);

      if (isLogin) {

        localStorage.setItem(
          "token",
          response.data.token
        );

        alert("Login successful");

        navigate("/dashboard");

      } else {

        alert("Signup successful");

        setIsLogin(true);
      }

    } catch (error) {

      console.log(error);

      alert(
        error.response?.data?.message ||
        JSON.stringify(error.response?.data) ||
        error.message
      );
    }
  };

  return (

    <div>

      <h1>
        {isLogin ? "Login" : "Signup"}
      </h1>

      <input
        type="email"
        placeholder="Email"
        onChange={(e) =>
          setEmail(e.target.value)
        }
      />

      <br /><br />

      <input
        type="password"
        placeholder="Password"
        onChange={(e) =>
          setPassword(e.target.value)
        }
      />

      <br /><br />

      <button onClick={handleAuth}>
        {isLogin ? "Login" : "Signup"}
      </button>

      <br /><br />

      <button
        onClick={() =>
          setIsLogin(!isLogin)
        }
      >
        {isLogin
          ? "Create new account"
          : "Already have account"}
      </button>

    </div>
  );
}

export default App;