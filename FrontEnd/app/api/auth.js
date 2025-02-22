import HttpService from "../services/HttpService";

const login = async (username, password) => {
  try {
    const response = await HttpService.create("login", {
      'username': username,
      'password': password,
    });
    return response; 
  } catch (error) {
    console.error("Login failed:", error);
    return error.response?.status || 500; 
  }
};

const register = async (firstName, lastName, email, password, type) => {
  try {
    const response = await HttpService.create("register", {
      'name': firstName,
      'surname': lastName,
      'username': username,
      'points': 0,
      'password': password,
      "world": "Srednji svijet",
      "character": "Vitez"
    });
    return response; 
  } catch (error) {
    console.error("Registration failed:", error);
    return error.response?.status || 500; 
  }
};

export default {
  login,
  register,
};
