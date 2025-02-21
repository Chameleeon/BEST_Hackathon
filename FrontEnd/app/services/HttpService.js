import { create } from "apisauce";
import { API_URL } from "@env";

const api = create({
  baseURL: API_URL,
  headers: { "Content-Type": "application/json" },
});

class HttpService {
  handleResponse(response) {
    if (!response.ok) {
      return {
        error: true,
        status: response.status,
        message: response.problem || "Request failed",
      };
    }
    return response.data;
  }

  async create(resource, data) {
    const response = await api.post(`/${resource}`, data);
    return this.handleResponse(response);
  }

  async get(resource) {
    const response = await api.get(`/${resource}`);
    return this.handleResponse(response);
  }

  async getById(resource, id) {
    const response = await api.get(`/${resource}/${id}`);
    return this.handleResponse(response);
  }

  async update(resource, data) {
    const response = await api.put(`/${resource}`, data);
    return this.handleResponse(response);
  }

  async delete(resource) {
    const response = await api.delete(`/${resource}`);
    return this.handleResponse(response);
  }
}

export default new HttpService();
