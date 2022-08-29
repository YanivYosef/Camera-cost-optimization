import React, {useEffect, useState} from 'react';
import './App.css';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Headline from './components/Headline'
import InsertDetails from './components/InsertDetails'
import Features from './components/Features'
import Submit from './components/Submit'
import axios from "axios";
import {Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, TextField} from "@mui/material";
import Button from "@mui/material/Button";

function App() {

  const [payload, setPayload] = useState({
    "graph": {

    },
    "CamerasConfig": {
      "minNumOfCameras": 30,
      "maxNumOfCameras": 50
    },
    "EaConfig": {
      "numOfCamerasWeight": 0.3,
      "coverWeight": 0.7,
      "selection": 1,
      "populationSize": 40,
      "maxNumOfGeneration": 100,
      "targetFitness": 0.7
    }

  });

  const [open, setOpen] = React.useState(false);
  const [solution, setSolution] = React.useState("");

  const client = axios.create({
    baseURL: "http://localhost:8080"
  });

//CamerasConfig": {
//       "minNumOfCameras": 30,
//       "maxNumOfCameras": 50
//     }

//const new_obj = { ...obj, name: { first: 'blah', last: 'ha'} } // to create an object

const updateCamerasConfig = (camerasConfig) => {
  setPayload({...payload, CamerasConfig:
        {minNumOfCameras: camerasConfig.minNumOfCameras, maxNumOfCameras: camerasConfig.maxNumOfCameras}})
}

const updateEaConfig = (EaConfig) => {
  setPayload({...payload, EaConfig: {numOfCamerasWeight: EaConfig.numOfCamerasWeight,
      coverWeight: EaConfig.coverWeight,
      selection: EaConfig.selection,
      populationSize: EaConfig.populationSize,
      maxNumOfGeneration: EaConfig.maxNumOfGeneration,
      targetFitness: EaConfig.targetFitness}})
}
const onUpload = (graph) => {
  setPayload({...payload, graph:
        {vertexAmount: graph.vertexAmount, edges: graph.edges}})
}

const onSubmit = async () => {
  const response = await client.post('/camerasMap', {
    "payload": payload
  });
  const data = response.data.replaceAll("=", ": ");
  setSolution(data);
  //setSolution(JSON.parse(data));
  setOpen(true);
}

  const handleClose = () => {
    setOpen(false);
  };

  return (
    <main>
    <Grid container direction = "column" spacing={1}>
      <Headline/>
      <InsertDetails updateCamerasConfig = {updateCamerasConfig} payloadCamerasConfig={payload.CamerasConfig} onUpload={onUpload}/>
      <Features updateEaConfig = {updateEaConfig} payloadEaConfig={payload.EaConfig}/>
      <Submit onSubmit = {onSubmit} />
    </Grid>
      <Dialog open={open} onClose={handleClose}>
        <DialogTitle>Solution:</DialogTitle>
        <DialogContent>
          <DialogContentText>
            {solution}
          </DialogContentText>

        </DialogContent>
      </Dialog>
    </main>
  );
}

export default App;