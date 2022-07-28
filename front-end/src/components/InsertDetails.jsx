import React from 'react';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';

function InsertDetails() {
return (<Grid item style={{ height: "230px", color: 'black', backgroundColor: '#c0d8f0' }} >
                <Typography variant="h5" gutterBottom component="div">
      Insert details
      </Typography>
         <Typography variant="subtitle1" gutterBottom component="div">
   send us a message and we'll respond within 24 hours.
      </Typography>
  <TextField id="filled-basic" label="min numbers of cameras: " variant="filled" />
    <TextField id="filled-basic" label="max numbers of cameras: " variant="filled" />
  <Typography variant="subtitle1" gutterBottom component="div">
   Insert a graph
      </Typography>
<Button variant="contained" color="success">
  Upload
</Button>
    </Grid>)
}

export default InsertDetails;