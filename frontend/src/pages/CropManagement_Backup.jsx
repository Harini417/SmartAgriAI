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
} from "@mui/material";

import { DataGrid } from "@mui/x-data-grid";
import AddIcon from "@mui/icons-material/Add";

function CropManagement() {
  const [crops, setCrops] = useState([]);
  const [open, setOpen] = useState(false);

  const [crop, setCrop] = useState({
    cropName: "",
    cropType: "",
    plantingDate: "",
    soilType: "",
    waterRequirement: "",
    location: "",
    status: "",
  });

  const loadCrops = () => {
    fetch("http://localhost:8080/crops")
      .then((response) => response.json())
      .then((data) => {
        console.log("GET DATA:", data);
        setCrops(data);
      })
      .catch((error) => console.error(error));
  };

  useEffect(() => {
    loadCrops();
  }, []);

  const saveCrop = () => {
    console.log("Sending:", crop);

    fetch("http://localhost:8080/crops", {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        cropName: crop.cropName,
        cropType: crop.cropType,
        plantingDate: crop.plantingDate,
        soilType: crop.soilType,
        waterRequirement: Number(crop.waterRequirement),
        location: crop.location,
        status: crop.status,
      }),
    })
      .then(async (response) => {
        if (!response.ok) {
          const err = await response.text();
          throw new Error(err);
        }
        return response.json();
      })
      .then((data) => {
        console.log("Saved:", data);

        setCrops((prev) => [...prev, data]);

        setCrop({
          cropName: "",
          cropType: "",
          plantingDate: "",
          soilType: "",
          waterRequirement: "",
          location: "",
          status: "",
        });

        setOpen(false);
      })
      .catch((err) => {
        console.error(err);
        alert("Unable to save crop. Check backend console.");
      });
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
    { field: "location", headerName: "Location", width: 150 },
    { field: "status", headerName: "Status", width: 150 },
  ];

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
          onClick={() => setOpen(true)}
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

      <DataGrid
        sx={{ mt: 3 }}
        rows={crops}
        columns={columns}
        pageSizeOptions={[5]}
        initialState={{
          pagination: {
            paginationModel: {
              pageSize: 5,
            },
          },
        }}
        autoHeight
      />

      <Dialog
        open={open}
        onClose={() => setOpen(false)}
        maxWidth="sm"
        fullWidth
      >
        <DialogTitle>Add Crop</DialogTitle>

        <DialogContent>
          <TextField
            margin="normal"
            fullWidth
            label="Crop Name"
            value={crop.cropName}
            onChange={(e) =>
              setCrop({
                ...crop,
                cropName: e.target.value,
              })
            }
          />

          <TextField
            margin="normal"
            fullWidth
            label="Crop Type"
            value={crop.cropType}
            onChange={(e) =>
              setCrop({
                ...crop,
                cropType: e.target.value,
              })
            }
          />

          <TextField
            margin="normal"
            fullWidth
            type="date"
            label="Planting Date"
            InputLabelProps={{ shrink: true }}
            value={crop.plantingDate}
            onChange={(e) =>
              setCrop({
                ...crop,
                plantingDate: e.target.value,
              })
            }
          />

          <TextField
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
          />

          <TextField
            margin="normal"
            fullWidth
            type="number"
            label="Water Requirement"
            value={crop.waterRequirement}
            onChange={(e) =>
              setCrop({
                ...crop,
                waterRequirement: e.target.value,
              })
            }
          />

          <TextField
            margin="normal"
            fullWidth
            label="Location"
            value={crop.location}
            onChange={(e) =>
              setCrop({
                ...crop,
                location: e.target.value,
              })
            }
          />

          <TextField
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
          />
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
    </Box>
  );
}

export default CropManagement;