import React from "react";
import { Typography, Box } from "@mui/material";

function ComingSoon({ title }) {
  return (
    <Box>
      <Typography variant="h4">
        {title}
      </Typography>

      <Typography sx={{ mt: 2 }}>
        This module is under development.
      </Typography>
    </Box>
  );
}

export default ComingSoon;