import Snackbar from "@mui/material/Snackbar";
import Alert from "@mui/material/Alert";
import React, { useState } from "react";
import {
  Avatar,
  Box,
  Card,
  CardContent,
  Typography,
  Grid,
  TextField,
  MenuItem,
  Switch,
  FormControlLabel,
  Button,
  Divider,
   Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
} from "@mui/material";
import SaveIcon from "@mui/icons-material/Save";
import PersonIcon from "@mui/icons-material/Person";
import SettingsSuggestIcon from "@mui/icons-material/SettingsSuggest";
import NotificationsIcon from "@mui/icons-material/Notifications";
import SecurityIcon from "@mui/icons-material/Security";
import { useThemeMode } from "../context/ThemeContext";
import {
  useNavigate,
} from "react-router-dom";
function Settings() {
  const { mode, toggleTheme } = useThemeMode();
  const navigate = useNavigate();
const user = JSON.parse(localStorage.getItem("user"));
  const [settings, setSettings] = useState({
    name: user?.name || "",
    email: user?.email || "",
    phone: user?.phone || "",
    weatherAlerts: true,
    irrigationAlerts: true,
    harvestAlerts: true,
    diseaseAlerts: true,
});
const [snackbarOpen, setSnackbarOpen] = useState(false);
const [snackbarMessage, setSnackbarMessage] = useState("");
const [snackbarSeverity, setSnackbarSeverity] = useState("success");
  const [profileImage, setProfileImage] = useState(null);

  const [openPassword, setOpenPassword] = useState(false);

  const [passwordData, setPasswordData] = useState({
    oldPassword: "",
    newPassword: "",
    confirmPassword: "",
  });

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    navigate("/login");
  };

  const handleChange = (e) => {
    setSettings({
      ...settings,
      [e.target.name]: e.target.value,
    });
  };

  const handleSwitch = (e) => {
    setSettings({
      ...settings,
      [e.target.name]: e.target.checked,
    });
  };

  const handleImageUpload = (event) => {
    const file = event.target.files[0];

    if (file) {
      setProfileImage(URL.createObjectURL(file));
    }
  };
const saveSettings = () => {
  const updatedUser = {
    ...user,
    name: settings.name,
    email: settings.email,
    phone: settings.phone,
  };

  localStorage.setItem("user", JSON.stringify(updatedUser));

  setSnackbarMessage("Settings saved successfully!");
  setSnackbarSeverity("success");
  setSnackbarOpen(true);

  setTimeout(() => {
    window.location.reload();
  }, 1000);
};
  

  return (
    <Box sx={{ p: 3 }}>
      <Typography variant="h4" fontWeight="bold" mb={3}>
        ⚙️ Settings
      </Typography>

      <Grid container spacing={3}>

  
              <Grid item xs={12} md={6}>
          <Card sx={{ borderRadius: 3 }}>
            <CardContent>
              <Typography
                variant="h6"
                sx={{ display: "flex", alignItems: "center", gap: 1 }}
              >
                <PersonIcon color="primary" />
                Profile
              </Typography>

              <Divider sx={{ my: 2 }} />
<Box
  sx={{
    display: "flex",
    flexDirection: "column",
    alignItems: "center",
    mb: 3,
  }}
>
  <Avatar
    src={profileImage}
    sx={{
      width: 90,
      height: 90,
      mb: 2,
    }}
  />

  <Button
    variant="outlined"
    component="label"
  >
    Upload Photo

    <input
      hidden
      accept="image/*"
      type="file"
      onChange={handleImageUpload}
    />
  </Button>
</Box>
              <TextField
                fullWidth
                margin="normal"
                label="Full Name"
                name="name"
                value={settings.name}
                onChange={handleChange}
              />

              <TextField
                fullWidth
                margin="normal"
                label="Email"
                name="email"
                value={settings.email}
                onChange={handleChange}
              />

              <TextField
  fullWidth
  margin="normal"
  label="Phone Number"
  name="phone"
  value={settings.phone}
  onChange={handleChange}
  inputProps={{
    maxLength: 10,
  }}
  error={
    settings.phone !== "" &&
    !/^[6-9]\d{9}$/.test(settings.phone)
  }
  helperText={
    settings.phone !== "" &&
    !/^[6-9]\d{9}$/.test(settings.phone)
      ? "Enter a valid 10-digit Indian mobile number"
      : ""
  }
/>
            </CardContent>
          </Card>
        </Grid>
                <Grid item xs={12} md={6}>
          <Card sx={{ borderRadius: 3 }}>
            <CardContent>
              <Typography
                variant="h6"
                sx={{ display: "flex", alignItems: "center", gap: 1 }}
              >
                <SettingsSuggestIcon color="success" />
                Preferences
              </Typography>

              <Divider sx={{ my: 2 }} />

              <TextField
  select
  fullWidth
  margin="normal"
  label="Theme"
  value={mode === "light" ? "Light" : "Dark"}
  onChange={(e) =>
    toggleTheme(
      e.target.value === "Light"
        ? "light"
        : "dark"
    )
  }
>
  <MenuItem value="Light">Light</MenuItem>
  <MenuItem value="Dark">Dark</MenuItem>
</TextField>

            </CardContent>
          </Card>
        </Grid>
     <Grid item xs={12} md={6}>
  <Card sx={{ borderRadius: 3 }}>
    <CardContent>
      <Typography
        variant="h6"
        sx={{ display: "flex", alignItems: "center", gap: 1 }}
      >
        <NotificationsIcon color="warning" />
        Notifications
      </Typography>

      <Divider sx={{ my: 2 }} />

      <FormControlLabel
        control={
          <Switch
            checked={settings.weatherAlerts}
            onChange={handleSwitch}
            name="weatherAlerts"
          />
        }
        label="Weather Alerts"
      />

      <FormControlLabel
        control={
          <Switch
            checked={settings.irrigationAlerts}
            onChange={handleSwitch}
            name="irrigationAlerts"
          />
        }
        label="Irrigation Alerts"
      />

      <FormControlLabel
        control={
          <Switch
            checked={settings.harvestAlerts}
            onChange={handleSwitch}
            name="harvestAlerts"
          />
        }
        label="Harvest Alerts"
      />

      <FormControlLabel
        control={
          <Switch
            checked={settings.diseaseAlerts}
            onChange={handleSwitch}
            name="diseaseAlerts"
          />
        }
        label="Disease Alerts"
      />
    </CardContent>
  </Card>
</Grid>
   <Grid item xs={12} md={6}>
  <Card sx={{ borderRadius: 3 }}>
    <CardContent>
      <Typography
        variant="h6"
        sx={{ display: "flex", alignItems: "center", gap: 1 }}
      >
        <SecurityIcon color="error" />
        Security
      </Typography>

      <Divider sx={{ my: 2 }} />
<Button
  fullWidth
  variant="outlined"
  sx={{ mb: 2 }}
  onClick={() => setOpenPassword(true)}
>
  Change Password
</Button>
      

      <Button
  fullWidth
  color="error"
  variant="outlined"
  onClick={handleLogout}
>
  Logout
</Button>
    </CardContent>
  </Card>
</Grid>
</Grid>
<Box
  sx={{
    mt: 4,
    display: "flex",
    justifyContent: "center",
  }}
>
  <Button
    variant="contained"
    size="large"
    startIcon={<SaveIcon />}
    onClick={saveSettings}
  >
    Save Settings
  </Button>
</Box>
<Dialog
  open={openPassword}
  onClose={() => setOpenPassword(false)}
  fullWidth
>
  <DialogTitle>Change Password</DialogTitle>

  <DialogContent>

    <TextField
      margin="normal"
      fullWidth
      label="Current Password"
      type="password"
      value={passwordData.oldPassword}
      onChange={(e) =>
        setPasswordData({
          ...passwordData,
          oldPassword: e.target.value,
        })
      }
    />

    <TextField
      margin="normal"
      fullWidth
      label="New Password"
      type="password"
      value={passwordData.newPassword}
      onChange={(e) =>
        setPasswordData({
          ...passwordData,
          newPassword: e.target.value,
        })
      }
    />

    <TextField
      margin="normal"
      fullWidth
      label="Confirm Password"
      type="password"
      value={passwordData.confirmPassword}
      onChange={(e) =>
        setPasswordData({
          ...passwordData,
          confirmPassword: e.target.value,
        })
      }
    />

  </DialogContent>

  <DialogActions>

    <Button onClick={() => setOpenPassword(false)}>
      Cancel
    </Button>

    <Button
      variant="contained"
      onClick={() => {
        if (
          passwordData.newPassword !==
          passwordData.confirmPassword
        ) {
          alert("Passwords do not match");
          return;
        }

        alert("Password Changed Successfully");

        setOpenPassword(false);

        setPasswordData({
          oldPassword: "",
          newPassword: "",
          confirmPassword: "",
        });
      }}
    >
      Save
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

export default Settings;