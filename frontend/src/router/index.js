import { createBrowserRouter } from "react-router-dom";
import { Programa, Login, Docente } from "../pages";
import { Navigation } from "./Navigation";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <Navigation />,
  },
  {
    path: "/programa",
    element: <Programa />,
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/docente/:idDocente",
    element: <Docente />,
  },
]);
