import React, { useState, useEffect } from "react";
import {
  Box,
  Grid,
  Card,
  CardContent,
  Typography,
  Skeleton,
  useTheme,
} from "@mui/material";
import { motion } from "framer-motion";
import {
  Cloud,
  Droplets,
  Leaf,
  Activity,
  TrendingUp,
  AlertCircle,
} from "lucide-react";
import {
  ResponsiveContainer,
  LineChart,
  Line,
  AreaChart,
  Area,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
} from "recharts";
import { dashboardAPI, cropAPI } from "../services/api";
import { useNavigate } from "react-router-dom";
const rainfallData = [
  { month: "Jan", rainfall: 45 },
  { month: "Feb", rainfall: 52 },
  { month: "Mar", rainfall: 68 },
  { month: "Apr", rainfall: 85 },
  { month: "May", rainfall: 92 },
  { month: "Jun", rainfall: 78 },
];
const StatCard = ({
  icon: Icon,
  label,
  value,
  unit,
  color,
  loading,
}) => {
  const theme = useTheme();

  return (
    <motion.div
      whileHover={{ y: -4 }}
      transition={{ duration: 0.3 }}
      style={{ width: "100%" }}
    >
      <Card
        sx={{
          borderRadius: 2,
          height: "100%",
          backgroundColor: "#fff",
        }}
      >
        <CardContent sx={{ p: 3 }}>
          <Box
            display="flex"
            justifyContent="space-between"
            alignItems="flex-start"
          >
            <Box>
              <Typography
                variant="body2"
                color={theme.palette.text.secondary}
              >
                {label}
              </Typography>

              {loading ? (
                <Skeleton width={80} />
              ) : (
                <Typography variant="h4" fontWeight={700}>
                  {value}
                  <Typography
                    component="span"
                    variant="body2"
                    sx={{ ml: 0.5 }}
                  >
                    {unit}
                  </Typography>
                </Typography>
              )}
            </Box>

            <Box
              sx={{
                p: 1.5,
                borderRadius: 2,
                bgcolor: `${color}20`,
              }}
            >
              <Icon size={24} color={color} />
            </Box>
          </Box>
        </CardContent>
      </Card>
    </motion.div>
  );
};
export const Dashboard = () => {
  const theme = useTheme();
  const navigate = useNavigate();

  const [dashboardData, setDashboardData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [crops, setCrops] = useState([]);
  const [chartData, setChartData] = useState([]);
    useEffect(() => {
    const fetchDashboard = async () => {
      try {
        const user = JSON.parse(localStorage.getItem("user"));

        const dashboardResponse =
          await dashboardAPI.getAnalytics(user.id);

        setDashboardData(dashboardResponse.data);

        const cropResponse =
          await cropAPI.getUserCrops(user.id);

        setCrops(cropResponse.data);

        const months = [
          { month: "Jan", production: 0 },
          { month: "Feb", production: 0 },
          { month: "Mar", production: 0 },
          { month: "Apr", production: 0 },
          { month: "May", production: 0 },
          { month: "Jun", production: 0 },
          { month: "Jul", production: 0 },
          { month: "Aug", production: 0 },
          { month: "Sep", production: 0 },
          { month: "Oct", production: 0 },
          { month: "Nov", production: 0 },
          { month: "Dec", production: 0 },
        ];

        cropResponse.data.forEach((crop) => {
          if (crop.plantingDate) {
            const month =
              new Date(crop.plantingDate).getMonth();

            months[month].production++;
          }
        });

        setChartData(months);

      } catch (error) {
        console.error(error);
      } finally {
        setLoading(false);
      }
    };

    fetchDashboard();
  }, []);
  return (
  <Box>
    {/* Header */}
    <Box sx={{ mb: 4 }}>
      <Typography variant="h4" fontWeight={700}>
        Dashboard
      </Typography>

      <Typography
        variant="body2"
        color={theme.palette.text.secondary}
      >
        Welcome back! Here's your farm overview.
      </Typography>
    </Box>

    {/* Statistics */}
    <Grid container spacing={2} sx={{ mb: 4 }}>
      <Grid item xs={12} sm={6} md={3}>
        <StatCard
          icon={Leaf}
          label="Total Crops"
          value={dashboardData?.totalCrops || 0}
          unit=""
          color="#2e7d32"
          loading={loading}
        />
      </Grid>

      <Grid item xs={12} sm={6} md={3}>
        <StatCard
          icon={Droplets}
          label="Soil Records"
          value={dashboardData?.totalSoilRecords || 0}
          unit=""
          color="#00897b"
          loading={loading}
        />
      </Grid>

      <Grid item xs={12} sm={6} md={3}>
        <StatCard
          icon={Cloud}
          label="Avg Temperature"
          value={(dashboardData?.averageTemperature || 0).toFixed(1)}
          unit="°C"
          color="#ff9800"
          loading={loading}
        />
      </Grid>

      <Grid item xs={12} sm={6} md={3}>
        <StatCard
          icon={Activity}
          label="Soil Moisture"
          value={(dashboardData?.averageMoisture || 0).toFixed(0)}
          unit="%"
          color="#1976d2"
          loading={loading}
        />
      </Grid>

      <Grid item xs={12} sm={6} md={3}>
        <StatCard
          icon={Leaf}
          label="Healthy Soil"
          value={dashboardData?.healthySoilCount || 0}
          unit=""
          color="#43a047"
          loading={loading}
        />
      </Grid>

      <Grid item xs={12} sm={6} md={3}>
        <StatCard
          icon={AlertCircle}
          label="Unhealthy Soil"
          value={dashboardData?.unhealthySoilCount || 0}
          unit=""
          color="#d32f2f"
          loading={loading}
        />
      </Grid>
    </Grid>

    {/* Charts */}
    <Grid container spacing={3}>
      <Grid item xs={12} md={8}>
        <Card sx={{ borderRadius: 2 }}>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              Monthly Crop Production
            </Typography>

            {loading ? (
              <Skeleton variant="rectangular" height={300} />
            ) : (
              <ResponsiveContainer width="100%" height={300}>
                <AreaChart data={chartData}>
                  <CartesianGrid strokeDasharray="3 3" />
                  <XAxis dataKey="month" />
                  <YAxis allowDecimals={false} />
                  <Tooltip />
                  <Legend />
                  <Area
                    type="monotone"
                    dataKey="production"
                    stroke="#4caf50"
                    fill="#81c784"
                  />
                </AreaChart>
              </ResponsiveContainer>
            )}
          </CardContent>
        </Card>
      </Grid>

      <Grid item xs={12} md={4}>
        <Card sx={{ borderRadius: 2 }}>
          <CardContent>
            <Typography variant="h6" gutterBottom>
              Rainfall Trend
            </Typography>

            {loading ? (
              <Skeleton variant="rectangular" height={300} />
            ) : (
              <ResponsiveContainer width="100%" height={300}>
                <LineChart data={rainfallData}>
                  <CartesianGrid strokeDasharray="3 3" />
                  <XAxis dataKey="month" />
                  <YAxis />
                  <Tooltip />
                  <Legend />
                  <Line
                    type="monotone"
                    dataKey="rainfall"
                    stroke="#2196f3"
                    strokeWidth={3}
                  />
                </LineChart>
              </ResponsiveContainer>
            )}
          </CardContent>
        </Card>
      </Grid>
    </Grid>
  </Box>
);
};

export default Dashboard;