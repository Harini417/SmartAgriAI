import React, { useState, useEffect } from "react";
import {
  AppBar,
  Toolbar,
  Box,
  IconButton,
  Avatar,
  Menu,
  MenuItem,
  Typography,
  useTheme,
  Badge,
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  Button,
} from "@mui/material";
import {
  Menu as MenuIcon,
  Bell,
  User,
  LogOut,
  Settings,
} from "lucide-react";
import { useNavigate } from "react-router-dom";
import { cropAPI } from "../../services/api";

export const Navbar = ({ onMenuClick }) => {
  const theme = useTheme();
  const navigate = useNavigate();

  const [anchorEl, setAnchorEl] = useState(null);
  const [notificationOpen, setNotificationOpen] = useState(false);
  const [crops, setCrops] = useState([]);

  const user = JSON.parse(localStorage.getItem("user"));

  useEffect(() => {
    const loadCrops = async () => {
      try {
        if (!user) return;

        const response = await cropAPI.getUserCrops(user.id);
        setCrops(response.data);
      } catch (error) {
        console.error(error);
      }
    };

    loadCrops();
  }, []);

  const handleMenuOpen = (e) => setAnchorEl(e.currentTarget);

  const handleMenuClose = () => setAnchorEl(null);

  const handleLogout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("user");
    navigate("/login");
  };

  // Generate Notifications
  const notifications = [];

  crops.forEach((crop) => {
    if (crop.status === "Harvest Ready") {
      notifications.push(
        `🌾 ${crop.cropName} is ready for harvest.`
      );
    }

    if (crop.waterRequirement > 50) {
      notifications.push(
        `💧 ${crop.cropName} requires ${crop.waterRequirement} L/day of water.`
      );
    }

    if (crop.status === "Inactive") {
      notifications.push(
        `⚠ ${crop.cropName} is inactive.`
      );
    }
  });

  return (
    <>
      <AppBar
        position="sticky"
        sx={{
          backgroundColor: "#ffffff",
          color: "#212121",
        }}
      >
        <Toolbar
          sx={{
            justifyContent: "space-between",
            minHeight: 64,
          }}
        >
          {/* Left */}

          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              gap: 2,
            }}
          >
            <IconButton
              onClick={onMenuClick}
              sx={{
                display: {
                  xs: "block",
                  md: "none",
                },
                color: theme.palette.primary.main,
              }}
            >
              <MenuIcon size={24} />
            </IconButton>
          </Box>

          {/* Right */}

          <Box
            sx={{
              display: "flex",
              alignItems: "center",
              gap: 1,
            }}
          >
            {/* Notification */}

            <IconButton
              onClick={() => setNotificationOpen(true)}
              sx={{
                color: theme.palette.text.primary,
              }}
            >
              <Badge
                badgeContent={notifications.length}
                color="error"
              >
                <Bell size={20} />
              </Badge>
            </IconButton>

            {/* User */}

            <Box
              onClick={handleMenuOpen}
              sx={{
                display: "flex",
                alignItems: "center",
                gap: 1.5,
                p: 0.5,
                cursor: "pointer",
                borderRadius: 1,
                "&:hover": {
                  backgroundColor: "rgba(0,0,0,0.05)",
                },
              }}
            >
              <Avatar
                sx={{
                  width: 36,
                  height: 36,
                  backgroundColor:
                    theme.palette.primary.main,
                }}
              >
                {user?.name?.charAt(0).toUpperCase()}
              </Avatar>

              <Box
                sx={{
                  display: {
                    xs: "none",
                    sm: "block",
                  },
                }}
              >
                <Typography
                  variant="body2"
                  fontWeight={600}
                >
                  {user?.name}
                </Typography>

                <Typography variant="caption">
                  {user?.email}
                </Typography>
              </Box>
            </Box>

            {/* Profile Menu */}

            <Menu
              anchorEl={anchorEl}
              open={Boolean(anchorEl)}
              onClose={handleMenuClose}
            >
              <MenuItem
                onClick={() => {
                  handleMenuClose();
                  navigate("/profile");
                }}
              >
                <User
                  size={18}
                  style={{ marginRight: 10 }}
                />
                Profile
              </MenuItem>

              <MenuItem
                onClick={() => {
                  handleMenuClose();
                  navigate("/settings");
                }}
              >
                <Settings
                  size={18}
                  style={{ marginRight: 10 }}
                />
                Settings
              </MenuItem>

              <MenuItem
                onClick={() => {
                  handleMenuClose();
                  handleLogout();
                }}
              >
                <LogOut
                  size={18}
                  style={{ marginRight: 10 }}
                />
                Logout
              </MenuItem>
            </Menu>
          </Box>
        </Toolbar>
      </AppBar>

      {/* Notification Dialog */}

      <Dialog
        open={notificationOpen}
        onClose={() => setNotificationOpen(false)}
        fullWidth
        maxWidth="sm"
      >
        <DialogTitle>
          Crop Notifications
        </DialogTitle>

        <DialogContent dividers>
          {notifications.length === 0 ? (
            <Typography>
              ✅ No notifications.
            </Typography>
          ) : (
            notifications.map((notification, index) => (
              <Typography
                key={index}
                sx={{ mb: 2 }}
              >
                {notification}
              </Typography>
            ))
          )}
        </DialogContent>

        <DialogActions>
          <Button
            onClick={() =>
              setNotificationOpen(false)
            }
          >
            Close
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
};

export default Navbar;