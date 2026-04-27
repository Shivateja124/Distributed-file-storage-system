import { useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../services/api";
import "./../styles/login.css";
import lock from "./../assets/lock.png";
import security from "./../assets/security.png";
import distributed from "./../assets/distributed.png";
import database from "./../assets/database.png";
import availability from "./../assets/availability.png";

function Login() {
  const [password, setPassword] = useState("");
  const [username, setUsername] = useState("");
  const [errors, setErrors] = useState({});

  const navigate = useNavigate();

  const handleLogin = async () => {
    let newErrors = {};

    if (!username) newErrors.username = "Please enter username";
    if (!password) newErrors.password = "Please enter password";

    setErrors(newErrors);

    if (Object.keys(newErrors).length > 0) return;

    try {
      const res = await API.post("/login", {
        username,
        password
      });

      console.log(res.data);

      localStorage.setItem("username", username);

      navigate("/dashboard");

    } catch (err) {
      setErrors({
        general: err.response?.data || "Invalid credentials"
      });
    }
  };

  return (
    <div className="login-container">

      <div className="login-card">

        <div className="lock-logo">
          <img src={lock} alt="Lock image" className="lock-icon"></img>
        </div>

        <h2>Welcome Back</h2>
        <p>Login to your SecureDrive account</p>


        <input
          type="text"
          placeholder="Username or Email"
          onChange={(e) => setUsername(e.target.value)}
        />
        {errors.username && <p className="error">{errors.username}</p>}


        <input
          type="password"
          placeholder="Password"
          onChange={(e) => setPassword(e.target.value)}
        />
        {errors.password && <p className="error">{errors.password}</p>}


        <div className="login-options">
          <label>
            <input type="checkbox" /> Remember me
          </label>
          <span className="forgot">Forgot Password?</span>
        </div>


        {errors.general && <p className="error">{errors.general}</p>}

        <button className="login-btn" onClick={handleLogin}>
          Login
        </button>

        <p className="signup-text">
          Don’t have an account?{" "}
          <span onClick={() => navigate("/signup")}>Sign Up</span>
        </p>

      </div>

      <div className="feature-container">
              <div className="feature-wrapper">
      
                <div className="feature-card">
                  <img src={security} className="feature-img" alt="encryption" />
                  <div className="feature-text">
                    <h3>End-to-End Encryption</h3>
                    <p>Your files are encrypted before upload and only you have the key.</p>
                  </div>
                </div>
      
                <div className="feature-card">
                  <img src={distributed} className="feature-img" alt="distributed" />
                  <div className="feature-text">
                    <h3>Distributed Architecture</h3>
                    <p>Files are split and stored across multiple servers for maximum reliability.</p>
                  </div>
                </div>
      
                <div className="feature-card">
                  <img src={database} className="feature-img" alt="redundancy" />
                  <div className="feature-text">
                    <h3>Data Redundancy</h3>
                    <p>Replicated and backed up across nodes to prevent data loss.</p>
                  </div>
                </div>
      
                <div className="feature-card">
                  <img src={availability} className="feature-img" alt="availability" />
                  <div className="feature-text">
                    <h3>High Availability</h3>
                    <p>Access your files anytime, anywhere with 99.9% uptime.</p>
                  </div>
                </div>
      
              </div>
            </div>

    </div>
  );
}

export default Login;