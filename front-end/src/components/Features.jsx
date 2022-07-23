import React from 'react';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';

function Features(EAdata) {
return(<Grid item style={{ height: "160px", color: 'black', backgroundColor: '#c0d8f0' }} >
  <Typography variant="h5" gutterBottom component="div">
        features for the EA
      </Typography>
 <TextField id="filled-basic" label="numOfCamerasWeight: " variant="filled" defaultValue  = {EAdata.numOfCamerasWeight} />   
  <TextField id="filled-basic" label="coverWeight: " variant="filled" defaultValue  = {EAdata.coverWeight} />  <TextField id="filled-basic" label="selection: " variant="filled" defaultValue  = {EAdata.selection} />
  <TextField id="filled-basic" label="populationSize: " variant="filled" defaultValue  = {EAdata.populationSize} />
  <TextField id="filled-basic" label="maxNumOfGeneration: " variant="filled" defaultValue  = {EAdata.maxNumOfGeneration} />
  <TextField id="filled-basic" label="targetFitness: " variant="filled" defaultValue  = {EAdata.targetFitness} />
    </Grid>)
}

export default Features;