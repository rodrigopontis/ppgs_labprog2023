import { Link, useNavigate } from "react-router-dom";
import { useAuth } from "../auth/AuthProvider";

export const NavBar = () => {
  const navigate = useNavigate();

  const { onLogout } = useAuth();

  const handleSelectLink = (loc) => {
    if (window.location.pathname === loc) return "nav-item active";

    return "nav-item";
  };

  const tryLogout = () => {
    onLogout();
    navigate("/");
  };

  return (
    <nav className="main-header navbar navbar-expand-md navbar-light navbar-white">
      <div className="container">
        <a
          href="https://github.com/gebraz/ppgs_labprog2023"
          className="navbar-brand"
          target="_blank"
          rel="noreferrer"
        >
          <span className="brand-text font-weight-light">SPPG</span>
        </a>

        <button
          className="navbar-toggler order-1"
          type="button"
          data-toggle="collapse"
          data-target="#navbarCollapse"
          aria-controls="navbarCollapse"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>

        <div className="collapse navbar-collapse order-3" id="navbarCollapse">
          <ul className="navbar-nav">
            <li className={handleSelectLink("/programa")}>
              <Link to="/programa" className="nav-link">
                Programas
              </Link>
            </li>
          </ul>
        </div>
      </div>
      <form className="form-inline mr-5 bg-red">
        <button type="button" className="btn btn-danger" onClick={tryLogout}>
          <i className="fa-sharp fa-solid fa-right-to-bracket"></i>
        </button>
      </form>
    </nav>
  );
};
