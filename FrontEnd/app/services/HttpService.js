import { create } from "apisauce";


const api = create({
  baseURL: 'http://172.20.10.3:8080',
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
