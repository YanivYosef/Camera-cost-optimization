import React from 'react';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';

function Headline() {
return (<Grid item style={{ height: "100px", color: 'black', backgroundColor: '#c0d8f0' }} >
         <Typography variant="h4" gutterBottom component="div">
        Get your optimal city cover
      </Typography>
         <Typography variant="subtitle1" gutterBottom component="div">
        Let my EA algorithm find the best way to locate cameras on your city
      </Typography>
    </Grid>)
}

export default Headline;