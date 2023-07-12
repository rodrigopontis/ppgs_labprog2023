import { NavBar } from "./NavBar";

export const PageComponent = ({ children }) => {
  return (
    <div className="wrapper">
      <NavBar />
      {children}
    </div>
  );
};
