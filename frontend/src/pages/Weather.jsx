import React, { useEffect, useState } from "react";
import {
  Box,
  Card,
  CardContent,
  Typography,
} from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";
import { cropAPI } from "../services/api";

function Weather() {
  const [crops, setCrops] = useState([]);

  useEffect(() => {
    loadCrops();
  }, []);

  const loadCrops = async () => {
    try {
      const response = await cropAPI.getAllCrops();
      setCrops(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  // Generate weather based on location
  const getWeatherData = (location) => {
    switch ((location || "").toLowerCase()) {
      case "coimbatore":
        return {
          temperature: "30°C",
          humidity: "65%",
          rainfall: "15%",
          wind: "12 km/h",
          condition: "Sunny",
          recommendation: "Good day for irrigation.",
        };

      case "ooty":
        return {
          temperature: "19°C",
          humidity: "85%",
          rainfall: "70%",
          wind: "8 km/h",
          condition: "Rainy",
          recommendation: "Rain expected. Avoid irrigation.",
        };

      case "kodaikanal":
        return {
          temperature: "18°C",
          humidity: "82%",
          rainfall: "60%",
          wind: "10 km/h",
          condition: "Cloudy",
          recommendation: "Monitor rainfall before watering.",
        };

      case "coonoor":
        return {
          temperature: "20°C",
          humidity: "80%",
          rainfall: "55%",
          wind: "9 km/h",
          condition: "Cloudy",
          recommendation: "Light irrigation only if needed.",
        };

      case "valparai":
        return {
          temperature: "22°C",
          humidity: "90%",
          rainfall: "75%",
          wind: "14 km/h",
          condition: "Rainy",
          recommendation: "Heavy rain expected today.",
        };

      default:
        return {
          temperature: "28°C",
          humidity: "70%",
          rainfall: "25%",
          wind: "10 km/h",
          condition: "Partly Cloudy",
          recommendation: "Normal irrigation schedule.",
        };
    }
  };

  const rows = crops.map((crop) => {
    const weather = getWeatherData(crop.location);

    return {
  id: crop.id,
  cropName: crop.cropName,
  country: crop.country,
  state: crop.state,
  location: crop.location,
  temperature: weather.temperature,
  humidity: weather.humidity,
  rainfall: weather.rainfall,
  wind: weather.wind,
  condition: weather.condition,
  recommendation: weather.recommendation,
};
  });

  const columns = [
    { field: "cropName", headerName: "Crop", width: 150 },
    { field: "country", headerName: "Country", width: 120 },
    { field: "state", headerName: "State", width: 150 },
    { field: "location", headerName: "Hilly Region", width: 170 },
    { field: "temperature", headerName: "Temperature", width: 130 },
    { field: "humidity", headerName: "Humidity", width: 120 },
    { field: "rainfall", headerName: "Rain Chance", width: 130 },
    { field: "wind", headerName: "Wind Speed", width: 130 },
    { field: "condition", headerName: "Condition", width: 130 },
    { field: "recommendation", headerName: "Recommendation", width: 280 },
  ];

  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" gutterBottom>
        🌦️ Weather Monitoring
      </Typography>

      <Card>
        <CardContent>
          <Typography variant="h6" sx={{ mb: 2 }}>
            Weather Analysis
          </Typography>

          <Box sx={{ width: "100%", height: "70vh" }}>
            <DataGrid
              rows={rows}
              columns={columns}
              hideFooter
              disableRowSelectionOnClick
            />
          </Box>
        </CardContent>
      </Card>
    </Box>
  );
}

export default Weather;