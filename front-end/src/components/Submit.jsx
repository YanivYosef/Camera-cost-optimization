import React from 'react';
import Grid from '@mui/material/Grid';
import Button from "@mui/material/Button";

function Submit({onSubmit}) {
  return(<Grid item style={{ height: "125px", color: 'black', backgroundColor: '#c0d8f0' }} >
      <Button variant="contained" color="success" onClick={onSubmit}>
          Submit
      </Button>
    </Grid>)
}

export default Submit;