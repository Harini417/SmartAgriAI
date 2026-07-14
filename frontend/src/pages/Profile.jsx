import { Box, Card, CardContent, Typography, Avatar } from "@mui/material";

function Profile() {
  const user = JSON.parse(localStorage.getItem("user"));

  return (
    <Box sx={{ p: 3 }}>
      <Card>
        <CardContent>
          <Typography variant="h4" gutterBottom>
            My Profile
          </Typography>

          <Avatar
            sx={{
              width: 80,
              height: 80,
              mb: 2,
              bgcolor: "green",
              fontSize: 32,
            }}
          >
            {user?.name?.charAt(0).toUpperCase()}
          </Avatar>

          <Typography><b>Name:</b> {user?.name}</Typography>
          <Typography><b>Email:</b> {user?.email}</Typography>
        </CardContent>
      </Card>
    </Box>
  );
}

export default Profile;