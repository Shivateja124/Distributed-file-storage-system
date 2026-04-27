import { useState } from "react";
import { useNavigate } from "react-router-dom";
import API from "../services/api";
import "./../styles/signup.css";
import account from "./../assets/account.png";
import security from "./../assets/security.png";
import distributed from "./../assets/distributed.png";
import database from "./../assets/database.png";
import availability from "./../assets/availability.png";

function Signup() {
  const navigate = useNavigate();

  const [form, setForm] = useState({
    username: "",
    email: "",
    password: "",
    confirmPassword: ""
  });

  const [errors, setErrors] = useState({});

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const handleSignup = async () => {
    let newErrors = {};

    if (!form.username) newErrors.username = "Enter username";
    if (!form.email) newErrors.email = "Enter email";
    if (!form.password) newErrors.password = "Enter password";
    if (!form.confirmPassword)
      newErrors.confirmPassword = "Confirm your password";

    if (form.password !== form.confirmPassword) {
      newErrors.confirmPassword = "Passwords do not match";
    }

    setErrors(newErrors);

    if (Object.keys(newErrors).length > 0) return;

    try {
      const res = await API.post("/register", {
        email: form.email,
        username: form.username,
        password: form.password
      });

      console.log(res.data);

      // Redirect to login after signup
      navigate("/login");

    } catch (err) {
      setErrors({
        general: err.response?.data || "Signup failed"
      });
    }
  };

  return (
    <div className="signup-container">

      <div className="signup-card">

        <div className="lock-logo">
          <img src={account} alt="icon" className="lock-icon" />
        </div>

        <h2>Create Account</h2>
        <p>Sign up to your SecureDrive account</p>

        {/* USERNAME */}
        <input
          type="text"
          name="username"
          placeholder="Username"
          onChange={handleChange}
        />
        {errors.username && <p className="error">{errors.username}</p>}

        {/* EMAIL */}
        <input
          type="email"
          name="email"
          placeholder="Email"
          onChange={handleChange}
        />
        {errors.email && <p className="error">{errors.email}</p>}

        {/* PASSWORD */}
        <input
          type="password"
          name="password"
          placeholder="Password"
          onChange={handleChange}
        />
        {errors.password && <p className="error">{errors.password}</p>}

        {/* CONFIRM PASSWORD */}
        <input
          type="password"
          name="confirmPassword"
          placeholder="Confirm Password"
          onChange={handleChange}
        />
        {errors.confirmPassword && (
          <p className="error">{errors.confirmPassword}</p>
        )}

        {/* TERMS */}
        <div className="terms">
          <input type="checkbox" />
          <span>
            I agree to the <b>Terms</b> & <b>Privacy Policy</b>
          </span>
        </div>

        {errors.general && <p className="error">{errors.general}</p>}

        <button className="signup-btn" onClick={handleSignup}>
          Sign Up
        </button>

        <p className="login-text">
          Already have an account?{" "}
          <span onClick={() => navigate("/login")}>Login</span>
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

export default Signup;