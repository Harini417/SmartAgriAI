import React, { useState } from "react";
import {
  Box,
  Card,
  CardContent,
  Typography,
  TextField,
  MenuItem,
  Button,
} from "@mui/material";

import Grid from "@mui/material/Grid";
import { useEffect } from "react";
import { cropAPI } from "../services/api";
function AIRecommendation() {

  const [formData, setFormData] = useState({
  country: "",
  state: "",
  location: "",
  soilType: "",
  season: "",
  water: "",
});
  const locationData = {
    India: {
      "Tamil Nadu": [
        "Ooty",
        "Coonoor",
        "Kotagiri",
        "Kodaikanal",
        "Yercaud",
        "Valparai",
      ],
  
      Kerala: [
        "Munnar",
        "Wayanad",
        "Idukki",
        "Thekkady",
      ],
  
      Karnataka: [
        "Coorg",
        "Chikmagalur",
        "Sakleshpur",
      ],
  
      "Himachal Pradesh": [
        "Shimla",
        "Manali",
        "Dharamshala",
      ],
  
      Uttarakhand: [
        "Mussoorie",
        "Nainital",
      ],
  
      Sikkim: [
        "Gangtok",
      ],
  
      Meghalaya: [
        "Shillong",
      ],
  
      Mizoram: [
        "Aizawl",
      ],
  
      "West Bengal": [
        "Darjeeling",
        "Kalimpong",
      ],
    },
  };
  const [recommendation, setRecommendation] = useState([]);
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


  const cropRules = () => {

    const { soilType, location, season, water } = formData;

    let result = [];


const mountainPlaces = [
  "Ooty",
  "Coonoor",
  "Kotagiri",
  "Kodaikanal",
  "Yercaud",
  "Valparai",
  "Munnar",
  "Wayanad",
  "Idukki",
  "Thekkady",
  "Coorg",
  "Chikmagalur",
  "Sakleshpur",
  "Shimla",
  "Manali",
  "Dharamshala",
  "Mussoorie",
  "Nainital",
  "Gangtok",
  "Shillong",
  "Aizawl",
  "Darjeeling",
  "Kalimpong",
];

if (
  soilType === "Mountain Soil" ||
  mountainPlaces.includes(location)
) {
  result.push({
    crop: "Tea",
    reason: "Suitable for cool climate and mountain regions.",
  });

  result.push({
    crop: "Coffee",
    reason: "Grows well in high altitude and humid conditions.",
  });

  result.push({
    crop: "Cardamom",
    reason: "Requires shade and good rainfall.",
  });
}

else if (soilType === "Black Soil") {
  result.push({
    crop: "Cotton",
    reason: "Black soil has good moisture retention.",
  });

  result.push({
    crop: "Groundnut",
    reason: "Suitable for well-drained black soil.",
  });
}

else if (soilType === "Red Soil") {
  result.push({
    crop: "Millet",
    reason: "Millets perform well in red soil.",
  });

  result.push({
    crop: "Groundnut",
    reason: "Suitable for low moisture conditions.",
  });
}

else {
  result.push({
    crop: "Vegetables",
    reason: "Suitable based on general soil conditions.",
  });

  result.push({
    crop: "Pulses",
    reason: "Requires moderate water availability.",
  });
}


    if (water === "Low") {
      result.push({
        crop: "Millet",
        reason: "Requires less water compared to other crops."
      });
    }


    setRecommendation(result);

  };


  return (

    <Box sx={{ p: 3 }}>

      <Typography variant="h4" gutterBottom>
        🤖 AI Crop Recommendation
      </Typography>


      <Card>

        <CardContent sx={{ p: 4 }}>

          <Typography variant="h6" sx={{ mb: 4 }}>
            Enter Farm Details
          </Typography>


          <Grid container spacing={3}>


            <Grid size={{ xs: 12, sm: 6 }}>
              <TextField
                select
                fullWidth
                sx={{ mt: 1 }}
                label="Soil Type"
                value={formData.soilType}
                onChange={(e)=>
                  setFormData({
                    ...formData,
                    soilType:e.target.value
                  })
                }
              >

                <MenuItem value="Mountain Soil">
                  Mountain Soil
                </MenuItem>

                <MenuItem value="Black Soil">
                  Black Soil
                </MenuItem>

                <MenuItem value="Red Soil">
                  Red Soil
                </MenuItem>

                <MenuItem value="Loamy">
                  Loamy
                </MenuItem>

              </TextField>
            </Grid>
          {/* Country */}
<Grid size={{ xs: 12, sm: 4 }}>
  <TextField
    select
    fullWidth
    label="Country"
    value={formData.country}
    onChange={(e) =>
      setFormData({
        ...formData,
        country: e.target.value,
        state: "",
        location: "",
      })
    }
  >
    <MenuItem value="India">India</MenuItem>
  </TextField>
</Grid>

{/* State */}
<Grid size={{ xs: 12, sm: 4 }}>
  <TextField
    select
    fullWidth
    label="State"
    value={formData.state}
    disabled={!formData.country}
    onChange={(e) =>
      setFormData({
        ...formData,
        state: e.target.value,
        location: "",
      })
    }
  >
    {Object.keys(locationData[formData.country] || {}).map((state) => (
      <MenuItem key={state} value={state}>
        {state}
      </MenuItem>
    ))}
  </TextField>
</Grid>

{/* Hilly Region */}
<Grid size={{ xs: 12, sm: 4 }}>
  <TextField
    select
    fullWidth
    label="Hilly Region"
    value={formData.location}
    disabled={!formData.state}
    onChange={(e) =>
      setFormData({
        ...formData,
        location: e.target.value,
      })
    }
  >
    {(locationData[formData.country]?.[formData.state] || []).map((place) => (
      <MenuItem key={place} value={place}>
        {place}
      </MenuItem>
    ))}
  </TextField>
</Grid>


           

              

               

            

          



            <Grid size={{ xs: 12, sm: 6 }}>

              <TextField
                select
                fullWidth
                sx={{ mt: 1 }}
                label="Season"
                value={formData.season}
                onChange={(e)=>
                  setFormData({
                    ...formData,
                    season:e.target.value
                  })
                }
              >

                <MenuItem value="Summer">
                  Summer
                </MenuItem>

                <MenuItem value="Winter">
                  Winter
                </MenuItem>

                <MenuItem value="Monsoon">
                  Monsoon
                </MenuItem>

              </TextField>

            </Grid>



            <Grid size={{ xs: 12, sm: 6 }}>

              <TextField
                select
                fullWidth
                sx={{ mt: 1 }}
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


          </Grid>



          <Button
            variant="contained"
            sx={{ mt:3 }}
            onClick={cropRules}
          >
            Get Recommendation
          </Button>



        </CardContent>

      </Card>
<Card sx={{ mt: 3 }}>
  <CardContent>
    <Typography variant="h6" gutterBottom>
      Saved Crops
    </Typography>

    {crops.length === 0 ? (
      <Typography>No crops available.</Typography>
    ) : (
      crops.map((crop) => (
        <Box
          key={crop.id}
          sx={{
            border: "1px solid #ddd",
            borderRadius: 2,
            p: 2,
            mb: 2,
          }}
        >
          <Typography variant="h6">
            🌱 {crop.cropName}
          </Typography>

          <Typography>
            <strong>Country:</strong> {crop.country}
          </Typography>

          <Typography>
            <strong>State:</strong> {crop.state}
          </Typography>

          <Typography>
            <strong>Hilly Region:</strong> {crop.location}
          </Typography>

          <Typography>
            <strong>Soil:</strong> {crop.soilType}
          </Typography>

          <Button
            sx={{ mt: 2 }}
            variant="contained"
            onClick={() =>
  setFormData({
    country: crop.country || "",
    state: crop.state || "",
    location: crop.location || "",
    soilType: crop.soilType || "",
    season: "Monsoon",
    water: "Medium",
  })
}
          >
            Use This Crop
          </Button>
        </Box>
      ))
    )}
  </CardContent>
</Card>


      {recommendation.length > 0 && (

        <Card sx={{ mt:3 }}>

          <CardContent>

            <Typography variant="h6">
              🌱 Recommended Crops
            </Typography>


            {recommendation.map((item,index)=>(

              <Box key={index} sx={{ mt:2 }}>

                <Typography variant="subtitle1">
                  {index+1}. {item.crop}
                </Typography>

                <Typography color="text.secondary">
                  {item.reason}
                </Typography>

              </Box>

            ))}


          </CardContent>

        </Card>

      )}


    </Box>

  );
}


export default AIRecommendation;