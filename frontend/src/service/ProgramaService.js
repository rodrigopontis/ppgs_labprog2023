import { HttpService } from "./HttpService";

export default class ProgramaService extends HttpService {
  create(data) {
    return this.post("movies", data);
  }
}
