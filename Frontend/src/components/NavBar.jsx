import { useNavigate } from "react-router-dom";
import "./../styles/navbar.css";
import logo from "./../assets/logo.png";

const NavBar = () => {
  const navigate = useNavigate();

  return (
    <div className="navbar-container">

      <div className="navbar-left" onClick={() => navigate("/")}>
        <img src={logo} alt="logo" className="logo" />
        <h2 className="project-name">SecureDrive</h2>
      </div>

      <div className="navbar-right">
        <button onClick={() => navigate("/login")} className="login-button">
          Login
        </button>
        <button onClick={() => navigate("/signup")} className="signup-button">
          Sign Up
        </button>
      </div>

    </div>
  );
};

export default NavBar;