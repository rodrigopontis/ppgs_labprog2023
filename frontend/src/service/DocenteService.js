import HttpService from "./HttpService";

export default class DocenteService extends HttpService {
  getName = async (idDocente) => {
    return await this.get(`/docente/nome/${idDocente}`);
  };

  getDocenteProds = async (idDocente, anoIni, anoFim) => {
    // console.log("Req:", idProg, anoIni, anoFim);
    return await this.get(
      `/docente/obter_producoes/${idDocente}/${anoIni}/${anoFim}`
    );
  };

  getDocenteTecnica = async (idDocente, anoIni, anoFim) => {
    // console.log("Req:", idDocente, anoIni, anoFim);
    return await this.get(
      `/docente/obter_tecnicas/${idDocente}/${anoIni}/${anoFim}`
    );
  };

  getOrientacoesDocente = async (idDocente, anoIni, anoFim) => {
    // console.log("Req:", idDocente, anoIni, anoFim);
    return await this.get(
      `/docente/obter_orientacoes/${idDocente}/${anoIni}/${anoFim}`
    );
  };

  getAllDocenteStats = async (idDocente, anoIni, anoFim) => {
    // console.log("Req:", idProg, anoIni, anoFim);
    return await this.get(
      `docente/qualis/stats/15/${idDocente}/${anoIni}/${anoFim}`
    );
  };
}
