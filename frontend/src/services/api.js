import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});
const user = JSON.parse(localStorage.getItem("user"));

console.log(user);
console.log(user.id);

// Request interceptor
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => Promise.reject(error)
);

// Response interceptor
api.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      localStorage.removeItem('token');
      window.location.href = '/login';
    }
    return Promise.reject(error);
  }
);

// Dashboard APIs
export const dashboardAPI = {
getAnalytics: (userId) => api.get(`/dashboard/${userId}`),
  getHealthCheck: () => api.get('/dashboard/health'),
};

// Crop APIs
export const cropAPI = {
  getAllCrops: () => api.get('/crops'),
  getUserCrops: (userId) => api.get(`/crops/user/${userId}`),
  getCropById: (id) => api.get(`/crops/${id}`),
  getCropsByLocation: (location) => api.get(`/crops/location/${location}`),
  getCropsByStatus: (status) => api.get(`/crops/status/${status}`),
  getCropsByType: (type) => api.get(`/crops/type/${type}`),
  createCrop: (userId, cropData) =>
  api.post(`/crops/user/${userId}`, cropData),
  updateCrop: (id, cropData) => api.put(`/crops/${id}`, cropData),
  deleteCrop: (id) => api.delete(`/crops/${id}`),
};

// Soil Data APIs
export const soilAPI = {
  getAllSoilData: () => api.get('/soil-data'),
  getSoilDataById: (id) => api.get(`/soil-data/${id}`),
  getSoilDataByLocation: (location) => api.get(`/soil-data/location/${location}`),
  createSoilData: (soilData) => api.post('/soil-data', soilData),
  updateSoilData: (id, soilData) => api.put(`/soil-data/${id}`, soilData),
  deleteSoilData: (id) => api.delete(`/soil-data/${id}`),
};

// Weather APIs
export const weatherAPI = {
  getWeatherByCity: (city) => api.get(`/weather/${city}`),
  getWeatherHealthCheck: () => api.get('/weather/health'),
};

// Crop Recommendation APIs
export const recommendationAPI = {
  getCropRecommendation: (data) => api.post('/recommendation', data),
  getHealthCheck: () => api.get('/recommendation/health'),
};

// Irrigation APIs
export const irrigationAPI = {
  getIrrigationRecommendation: (data) => api.post('/irrigation/recommendation', data),
  getHealthCheck: () => api.get('/irrigation/health'),
};

// Yield Prediction APIs
export const yieldAPI = {
  predictYield: (data) => api.post('/yield/predict', data),
  getHealthCheck: () => api.get('/yield/health'),
};

export default api;
