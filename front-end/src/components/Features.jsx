import React from 'react';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Button from '@mui/material/Button';
import TextField from '@mui/material/TextField';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import {Select} from "@mui/material";
//import { SelectChangeEvent } from '@mui/material/Select';

function Features() {
    const [selection, setSelection] = React.useState('');

    const handleChange = (event) => {
        setSelection(event.target.value)};

return(<Grid item style={{ height: "220px", color: 'black', backgroundColor: '#c0d8f0' }} >
  <Typography variant="h5" gutterBottom component="div">
        features for the EA
      </Typography>
    <Grid container>
        <Grid item xs={12}>
            <TextField id="filled-basic" label="numOfCamerasWeight: " variant="filled" defaultValue={0.3}/>
            <TextField id="filled-basic" label="coverWeight: " variant="filled" defaultValue={0.7}/>
            <FormControl variant="filled" sx={{ minWidth: 220 }}>
                <InputLabel id="demo-simple-select-filled-label">Selection</InputLabel>
                <Select
                    labelId="demo-simple-select-filled-label"
                    id="demo-simple-select-filled"
                    value={selection}
                    onChange={handleChange}
                >
                    <MenuItem value="">
                        <em>None</em>
                    </MenuItem>
                    <MenuItem value={10}>RouletteWheelSelection</MenuItem>
                    <MenuItem value={20}>SigmaScaling</MenuItem>
                    <MenuItem value={40}>StochasticUniversalSampling</MenuItem>
                    <MenuItem value={50}>TournamentSelection</MenuItem>
                    <MenuItem value={60}>TruncationSelection</MenuItem>
                    <MenuItem value={70}>RankSelection</MenuItem>
                </Select>
            </FormControl>
        </Grid>
    </Grid>
    <Grid container>
        <Grid item xs={12}>
            <TextField id="filled-basic" label="populationSize: " variant="filled" defaultValue={40}/>
            <TextField id="filled-basic" label="maxNumOfGeneration: " variant="filled" defaultValue={100}/>
            <TextField id="filled-basic" label="targetFitness: " variant="filled" defaultValue={0.7}/>
        </Grid>
    </Grid>
    </Grid>)
}

export default Features;