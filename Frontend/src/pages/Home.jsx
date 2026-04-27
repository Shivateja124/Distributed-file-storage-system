import "./../styles/home.css";
import secure from "./../assets/secure.png";
import reliable from "./../assets/reliable.png";
import fast from "./../assets/fast.png";
import security from "./../assets/security.png";
import distributed from "./../assets/distributed.png";
import database from "./../assets/database.png";
import availability from "./../assets/availability.png";
function Home() {
  return (
    <div className="home-container">
      <div className="left-container">
        <h1>Distributed Secure</h1>
        <h1>
          <span className="green-text">File Storage</span> System
        </h1>




        <p className="sub-desc">Store, manage, and access your files securely using a distributed system
          built for privacy and performance.
        </p>



        <div className="buttons">
          <button className="get-start-button">Get Started <span>⟶</span></button>
          <button className="learn-more-button">Learn More</button>
        </div>


        <div className="highlights">
          <div className="highlight-item">
            <img src={secure} className="highlights-img" alt="secure" />
            <span>Secure</span>
          </div>

          <div className="highlight-item">
            <img src={reliable} className="highlights-img" alt="reliable" />
            <span>Reliable</span>
          </div>

          <div className="highlight-item">
            <img src={fast} className="highlights-img" alt="fast" />
            <span>Fast</span>
          </div>
        </div>




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

      <div>

      </div>
    </div>
  );
}

export default Home;