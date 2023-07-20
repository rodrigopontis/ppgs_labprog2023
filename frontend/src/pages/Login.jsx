import { useState } from "react";
import { Navigate } from "react-router-dom";
import { useAuth } from "../auth/AuthProvider";

export const Login = () => {
  const [formData, setFormData] = useState({
    username: "",
    password: "",
  });

  const { token, onLogin } = useAuth();

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const tryLogin = () => {
    onLogin(formData);
  };

  if (token) return <Navigate to="/programa" />;

  return (
    <section className="vh-100">
      <div className="container-fluid">
        <div className="row">
          <div className="col-sm-6 text-black">
            <div className="px-5 ms-xl-4">
              <i className="fas fa-crow fa-2x me-3 pt-5 mt-xl-4"></i>
              <span className="h1 fw-bold mb-0">SPPG</span>
            </div>

            <div className="d-flex align-items-center h-custom-2 px-5 ms-xl-4 mt-5 pt-5 pt-xl-0 mt-xl-n5">
              <form>
                <h3 className="fw-normal mb-3 pb-3">
                  Bem vindo ao Programa de PPG
                </h3>

                <div className="form-outline mb-4">
                  <input
                    type="email"
                    className="form-control form-control-lg"
                    name="username"
                    placeholder="Email"
                    onChange={handleInputChange}
                  />
                </div>

                <div className="form-outline mb-4">
                  <input
                    type="password"
                    name="password"
                    className="form-control form-control-lg"
                    placeholder="Senha"
                    onChange={handleInputChange}
                  />
                </div>

                <div className="pt-1 mb-4">
                  <button
                    className="btn btn-info btn-lg btn-block"
                    type="button"
                    onClick={tryLogin}
                  >
                    Entrar
                  </button>
                </div>

                <p className="small mb-5 pb-lg-2">
                  <a className="text-muted" href="#!">
                    Esqueceu a senha?
                  </a>
                </p>
                <p>
                  Não tem conta?{" "}
                  <a href="#!" className="link-info">
                    Faça seu cadastro
                  </a>
                </p>
              </form>
            </div>
          </div>
          <div className="col-sm-6 px-0 d-none d-sm-block">
            <img
              src="https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-login-form/img3.webp"
              alt="Login"
              className="w-100 vh-100"
              style={imgStyle}
            />
          </div>
        </div>
      </div>
    </section>
  );
};

const imgStyle = {
  objectFit: "cover",
};
