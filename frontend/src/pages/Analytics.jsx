import React from "react";
import {
  Box,
  Card,
  CardContent,
  Typography,
  Grid,
} from "@mui/material";
import { useEffect, useState } from "react";
import { cropAPI } from "../services/api";
function Analytics() {
      const [crops, setCrops] = useState([]);

  useEffect(() => {
    loadCrops();
  }, []);


  const loadCrops = async () => {
    try {
      const response = await cropAPI.getAllCrops();
      setCrops(response.data);
    } catch (error) {
      console.error("Error loading crops:", error);
    }
  };

  return (
    <Box sx={{ p: 3 }}>

      <Typography variant="h4" gutterBottom>
        📊 Farm Analytics Dashboard
      </Typography>


      <Grid container spacing={3}>

        <Grid size={{ xs: 12, md: 4 }}>
          <Card>
            <CardContent>

              <Typography variant="h6">
                🌱 Total Crops
              </Typography>

              <Typography variant="h4">
                {crops.length}
              </Typography>

            </CardContent>
          </Card>
        </Grid>
<Grid size={{ xs: 12, md: 4 }}>
  <Card>
    <CardContent>

      <Typography variant="h6">
        ✅ Healthy Crops
      </Typography>

      <Typography variant="h4">
        {
          crops.filter(
            crop =>
              crop.status === "Growing" ||
              crop.status === "Active" ||
              crop.status === "Harvest Ready"
          ).length
        }
      </Typography>

    </CardContent>
  </Card>
</Grid>


<Grid size={{ xs: 12, md: 4 }}>
  <Card>
    <CardContent>

      <Typography variant="h6">
        ⚠️ Need Attention
      </Typography>

      <Typography variant="h4">
        {
          crops.filter(
            crop => crop.status === "Inactive"
          ).length
        }
      </Typography>

    </CardContent>
  </Card>
</Grid>

       

      </Grid>



      <Card sx={{ mt: 3 }}>

        <CardContent>

          <Typography variant="h6">
            📈 Farm Performance
          </Typography>


          <Typography sx={{ mt: 2 }}>
            Crop Growth Status: Monitoring
          </Typography>


          <Typography>
            Irrigation Efficiency: Not Available
          </Typography>


          <Typography>
            Yield Prediction Status: Available
          </Typography>


        </CardContent>

      </Card>



      <Card sx={{ mt: 3 }}>

        <CardContent>

          <Typography variant="h6">
            🌾 Smart Agriculture Insights
          </Typography>


          <Typography sx={{ mt: 2 }}>
            • Monitor crop health regularly
          </Typography>

          <Typography>
            • Maintain proper irrigation schedules
          </Typography>

          <Typography>
            • Improve yield using AI recommendations
          </Typography>


        </CardContent>

      </Card>


    </Box>
  );
}


export default Analytics;