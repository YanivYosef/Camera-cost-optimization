import React from 'react';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import {Select} from "@mui/material";
import {useEffect, useState} from 'react';


function Features({updateEaConfig, payloadEaConfig}) {
    const [selection, setSelection] = React.useState(payloadEaConfig.selection);

    const handleChange = (event) => {
        setSelection(event.target.value)};

    const [EaConfig, setEaConfig] = useState(payloadEaConfig);

    const[numOfCamerasWeight, setNumOfCamerasWeight] = useState(payloadEaConfig.numOfCamerasWeight) ;
    const[coverWeight, setCoverWeight] = useState(payloadEaConfig.coverWeight) ;
    const[maxNumOfGeneration, setMaxNumOfGeneration] = useState(payloadEaConfig.maxNumOfGeneration) ;
    const[populationSize, setPopulationSize] = useState(payloadEaConfig.populationSize) ;
    const[targetFitness, setTargetFitness] = useState(payloadEaConfig.targetFitness) ;


    useEffect(() => {
        setEaConfig({ ...EaConfig, numOfCamerasWeight });
    }, [numOfCamerasWeight]);
    useEffect(() => {
        setEaConfig({ ...EaConfig, coverWeight });
    }, [coverWeight]);
    useEffect(() => {
        setEaConfig({ ...EaConfig, maxNumOfGeneration });
    }, [maxNumOfGeneration]);
    useEffect(() => {
        setEaConfig({ ...EaConfig, populationSize });
    }, [populationSize]);
    useEffect(() => {
        setEaConfig({ ...EaConfig, targetFitness });
    }, [targetFitness]);
    useEffect(() => {
        setEaConfig({ ...EaConfig, selection });
    }, [selection]);


    useEffect(() => {
        updateEaConfig(EaConfig);
    }, [EaConfig]);


    const onCamerasWeightChange =  (e) => {
        setNumOfCamerasWeight(+e.target.value);
    };
    const onCoverWeightChange =  (e) => {
        setCoverWeight(+e.target.value);
    };
    const onMaxNumOfGenerationChange =  (e) => {
        setMaxNumOfGeneration(+e.target.value);
    };
    const onPopulationSizeChange =  (e) => {
        setPopulationSize(+e.target.value);
    };
    const onTargetFitnessChange =  (e) => {
        setTargetFitness(+e.target.value);
    };


return(<Grid item style={{ height: "220px", color: 'black', backgroundColor: '#c0d8f0' }} >
  <Typography variant="h5" gutterBottom component="div">
        features for the EA
      </Typography>
    <Grid container>
        <Grid item xs={12}>
            <TextField id="filled-basic" label="numOfCamerasWeight: " variant="filled" defaultValue={0.3} onChange={onCamerasWeightChange}/>
            <TextField id="filled-basic" label="coverWeight: " variant="filled" defaultValue={0.7} onChange={onCoverWeightChange}/>
            <FormControl variant="filled" sx={{ minWidth: 220 }}>
                <InputLabel id="demo-simple-select-filled-label">Selection</InputLabel>
                <Select
                    labelId="demo-simple-select-filled-label"
                    id="demo-simple-select-filled"
                    value={selection}
                    onChange={handleChange}

                >

                    <MenuItem value={1}>RouletteWheelSelection</MenuItem>
                    <MenuItem value={2}>SigmaScaling</MenuItem>
                    <MenuItem value={4}>StochasticUniversalSampling</MenuItem>
                    <MenuItem value={5}>TournamentSelection</MenuItem>
                    <MenuItem value={6}>TruncationSelection</MenuItem>
                    <MenuItem value={7}>RankSelection</MenuItem>
                </Select>
            </FormControl>
        </Grid>
    </Grid>
    <Grid container>
        <Grid item xs={12}>
            <TextField id="filled-basic" label="populationSize: " variant="filled" defaultValue={40} onChange={onPopulationSizeChange}/>
            <TextField id="filled-basic" label="maxNumOfGeneration: " variant="filled" defaultValue={100} onChange={onMaxNumOfGenerationChange}/>
            <TextField id="filled-basic" label="targetFitness: " variant="filled" defaultValue={0.7} onChange={onTargetFitnessChange}/>
        </Grid>
    </Grid>
    </Grid>)
}

export default Features;