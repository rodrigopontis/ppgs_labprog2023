import HttpService from "./HttpService";

export default class ProgramaService extends HttpService {
  getStatsByYear = async (idProg, anoIni, anoFim) => {
    // console.log("Req:", idProg, anoIni, anoFim);
    return await this.get(
      `/qualis/stats/${idProg}/filter?anoIni=${anoIni}&anoFim=${anoFim}`
    );
  };

  getDocentesStatsByYear = async (idProg, anoIni, anoFim) => {
    // console.log("Req:", idProg, anoIni, anoFim);
    return await this.get(`docente/qualis/stats/${idProg}/${anoIni}/${anoFim}`);
  };
}
