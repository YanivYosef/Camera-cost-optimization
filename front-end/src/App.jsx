import React from 'react';
import './App.css';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Headline from './components/Headline'
import InsertDetails from './components/InsertDetails'
import Features from './components/Features'
import Submit from './components/Submit'

function App() {
  let data = {details: {minCameras: 0, maxCameras: 10}}
  let EAdata = {details: {numOfCamerasWeight: 0.4, coverWeight: 0.6, selection: 3, populationSize: 40, maxNumOfGeneration: 100, targetFitness: 0.7}}
  return (
    <main>
    <Grid container direction = "column" spacing={1}>
      <Headline/>
      <InsertDetails data = {data.details}/> 
      <Features EAdata = {EAdata.details}/>
      <Submit/>
    </Grid>
    </main>
  );
}

export default App;