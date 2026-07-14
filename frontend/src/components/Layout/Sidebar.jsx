import React from 'react';
import {
  Drawer,
  List,
  ListItem,
  ListItemButton,
  ListItemIcon,
  ListItemText,
  Divider,
  Box,
  Typography,
  useMediaQuery,
  useTheme,
} from '@mui/material';
import { useNavigate, useLocation } from 'react-router-dom';
import {
  LayoutDashboard,
  Leaf,
  Droplets,
  Cloud,
  Lightbulb,
  Droplet,
  TrendingUp,
  BarChart3,
  LogOut,
  Settings,
} from 'lucide-react';

const menuItems = [
  { label: 'Dashboard', path: '/dashboard', icon: LayoutDashboard },
  { label: 'Crop Management', path: '/crops', icon: Leaf },
  { label: 'Soil Monitoring', path: '/soil-monitoring', icon: Droplets },
  {
  label: "Weather",
  path: "/weather",
  icon: Cloud,
},
  { label: 'AI Recommendation', path: '/recommendation', icon: Lightbulb },
  { label: 'Smart Irrigation', path: '/irrigation', icon: Droplet },
  { label: 'Yield Prediction', path: '/yield', icon: TrendingUp },
  { label: 'Analytics', path: '/analytics', icon: BarChart3 },
];

export const Sidebar = ({ open, onClose }) => {
  const navigate = useNavigate();
  const location = useLocation();
  const theme = useTheme();
  const isMobile = useMediaQuery(theme.breakpoints.down('md'));

  const handleLogout = () => {
    localStorage.removeItem('token');
    navigate('/login');
  };

  const drawerContent = (
    <Box sx={{ display: 'flex', flexDirection: 'column', height: '100%' }}>
      {/* Logo Section */}
      <Box sx={{ p: 2, textAlign: 'center', borderBottom: `1px solid ${theme.palette.divider}` }}>
        <Typography
          variant="h6"
          sx={{
            fontWeight: 700,
            color: theme.palette.primary.main,
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            gap: 1,
          }}
        >
          <Leaf size={24} />
          SmartAgriAI
        </Typography>
        <Typography variant="caption" sx={{ color: theme.palette.text.secondary }}>
          Farm Management System
        </Typography>
      </Box>

      {/* Menu Items */}
      <List sx={{ flex: 1, pt: 2 }}>
        {menuItems.map((item) => {
          const Icon = item.icon;
          const isActive = location.pathname === item.path;

          return (
            <ListItem key={item.path} disablePadding sx={{ mb: 0.5, px: 1 }}>
             <ListItemButton
  onClick={() => {
    navigate(item.path);
    if (isMobile) onClose();
  }}
  sx={{
    borderRadius: 1,
    backgroundColor: isActive ? "rgba(46,125,50,0.1)" : "transparent",
    color: isActive
      ? theme.palette.primary.main
      : theme.palette.text.primary,
    "&:hover": {
      backgroundColor: isActive
        ? "rgba(46,125,50,0.15)"
        : "rgba(0,0,0,0.04)",
    },
  }}
>
  <ListItemIcon
    sx={{
      minWidth: 40,
      color: isActive
        ? theme.palette.primary.main
        : theme.palette.text.secondary,
    }}
  >
    <Icon size={20} />
  </ListItemIcon>

  <ListItemText
    primary={item.label}
    primaryTypographyProps={{
      variant: "body2",
      fontWeight: isActive ? 600 : 500,
    }}
  />
</ListItemButton>
            </ListItem>
          );
        })}
      </List>

      {/* Footer Section */}
      <Box sx={{ borderTop: `1px solid ${theme.palette.divider}`, p: 2 }}>
        <Divider sx={{ mb: 2 }} />
        <List disablePadding>
          <ListItem disablePadding sx={{ mb: 1 }}>
            <ListItemButton
  onClick={() => {
    navigate("/settings");
    if (isMobile) onClose();
  }}
  sx={{
    borderRadius: 1,
    backgroundColor:
      location.pathname === "/settings"
        ? "rgba(46,125,50,0.1)"
        : "transparent",
    "&:hover": {
      backgroundColor:
        location.pathname === "/settings"
          ? "rgba(46,125,50,0.15)"
          : "rgba(0,0,0,0.04)",
    },
  }}
>
             <ListItemIcon
  sx={{
    minWidth: 40,
    color:
      location.pathname === "/settings"
        ? theme.palette.primary.main
        : theme.palette.text.secondary,
  }}
> 
                <Settings size={20} />
              </ListItemIcon>
              <ListItemText
                primary="Settings"
                primaryTypographyProps={{ variant: 'body2' }}
              />
            </ListItemButton>
          </ListItem>
          <ListItem disablePadding>
            <ListItemButton
              onClick={handleLogout}
              sx={{
                borderRadius: 1,
                color: theme.palette.error.main,
                '&:hover': {
                  backgroundColor: 'rgba(244, 67, 54, 0.04)',
                },
              }}
            >
              <ListItemIcon sx={{ minWidth: 40, color: theme.palette.error.main }}>
                <LogOut size={20} />
              </ListItemIcon>
              <ListItemText
                primary="Logout"
                primaryTypographyProps={{ variant: 'body2' }}
              />
            </ListItemButton>
          </ListItem>
        </List>
      </Box>
    </Box>
  );

  return (
    <Drawer
      variant={isMobile ? 'temporary' : 'permanent'}
      open={isMobile ? open : true}
      onClose={onClose}
      sx={{
        width: 280,
        flexShrink: 0,
        '& .MuiDrawer-paper': {
          width: 280,
          boxSizing: 'border-box',
          backgroundColor: theme.palette.background.paper,
          borderRight: `1px solid ${theme.palette.divider}`,
        },
      }}
    >
      {drawerContent}
    </Drawer>
  );
};

export default Sidebar;