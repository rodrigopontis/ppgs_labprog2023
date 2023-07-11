import { useContext } from "react"

const AuthContext = useContext();

export const AuthProvider = ({children}) => {
    return <AuthContext.Provider>{children}</AuthContext.Provider>
}