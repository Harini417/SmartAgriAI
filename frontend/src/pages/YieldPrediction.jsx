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

function YieldPrediction() {

  const [formData, setFormData] = useState({
    crop: "",
    soilType: "",
    water: "",
    temperature: "",
    rainfall: "",
    fertilizer: "",
  });

  const [result, setResult] = useState(null);

const cropData = {
  Rice: {
    yield: "4 - 6 tons/hectare",
    condition: "Good",
    suggestion: "Maintain irrigation and monitor nitrogen levels."
  },

  Wheat: {
    yield: "3 - 5 tons/hectare",
    condition: "Good",
    suggestion: "Maintain soil moisture during flowering stage."
  },

  Maize: {
    yield: "5 - 8 tons/hectare",
    condition: "Excellent",
    suggestion: "Provide balanced fertilizer and proper watering."
  },

  Cotton: {
    yield: "2 - 4 tons/hectare",
    condition: "Good",
    suggestion: "Monitor pests and maintain soil nutrients."
  },

  Sugarcane: {
    yield: "70 - 100 tons/hectare",
    condition: "Excellent",
    suggestion: "Requires regular irrigation."
  },

  Groundnut: {
    yield: "2 - 3 tons/hectare",
    condition: "Good",
    suggestion: "Avoid excess water and maintain drainage."
  },

  Millet: {
    yield: "1.5 - 3 tons/hectare",
    condition: "Good",
    suggestion: "Suitable for low water availability."
  },

  Tomato: {
    yield: "30 - 50 tons/hectare",
    condition: "Good",
    suggestion: "Maintain nutrients and prevent diseases."
  },

  Banana: {
    yield: "40 - 60 tons/hectare",
    condition: "Excellent",
    suggestion: "Provide sufficient water and potassium fertilizer."
  },

  Tea: {
    yield: "2 - 4 tons/hectare",
    condition: "Good",
    suggestion: "Maintain humidity and regular pruning."
  },

  Coffee: {
    yield: "1 - 2 tons/hectare",
    condition: "Good",
    suggestion: "Maintain shade and moisture."
  }
};
const predictYield = () => {
   const selectedCrop = cropData[formData.crop];

if (selectedCrop) {

  setResult({
    crop: formData.crop,
    yield: selectedCrop.yield,
    condition: selectedCrop.condition,
    suggestion: selectedCrop.suggestion
  });

}
else {

  setResult({
    crop: formData.crop,
    yield: "Data not available",
    condition: "Unknown",
    suggestion: "Please select a valid crop."
  });

}

  };


  return (

    <Box sx={{ p:3 }}>

      <Typography variant="h4" gutterBottom>
        🌾 AI Yield Prediction
      </Typography>


      <Card>

        <CardContent>

          <Grid container spacing={3}>


            <Grid size={{xs:12, md:6}}>
              <TextField
                select
                fullWidth
                label="Crop Name"
                value={formData.crop}
                onChange={(e)=>
                  setFormData({
                    ...formData,
                    crop:e.target.value
                  })
                }
              >

                {Object.keys(cropData).map((crop)=>(
  <MenuItem key={crop} value={crop}>
    {crop}
  </MenuItem>
))}

              </TextField>
            </Grid>


            <Grid size={{xs:12, md:6}}>
              <TextField
                select
                fullWidth
                label="Soil Type"
                value={formData.soilType}
                onChange={(e)=>
                  setFormData({
                    ...formData,
                    soilType:e.target.value
                  })
                }
              >

                <MenuItem value="Red Soil">
  Red Soil
</MenuItem>

<MenuItem value="Black Soil">
  Black Soil
</MenuItem>

<MenuItem value="Loamy Soil">
  Loamy Soil
</MenuItem>

<MenuItem value="Sandy Soil">
  Sandy Soil
</MenuItem>

<MenuItem value="Clay Soil">
  Clay Soil
</MenuItem>

<MenuItem value="Alluvial Soil">
  Alluvial Soil
</MenuItem>

<MenuItem value="Mountain Soil">
  Mountain Soil
</MenuItem>

<MenuItem value="Laterite Soil">
  Laterite Soil
</MenuItem>

<MenuItem value="Peaty Soil">
  Peaty Soil
</MenuItem>

              </TextField>
            </Grid>


            <Grid size={{xs:12, md:6}}>
              <TextField
                select
                fullWidth
                label="Water Availability"
                value={formData.water}
                onChange={(e)=>
                  setFormData({
                    ...formData,
                    water:e.target.value
                  })
                }
              >

                <MenuItem value="Low">
                  Low
                </MenuItem>

                <MenuItem value="Medium">
                  Medium
                </MenuItem>

                <MenuItem value="High">
                  High
                </MenuItem>

              </TextField>
            </Grid>


            <Grid size={{xs:12, md:6}}>
              <TextField
                fullWidth
                label="Temperature °C"
                value={formData.temperature}
                onChange={(e)=>
                  setFormData({
                    ...formData,
                    temperature:e.target.value
                  })
                }
              />
            </Grid>


            <Grid size={{xs:12, md:6}}>
              <TextField
                fullWidth
                label="Rainfall (mm)"
                value={formData.rainfall}
                onChange={(e)=>
                  setFormData({
                    ...formData,
                    rainfall:e.target.value
                  })
                }
              />
            </Grid>


            <Grid size={{xs:12, md:6}}>
              <TextField
                select
                fullWidth
                label="Fertilizer Usage"
                value={formData.fertilizer}
                onChange={(e)=>
                  setFormData({
                    ...formData,
                    fertilizer:e.target.value
                  })
                }
              >

                <MenuItem value="Low">
                  Low
                </MenuItem>

                <MenuItem value="Medium">
                  Medium
                </MenuItem>

                <MenuItem value="High">
                  High
                </MenuItem>

              </TextField>
            </Grid>


          </Grid>


          <Button
            variant="contained"
            sx={{mt:3}}
            onClick={predictYield}
          >
            Predict Yield
          </Button>


        </CardContent>

      </Card>


      {result && (

        <Card sx={{mt:3}}>

          <CardContent>

            <Typography variant="h6">
              🌾 Yield Prediction Result
            </Typography>


            <Alert severity="success" sx={{mt:2}}>

              Crop: {result.crop}
              <br/>
              📊 Expected Yield: {result.yield}
              <br/>
              🌱 Crop Condition: {result.condition}
              <br/>
              💡 Suggestion: {result.suggestion}

            </Alert>


          </CardContent>

        </Card>

      )}


    </Box>

  );

}

export default YieldPrediction;