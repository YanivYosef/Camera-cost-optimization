import React from 'react';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';

function Features() {
return(<Grid item style={{ height: "220px", color: 'black', backgroundColor: '#c0d8f0' }} >
  <Typography variant="h5" gutterBottom component="div">
        features for the EA
      </Typography>
    <Grid container>
        <Grid item xs={12}>
            <TextField id="filled-basic" label="numOfCamerasWeight: " variant="filled" />
            <TextField id="filled-basic" label="coverWeight: " variant="filled" />
            <TextField id="filled-basic" label="selection: " variant="filled" />
        </Grid>
    </Grid>
    <Grid container>
        <Grid item xs={12}>
            <TextField id="filled-basic" label="populationSize: " variant="filled" />
            <TextField id="filled-basic" label="maxNumOfGeneration: " variant="filled" />
            <TextField id="filled-basic" label="targetFitness: " variant="filled" />
        </Grid>
    </Grid>
    </Grid>)
}

export default Features;