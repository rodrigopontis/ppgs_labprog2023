import { Link, useNavigate } from "react-router-dom";
export const NavBar = () => {
  const navigate = useNavigate();

  const tryLogout = () => {
    navigate("/login");
  };

  return (
    <nav className="main-header navbar navbar-expand-md navbar-light navbar-white">
      <div className="container">
        <Link to="/programa" className="navbar-brand">
          <span className="brand-text font-weight-light">SPPG</span>
        </Link>

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
            <li className="nav-item">
              <Link to="/programa" className="nav-link">
                Programas
              </Link>
            </li>
            <li className="nav-item">
              <Link to="/docente" className="nav-link">
                Docentes
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
