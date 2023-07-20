import { createContext, useContext, useEffect, useState } from "react";
import { CookiesProvider, Cookies } from "react-cookie";
import AuthService from "../service/AuthService";

const authContext = createContext();

const service = new AuthService();

const cookies = new Cookies();

export const AuthProvider = ({ children }) => {
  const [token, setToken] = useState(null);

  const handleLogin = ({ username, password }) => {
    if (token) return;

    service
      .tryLogin(username, password)
      .then((res) => {
        cookies.set("user", res.data.username);
        cookies.set("token", res.data.token);
        setToken(res.data.token);
      })
      .catch((err) => {
        console.error(err);
      });
  };

  const handleLogout = () => {
    cookies.remove("user");
    cookies.remove("token");
    setToken(null);
  };

  const value = {
    token,
    onLogin: handleLogin,
    onLogout: handleLogout,
  };

  // armazenar token para futuras sessoes
  useEffect(() => {
    const userToken = cookies.get("token");

    if (userToken) {
      setToken(userToken);
    }
  }, []);

  return (
    <CookiesProvider>
      <authContext.Provider value={value}>{children}</authContext.Provider>
    </CookiesProvider>
  );
};

export const useAuth = () => {
  return useContext(authContext);
};
