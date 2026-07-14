import api from "./api";

export const getAllCrops = () => api.get("/crops");

export const getCropById = (id) => api.get(`/crops/${id}`);

export const createCrop = (crop) => api.post("/crops", crop);

export const updateCrop = (id, crop) => api.put(`/crops/${id}`, crop);

export const deleteCrop = (id) => api.delete(`/crops/${id}`);