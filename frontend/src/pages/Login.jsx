import React, { useState } from 'react';
import {
  Box,
  Container,
  Card,
  TextField,
  Button,
  Typography,
  Alert,
  CircularProgress,
} from '@mui/material';
import { useNavigate, Link } from 'react-router-dom';
import { motion } from 'framer-motion';
import { Leaf } from 'lucide-react';
import axios from "axios";
export const Login = () => {
  const navigate = useNavigate();
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleLogin = async (e) => {
  e.preventDefault();
  setError("");

  if (!email || !password) {
    setError("Please fill in all fields");
    return;
  }

  if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
    setError("Please enter a valid email");
    return;
  }

  try {
    setLoading(true);

    const response = await axios.post(
      "http://localhost:8080/auth/login",
      {
        email,
        password,
      }
    );

    localStorage.setItem("token", "loggedin");
    localStorage.setItem(
      "user",
      JSON.stringify(response.data)
    );

    navigate("/dashboard");
    window.location.reload();

  } catch (err) {
    setError(
      err.response?.data?.message || "Invalid email or password"
    );
  } finally {
    setLoading(false);
  }
};

  return (
    <Box
      sx={{
        minHeight: '100vh',
        background: 'linear-gradient(135deg, #2e7d32 0%, #1b5e20 100%)',
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'center',
        p: 2,
      }}
    >
      <Container maxWidth="sm">
        <motion.div
          initial={{ opacity: 0, y: 20 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.5 }}
        >
          <Card
            sx={{
              p: 4,
              borderRadius: 2,
              boxShadow: '0 20px 60px rgba(0, 0, 0, 0.3)',
            }}
          >
            {/* Logo */}
            {/* Logo */}
<Box sx={{ textAlign: "center", mb: 4 }}>
  <Box sx={{ display: "flex", justifyContent: "center", mb: 2 }}>
    <Box
      sx={{
        p: 1.5,
        backgroundColor: "rgba(46,125,50,0.1)",
        borderRadius: 2,
      }}
    >
      <Leaf size={32} color="#2e7d32" />
    </Box>
  </Box>

  <Typography variant="h4" fontWeight="bold" gutterBottom>
    Welcome Back
  </Typography>

  <Typography
    variant="body1"
    color="text.secondary"
    sx={{ mb: 1 }}
  >
    Login to your SmartAgriAI account
  </Typography>

  <Typography
    variant="body2"
    color="text.secondary"
  >
    SmartAgriAI Farm Management System
  </Typography>
</Box>

            {/* Error Alert */}
            {error && (
              <Alert severity="error" sx={{ mb: 2 }}>
                {error}
              </Alert>
            )}

            {/* Form */}
            <Box component="form" onSubmit={handleLogin}>
              <TextField
                fullWidth
                label="Email"
                type="email"
                value={email}
                onChange={(e) => setEmail(e.target.value)}
                margin="normal"
                variant="outlined"
                placeholder="farmer@example.com"
              />

              <TextField
                fullWidth
                label="Password"
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                margin="normal"
                variant="outlined"
                placeholder="Enter your password"
              />

              <Button
  fullWidth
  variant="contained"
  size="large"
  type="submit"
  disabled={loading}
  sx={{ mt: 3, mb: 2 }}
>
  {loading ? <CircularProgress size={24} /> : "Login"}
</Button>

            
              <Typography align="center" sx={{ mt: 2 }}>
  Don't have an account?{" "}
  <Link
    to="/register"
    style={{
      color: "#2e7d32",
      textDecoration: "none",
      fontWeight: "bold",
    }}
  >
    Sign Up
  </Link>
</Typography>
            </Box>
          </Card>
        </motion.div>
      </Container>
    </Box>
  );
};

export default Login;
