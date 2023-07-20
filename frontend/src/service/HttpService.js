import axios from "axios";

export default class HttpService {
  token;

  constructor(token) {
    this.axios = axios.create({
      baseURL: "http://localhost:8080/api/v1",
      headers: {
        Cookies: `teles=${token}`,
      },
    });
  }

  post(url, data) {
    return this.axios.post(url, data);
  }

  get(url) {
    return this.axios.get(url);
  }
}
