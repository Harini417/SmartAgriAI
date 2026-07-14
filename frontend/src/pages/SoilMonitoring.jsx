import React, { useEffect, useState } from "react";
import {
  Box,
  Card,
  CardContent,
  Typography,
} from "@mui/material";
import { DataGrid } from "@mui/x-data-grid";
import { cropAPI } from "../services/api";

function SoilMonitoring() {
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

  const getSoilData = (soilType) => {
    switch (soilType) {
      case "Loamy":
        return {
          moisture: "65%",
          ph: "6.8",
          health: "Excellent",
          recommendation:
            "Suitable for most crops. Irrigate every 2-3 days.",
        };

      case "Clay":
        return {
          moisture: "80%",
          ph: "7.2",
          health: "Good",
          recommendation:
            "Avoid overwatering. Ensure proper drainage.",
        };

      case "Sandy":
        return {
          moisture: "35%",
          ph: "6.2",
          health: "Moderate",
          recommendation:
            "Water frequently and add organic compost.",
        };

      case "Black Soil":
        return {
          moisture: "70%",
          ph: "7.5",
          health: "Excellent",
          recommendation:
            "Ideal for cotton and oilseed crops.",
        };

      case "Red Soil":
        return {
          moisture: "45%",
          ph: "6.5",
          health: "Good",
          recommendation:
            "Apply organic manure regularly.",
        };

      case "Clay Loam":
        return {
          moisture: "68%",
          ph: "6.9",
          health: "Very Good",
          recommendation:
            "Maintain balanced irrigation.",
        };

      case "Sandy Loam":
        return {
          moisture: "50%",
          ph: "6.4",
          health: "Good",
          recommendation:
            "Water regularly and add compost.",
        };

      case "Silt":
        return {
          moisture: "60%",
          ph: "6.7",
          health: "Good",
          recommendation:
            "Maintain proper drainage.",
        };

      case "Silty Loam":
        return {
          moisture: "62%",
          ph: "6.8",
          health: "Excellent",
          recommendation:
            "Suitable for vegetables and grains.",
        };

      case "Laterite Soil":
        return {
          moisture: "40%",
          ph: "5.8",
          health: "Moderate",
          recommendation:
            "Apply lime and organic fertilizers.",
        };

      case "Mountain Soil":
        return {
          moisture: "55%",
          ph: "6.3",
          health: "Good",
          recommendation:
            "Suitable for tea and coffee cultivation.",
        };

      default:
        return {
          moisture: "60%",
          ph: "6.8",
          health: "Good",
          recommendation:
            "Maintain proper irrigation schedule.",
        };
    }
  };

  const rows = crops.map((crop) => {
    const soil = getSoilData(crop.soilType);

    return {
  id: crop.id,
  cropName: crop.cropName,
  soilType: crop.soilType,
  country: crop.country,
  state: crop.state,
  location: crop.location,
  moisture: soil.moisture,
  ph: soil.ph,
  health: soil.health,
  recommendation: soil.recommendation,
};
  });

  const columns = [
    {
      field: "cropName",
      headerName: "Crop Name",
      width: 160,
    },
    {
      field: "soilType",
      headerName: "Soil Type",
      width: 150,
    },
    {
  field: "country",
  headerName: "Country",
  width: 120,
},
{
  field: "state",
  headerName: "State",
  width: 150,
},
{
  field: "location",
  headerName: "Hilly Region",
  width: 170,
},
    {
      field: "moisture",
      headerName: "Moisture",
      width: 120,
    },
    {
      field: "ph",
      headerName: "pH",
      width: 100,
    },
    {
      field: "health",
      headerName: "Soil Health",
      width: 150,
    },
    {
      field: "recommendation",
      headerName: "Recommendation",
      width: 350,
    },
  ];

  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" gutterBottom>
        🌱 Soil Monitoring
      </Typography>

      <Card>
        <CardContent>
          <Typography variant="h6" sx={{ mb: 2 }}>
            Soil Analysis
          </Typography>

        <Box sx={{ width: "100%", height: "70vh", mt: 3 }}>
  <DataGrid
    rows={rows}
    columns={columns}
    disableRowSelectionOnClick
    hideFooter
  />
</Box> 
        </CardContent>
      </Card>
    </Box>
  );
}

export default SoilMonitoring;