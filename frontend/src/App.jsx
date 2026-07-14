import React from "react";
import { BrowserRouter, Routes, Route, Navigate, Outlet } from "react-router-dom";
import { ToastContainer } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

import MainLayout from "./layouts/MainLayout";
import Login from "./pages/Login";
import Dashboard from "./pages/Dashboard";
import CropManagement from "./pages/CropManagement";
import SoilMonitoring from "./pages/SoilMonitoring";
import Weather from "./pages/Weather";
import AIRecommendation from "./pages/AIRecommendation";
import Irrigation from "./pages/Irrigation";
import YieldPrediction from "./pages/YieldPrediction";
import Analytics from "./pages/Analytics";
import Settings from "./pages/Settings";
import Register from "./pages/Register";
import Profile from "./pages/Profile";
const ProtectedRoute = ({ isAuthenticated }) => {
  return isAuthenticated ? (
    <MainLayout>
      <Outlet />
    </MainLayout>
  ) : (
    <Navigate to="/login" replace />
  );
};

function App() {
  const isAuthenticated = !!localStorage.getItem("token");

  return (
    <>
      <BrowserRouter>
        <Routes>
          {/* Public Route */}
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          {/* Protected Routes */}
          <Route element={<ProtectedRoute isAuthenticated={isAuthenticated} />}>
            <Route path="/dashboard" element={<Dashboard />} />
            <Route path="/crops" element={<CropManagement />} />
            <Route path="/soil-monitoring" element={<SoilMonitoring />} />
            <Route path="/weather" element={<Weather />} />
            <Route path="/recommendation" element={<AIRecommendation />} />
            <Route path="/irrigation" element={<Irrigation />} />
            <Route path="/yield" element={<YieldPrediction />} />
            <Route path="/yield-prediction" element={<YieldPrediction />} />
            <Route path="/analytics" element={<Analytics />} />
            <Route path="/settings" element={<Settings />} />
            <Route path="/profile" element={<Profile />} />
          </Route>

          {/* Redirect */}
          <Route
            path="/"
            element={
              <Navigate
                to={isAuthenticated ? "/dashboard" : "/login"}
                replace
              />
            }
          />

          {/* 404 */}
          <Route path="*" element={<Navigate to="/" replace />} />
        </Routes>
      </BrowserRouter>

      <ToastContainer
        position="top-right"
        autoClose={3000}
        hideProgressBar={false}
        newestOnTop
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
      />
    </>
  );
}

export default App;