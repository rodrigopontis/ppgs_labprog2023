import HttpService from "./HttpService";

export default class AuthService extends HttpService {
  // rota de login somente para todos
  tryLogin = async (username, password) => {
    // console.log("Req:", idProg, anoIni, anoFim);
    return await this.post("/auth/login", { username, password });
  };
}
