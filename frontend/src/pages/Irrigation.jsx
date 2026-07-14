import React, { useState } from "react";
import {
  Box,
  Card,
  CardContent,
  Typography,
  TextField,
  MenuItem,
  Button,
  Alert,
} from "@mui/material";

import Grid from "@mui/material/Grid";

function SmartIrrigation() {
  const [formData, setFormData] = useState({
    soilMoisture: "",
    temperature: "",
    humidity: "",
  });

  const [result, setResult] = useState(null);

  const checkIrrigation = () => {
  const moisture = Number(formData.soilMoisture);
  const temp = Number(formData.temperature);
  const humidity = Number(formData.humidity);

  if (!moisture || !temp || !humidity) {
    setResult({
      status: "⚠️ Please enter all sensor values.",
      water: "",
      time: "",
      note: "",
    });
    return;
  }

  // Dry soil + high temperature
  if (moisture < 30 && temp > 30) {
    setResult({
      status: "💧 Irrigation Required Immediately",
      water: "💧 Water Amount: 25 liters per plant",
      time: "🕒 Best Time: Early Morning (6:00–8:00 AM)",
      note: "⚠️ High temperature detected. Avoid afternoon irrigation to reduce water loss.",
    });
  }

  // Medium moisture
  else if (moisture < 40) {
    setResult({
      status: "🌿 Moderate Irrigation Recommended",
      water: "💧 Water Amount: 15 liters per plant",
      time: "🕒 Best Time: Morning or Evening",
      note: "Monitor soil moisture regularly.",
    });
  }

  // Good moisture
  else {
    setResult({
      status: "✅ No Irrigation Needed",
      water: "💧 Soil moisture level is sufficient.",
      time: "",
      note: "🌱 Continue monitoring crop condition.",
    });
  }
};

  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" gutterBottom>
        💧 Smart Irrigation
      </Typography>

      <Card>
        <CardContent>
          <Grid container spacing={3}>

            <Grid size={{ xs: 12, md: 4 }}>
              <TextField
  fullWidth
  type="number"
  label="Soil Moisture (%)"
  value={formData.soilMoisture}
  onChange={(e) =>
    setFormData({
      ...formData,
      soilMoisture: e.target.value,
    })
  }
  inputProps={{
    min: 0,
    max: 100,
  }}
/>
            </Grid>

            <Grid size={{ xs: 12, md: 4 }}>
              <TextField
  fullWidth
  type="number"
  label="Temperature (°C)"
  value={formData.temperature}
  onChange={(e) =>
    setFormData({
      ...formData,
      temperature: e.target.value,
    })
  }
  inputProps={{
    min: 0,
    max: 60,
  }}
/>
            </Grid>

            <Grid size={{ xs: 12, md: 4 }}>
              <TextField
  fullWidth
  type="number"
  label="Humidity (%)"
  value={formData.humidity}
  onChange={(e) =>
    setFormData({
      ...formData,
      humidity: e.target.value,
    })
  }
  inputProps={{
    min: 0,
    max: 100,
  }}
/>
            </Grid>

        </Grid>
          <Button
            variant="contained"
            sx={{ mt: 3 }}
            onClick={checkIrrigation}
          >
            Check Irrigation
          </Button>
        </CardContent>
      </Card>

      {result && (
  <Card sx={{ mt: 3 }}>
    <CardContent>
      <Typography variant="h6">
        🌱 Irrigation Recommendation
      </Typography>

      <Typography sx={{ mt: 2 }}>
        {result.status}
      </Typography>

      <Typography sx={{ mt: 1 }}>
        {result.water}
      </Typography>

      <Typography sx={{ mt: 1 }}>
        {result.time}
      </Typography>

      <Typography sx={{ mt: 1 }}>
        {result.note}
      </Typography>

    </CardContent>
  </Card>
)}
    </Box>
  );
}

export default SmartIrrigation;