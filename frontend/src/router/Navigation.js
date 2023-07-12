import { useEffect, useState } from "react";
import { Navigate } from "react-router-dom";

export const Navigation = () => {
  const [token, setToken] = useState(null);

  useEffect(() => {
    const userToken = localStorage.getItem("token");

    setToken(userToken);
  }, []);

  if (token) return <Navigate to="/programa" />;

  return <Navigate to="/login" />;
};
