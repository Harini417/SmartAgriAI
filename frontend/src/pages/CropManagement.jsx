import React, { useEffect, useState } from "react";
import {
  Box,
  Button,
  Card,
  CardContent,
  Dialog,
  DialogActions,
  DialogContent,
  DialogTitle,
  TextField,
  Typography,
  MenuItem,
} from "@mui/material";
import EditIcon from "@mui/icons-material/Edit";
import IconButton from "@mui/material/IconButton";
import DeleteIcon from "@mui/icons-material/Delete";
import { DataGrid } from "@mui/x-data-grid";
import AddIcon from "@mui/icons-material/Add";
import { cropAPI } from "../services/api";
import Snackbar from "@mui/material/Snackbar";
import Alert from "@mui/material/Alert";
const cropOptions = {
  Vegetable: [
    "Tomato",
    "Potato",
    "Onion",
    "Brinjal",
    "Chilli",
    "Okra",
    "Carrot",
    "Cabbage",
    "Cauliflower",
    "Cucumber",
    "Pumpkin"
  ],

  Fruit: [
    "Mango",
    "Banana",
    "Apple",
    "Orange",
    "Guava",
    "Papaya",
    "Grapes",
    "Pomegranate",
    "Pineapple"
  ],

  Grain: [
    "Rice",
    "Wheat",
    "Maize",
    "Barley",
    "Sorghum"
  ],

  Pulse: [
    "Black Gram",
    "Green Gram",
    "Red Gram",
    "Chickpea",
    "Cowpea"
  ],

  Millet: [
    "Ragi",
    "Bajra",
    "Foxtail Millet",
    "Little Millet",
    "Kodo Millet"
  ],

  Oilseed: [
    "Groundnut",
    "Sunflower",
    "Sesame",
    "Mustard",
    "Soybean"
  ],

  Spice: [
    "Turmeric",
    "Ginger",
    "Pepper",
    "Cardamom",
    "Coriander"
  ],

  Plantation: [
    "Tea",
    "Coffee",
    "Rubber",
    "Coconut",
    "Arecanut"
  ]
};
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
function CropManagement() {
  const [crops, setCrops] = useState([]);
  const [open, setOpen] = useState(false);
  const [editing, setEditing] = useState(false);
  const [crop, setCrop] = useState({
  id: null,
  cropName: "",
  cropType: "",
  plantingDate: "",
  soilType: "",
  waterRequirement: "",
  country: "",
  state: "",
  location: "",
  status: "",
});
const [snackbarOpen, setSnackbarOpen] = useState(false);
const [snackbarMessage, setSnackbarMessage] = useState("");
const [snackbarSeverity, setSnackbarSeverity] = useState("success");
  const [searchText, setSearchText] = useState("");
  const loadCrops = async () => {
  try {
    const user = JSON.parse(localStorage.getItem("user"));

cropAPI.getUserCrops(user.id);

const response = await cropAPI.getUserCrops(user.id);
    console.log(response.data);
    setCrops(response.data);
  } catch (error) {
    console.error(error);
  }
};

  useEffect(() => {
    loadCrops();
  }, []);
const editCrop = (selectedCrop) => {
  console.log("Editing crop:", selectedCrop);

  let formattedType =
    selectedCrop.cropType
      ?.trim()
      .toLowerCase()
      .replace(/\b\w/g, (char) => char.toUpperCase()) || "";

  let formattedName =
    selectedCrop.cropName?.trim() || "";

  setCrop({
  id: selectedCrop.id,
  cropName: selectedCrop.cropName || "",
  cropType: selectedCrop.cropType || "",
  plantingDate: selectedCrop.plantingDate || "",
  soilType: selectedCrop.soilType || "",
  waterRequirement: selectedCrop.waterRequirement || "",
  country: selectedCrop.country || "",
  state: selectedCrop.state || "",
  location: selectedCrop.location || "",
  status: selectedCrop.status || "",
});

  setEditing(true);
  setOpen(true);
};
const deleteCrop = async (id) => {
  const confirmDelete = window.confirm(
    "Are you sure you want to delete this crop?"
  );

  if (!confirmDelete) return;

  try {
    await cropAPI.deleteCrop(id);
    loadCrops();
    setSnackbarMessage("Crop deleted successfully!");
setSnackbarSeverity("success");
setSnackbarOpen(true);
  } catch (error) {
  console.error("Save Crop Error:", error);
  console.error("Response:", error.response);
  console.error("Status:", error.response?.status);
  console.error("Data:", error.response?.data);

  setSnackbarMessage("Unable to delete crop.");
setSnackbarSeverity("error");
setSnackbarOpen(true);
}
};
  const saveCrop = async () => {
    const user = JSON.parse(localStorage.getItem("user"));
  try {
    const cropData = {
      cropName: crop.cropName,
      cropType: crop.cropType,
      plantingDate: crop.plantingDate,
      soilType: crop.soilType,
      waterRequirement: Number(crop.waterRequirement),

      country: crop.country,
      state: crop.state,
      location: crop.location,

      status: crop.status,
       user: {
    id: user.id,
  },

    };

    if (editing) {
      await cropAPI.updateCrop(crop.id, cropData);
    } else {
      await cropAPI.createCrop(user.id, cropData);
    }

    loadCrops();

setCrop({
  id: null,
  cropName: "",
  cropType: "",
  plantingDate: "",
  soilType: "",
  waterRequirement: "",
  country: "",
  state: "",
  location: "",
  status: "",
});

setEditing(false);
setOpen(false);

setSnackbarMessage(
  editing ? "Crop updated successfully!" : "Crop added successfully!"
);
setSnackbarSeverity("success");
setSnackbarOpen(true);

  } catch (error) {
    console.error("Status:", error.response?.status);
    console.error("Data:", error.response?.data);
    console.error("Error:", error);

    setSnackbarMessage("Unable to save crop.");
setSnackbarSeverity("error");
setSnackbarOpen(true);
  }
};

  const columns = [
    { field: "id", headerName: "ID", width: 70 },
    { field: "cropName", headerName: "Crop Name", width: 170 },
    { field: "cropType", headerName: "Crop Type", width: 150 },
    { field: "plantingDate", headerName: "Planting Date", width: 150 },
    { field: "soilType", headerName: "Soil Type", width: 150 },
    {
      field: "waterRequirement",
      headerName: "Water",
      width: 120,
    },
    {
  field: "country",
  headerName: "Country",
  width: 120,
},
{
  field: "state",
  headerName: "State",
  width: 180,
},
{
  field: "location",
  headerName: "Hilly Region",
  width: 180,
},
    { field: "status", headerName: "Status", width: 150 },
    {
  field: "actions",
  headerName: "Actions",
  width: 120,
  sortable: false,
  renderCell: (params) => (
  <>
    <IconButton onClick={() => editCrop(params.row)}>
      <EditIcon color="primary" />
    </IconButton>

    <IconButton onClick={() => deleteCrop(params.row.id)}>
      <DeleteIcon color="error" />
    </IconButton>
  </>
),
},
  ];
const filteredCrops = crops.filter((crop) => {
  const search = searchText.toLowerCase();

  return (
    crop.id?.toString().includes(search) ||
    crop.cropName?.toLowerCase().includes(search) ||
    crop.cropType?.toLowerCase().includes(search) ||
    crop.plantingDate?.toString().toLowerCase().includes(search) ||
    crop.soilType?.toLowerCase().includes(search) ||
    crop.waterRequirement?.toString().includes(search) ||
    crop.location?.toLowerCase().includes(search) ||
    crop.status?.toLowerCase().includes(search)
  );
});
  return (
    <Box sx={{ p: 3 }}>
      <Box
        sx={{
          display: "flex",
          justifyContent: "space-between",
          mb: 3,
        }}
      >
        <Typography variant="h4">
          🌾 Crop Management
        </Typography>

        <Button
          variant="contained"
          startIcon={<AddIcon />}
          onClick={() => {
  setCrop({
  id: null,
  cropName: "",
  cropType: "",
  plantingDate: "",
  soilType: "",
  waterRequirement: "",
  country: "",
  state: "",
  location: "",
  status: "",
});
  setEditing(false);
  setOpen(true);
}}
        >
          Add Crop
        </Button>
      </Box>

      <Card>
        <CardContent>
          <Typography variant="h6">
            Manage Crop Details
          </Typography>
        </CardContent>
      </Card>
<TextField
  fullWidth
  label="Search Crop"
  placeholder="Search by ID, crop name, crop type, planting date, soil type, water requirement, location or status..."
  value={searchText}
  onChange={(e) => setSearchText(e.target.value)}
  sx={{ mt: 3, mb: 2 }}
/>
      <Box sx={{ width: "100%", height: "70vh", mt: 3 }}>
  <DataGrid
    rows={filteredCrops}
    columns={columns}
    disableRowSelectionOnClick
    hideFooter
  />
</Box>

      <Dialog
  open={open}
  onClose={() => setOpen(false)}
  maxWidth="sm"
  fullWidth
>
  <DialogTitle>
    {editing ? "Edit Crop" : "Add Crop"}
  </DialogTitle>

  
    
        <DialogContent>
          <TextField
  select
  margin="normal"
  fullWidth
  label="Crop Type"
  value={crop.cropType}
  onChange={(e) =>
    setCrop({
      ...crop,
      cropType: e.target.value,
      cropName: "",
    })
  }
>
  <MenuItem value="Vegetable">Vegetable</MenuItem>
  <MenuItem value="Fruit">Fruit</MenuItem>
  <MenuItem value="Grain">Grain</MenuItem>
  <MenuItem value="Pulse">Pulse</MenuItem>
  <MenuItem value="Millet">Millet</MenuItem>
  <MenuItem value="Oilseed">Oilseed</MenuItem>
  <MenuItem value="Spice">Spice</MenuItem>
  <MenuItem value="Plantation">Plantation</MenuItem>
</TextField>


          <TextField
  select
  margin="normal"
  fullWidth
  label="Crop Name"
  value={crop.cropName}
  disabled={!crop.cropType || !cropOptions[crop.cropType]}
  onChange={(e) =>
    setCrop({
      ...crop,
      cropName: e.target.value,
      
    })
  }
>
  {(cropOptions[crop.cropType] || []).map((name) => (
    <MenuItem key={name} value={name}>
      {name}
    </MenuItem>
  ))}
</TextField>

          
         <TextField
  margin="normal"
  fullWidth
  type="date"
  value={crop.plantingDate}
  helperText="Planting Date"
  onChange={(e) =>
    setCrop({
      ...crop,
      plantingDate: e.target.value,
    })
  }
/>
          <TextField
  select
  margin="normal"
  fullWidth
  label="Soil Type"
  value={crop.soilType}
  onChange={(e) =>
    setCrop({
      ...crop,
      soilType: e.target.value,
    })
  }
>
  <MenuItem value="Loamy">Loamy</MenuItem>
  <MenuItem value="Clay">Clay</MenuItem>
  <MenuItem value="Clay Loam">Clay Loam</MenuItem>
  <MenuItem value="Sandy">Sandy</MenuItem>
  <MenuItem value="Sandy Loam">Sandy Loam</MenuItem>
  <MenuItem value="Silt">Silt</MenuItem>
  <MenuItem value="Silty Loam">Silty Loam</MenuItem>
  <MenuItem value="Red Soil">Red Soil</MenuItem>
  <MenuItem value="Black Soil">Black Soil</MenuItem>
  <MenuItem value="Laterite Soil">Laterite Soil</MenuItem>
  <MenuItem value="Mountain Soil">Mountain Soil</MenuItem>
</TextField>

          <TextField
  margin="normal"
  fullWidth
  type="number"
  label="Water Requirement (Litres/Day)"
  value={crop.waterRequirement}
  inputProps={{
    min: 0,
    step: 1,
  }}
 onChange={(e) => {
  const value = e.target.value;

  setCrop({
    ...crop,
    waterRequirement:
      value === "" ? "" : Math.max(0, Number(value)),
  });
}}
  
/>

      {/* Country */}

<TextField
  select
  margin="normal"
  fullWidth
  label="Country"
  value={crop.country}
  onChange={(e) =>
    setCrop({
      ...crop,
      country: e.target.value,
      state: "",
      location: "",
    })
  }
>
  <MenuItem value="India">India</MenuItem>
</TextField>

{/* State */}

<TextField
  select
  margin="normal"
  fullWidth
  label="State"
  value={crop.state}
  disabled={!crop.country}
  onChange={(e) =>
    setCrop({
      ...crop,
      state: e.target.value,
      location: "",
    })
  }
>
  {Object.keys(locationData[crop.country] || {}).map((state) => (
    <MenuItem key={state} value={state}>
      {state}
    </MenuItem>
  ))}
</TextField>

{/* Hilly Region */}

{/* Hilly Region */}

<TextField
  select
  margin="normal"
  fullWidth
  label="Hilly Region"
  value={crop.location}
  disabled={!crop.state}
  onChange={(e) =>
    setCrop({
      ...crop,
      location: e.target.value,
    })
  }
>
  {(locationData[crop.country]?.[crop.state] || []).map((place) => (
    <MenuItem key={place} value={place}>
      {place}
    </MenuItem>
  ))}
</TextField>

{/* Status */}

<TextField
  select
  margin="normal"
  fullWidth
  label="Status"
  value={crop.status}
  onChange={(e) =>
    setCrop({
      ...crop,
      status: e.target.value,
    })
  }
>
  <MenuItem value="Growing">Growing</MenuItem>
  <MenuItem value="Active">Active</MenuItem>
  <MenuItem value="Harvest Ready">Harvest Ready</MenuItem>
  <MenuItem value="Inactive">Inactive</MenuItem>
</TextField>

          
        </DialogContent>

        <DialogActions>
          <Button onClick={() => setOpen(false)}>
            Cancel
          </Button>

          <Button
            variant="contained"
            onClick={saveCrop}
          >
            Save Crop
          </Button>
        </DialogActions>
            </Dialog>

      <Snackbar
        open={snackbarOpen}
        autoHideDuration={3000}
        onClose={() => setSnackbarOpen(false)}
        anchorOrigin={{ vertical: "top", horizontal: "right" }}
      >
        <Alert
          onClose={() => setSnackbarOpen(false)}
          severity={snackbarSeverity}
          variant="filled"
          sx={{ width: "100%" }}
        >
          {snackbarMessage}
        </Alert>
      </Snackbar>

    </Box>
  );
}

export default CropManagement;