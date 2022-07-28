import React from 'react';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Button from "@mui/material/Button";

function Submit() {
  return(<Grid item style={{ height: "125px", color: 'black', backgroundColor: '#c0d8f0' }} >
      <Button variant="contained" color="success">
          Submit
      </Button>
    </Grid>)
}

export default Submit;