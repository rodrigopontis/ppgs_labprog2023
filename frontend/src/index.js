import React from "react";
import ReactDOM from "react-dom/client";
import { RouterProvider } from "react-router-dom";
import "@fortawesome/fontawesome-free/css/all.min.css";
import { router } from "./router";
import { AuthProvider } from "./auth/AuthProvider";

const root = ReactDOM.createRoot(document.getElementById("root"));

root.render(
  <AuthProvider>
    <RouterProvider router={router} />
  </AuthProvider>
);
