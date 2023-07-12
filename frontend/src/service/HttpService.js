import axios from "axios";

export default class HttpService {
  constructor() {
    this.axios = axios.create({
      baseURL: "http://localhost:8080/api/v1",
    });
  }

  post(url, data) {
    return this.axios.post(url, data);
  }

  get(url) {
    return this.axios.get(url);
  }
}
