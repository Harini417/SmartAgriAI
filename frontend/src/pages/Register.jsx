import React, { useState } from "react";
import {
  Box,
  Button,
  Card,
  CardContent,
  TextField,
  Typography,
} from "@mui/material";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";

function Register() {
  const navigate = useNavigate();

  const [user, setUser] = useState({
    name: "",
    email: "",
    password: "",
  });

  const handleChange = (e) => {
    setUser({
      ...user,
      [e.target.name]: e.target.value,
    });
  };

  const register = async () => {
    try {
      await axios.post("http://localhost:8080/auth/register", user);

      alert("Registration Successful");

      navigate("/login");
    } catch (error) {
      alert(error.response?.data?.message || "Registration Failed");
    }
  };

  return (
    <Box
  sx={{
    minHeight: "100vh",
    background: "linear-gradient(135deg, #2e7d32 0%, #1b5e20 100%)",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    p: 2,
  }}
>
      <Card sx={{ width: 420 }}>
        <CardContent>

          <Typography
            variant="h4"
            align="center"
            gutterBottom
          >
            Sign Up
          </Typography>

          <TextField
            fullWidth
            margin="normal"
            label="Full Name"
            name="name"
            value={user.name}
            onChange={handleChange}
          />

          <TextField
            fullWidth
            margin="normal"
            label="Email"
            name="email"
            value={user.email}
            onChange={handleChange}
          />

          <TextField
            fullWidth
            type="password"
            margin="normal"
            label="Password"
            name="password"
            value={user.password}
            onChange={handleChange}
          />

          <Button
            fullWidth
            variant="contained"
            sx={{ mt: 3 }}
            onClick={register}
          >
            Register
          </Button>

          <Typography align="center" sx={{ mt: 2 }}>
            Already have an account?{" "}
            <Link to="/login">Login</Link>
          </Typography>

        </CardContent>
      </Card>
    </Box>
  );
}

export default Register;