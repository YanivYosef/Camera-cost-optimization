import React, {useEffect, useState} from 'react';
import './App.css';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import Headline from './components/Headline'
import InsertDetails from './components/InsertDetails'
import Features from './components/Features'
import Submit from './components/Submit'
import axios from "axios";

function App() {

  const [payload, setPayload] = useState({
    "graph": {
      "vertexAmount": 100,
      "edges": [[75, 40], [52, 32], [27, 7], [4, 46], [10, 99], [51, 58], [39, 49], [44, 52], [88, 79], [32, 78], [15, 3], [27, 9], [84, 22], [96, 19], [74, 81], [83, 6], [23, 24], [16, 1], [70, 88], [67, 79], [87, 33], [13, 23], [73, 28], [4, 66], [38, 29], [6, 48], [14, 73], [58, 87], [96, 98], [59, 11], [47, 68], [12, 96], [47, 73], [93, 28], [82, 1], [18, 26], [15, 85], [25, 66], [100, 79], [15, 74], [60, 76], [24, 86], [36, 65], [73, 39], [88, 10], [82, 72], [19, 39], [43, 50], [92, 23], [45, 74], [80, 28]]

    },
    "CamerasConfig": {
      "minNumOfCameras": 30,
      "maxNumOfCameras": 50
    },
    "EaConfig": {
      "numOfCamerasWeight": 0.3,
      "coverWeight": 0.7,
      "selection": 4,
      "populationSize": 40,
      "maxNumOfGeneration": 100,
      "targetFitness": 0.7
    }

  });
  let data = {details: {minCameras: 0, maxCameras: 10}}
  let EAdata = {details: {numOfCamerasWeight: 0.4, coverWeight: 0.6, selection: 3, populationSize: 40, maxNumOfGeneration: 100, targetFitness: 0.7}}

  const client = axios.create({
    baseURL: "http://localhost:8080"
  });

//CamerasConfig": {
//       "minNumOfCameras": 30,
//       "maxNumOfCameras": 50
//     }

//const new_obj = { ...obj, name: { first: 'blah', last: 'ha'} }

const updateCamerasConfig = (camerasConfig) => {
  setPayload({...payload, CamerasConfig:
        {minNumOfCameras: camerasConfig.minNumOfCameras, maxNumOfCameras: camerasConfig.maxNumOfCameras}})
  console.log(payload);
}

const onSubmit = () => {
  client.post('/camerasMap', {
    "payload": {
      "graph": {
        "vertexAmount": 100,
        "edges": [[75, 40], [52, 32], [27, 7], [4, 46], [10, 99], [51, 58], [39, 49], [44, 52], [88, 79], [32, 78], [15, 3], [27, 9], [84, 22], [96, 19], [74, 81], [83, 6], [23, 24], [16, 1], [70, 88], [67, 79], [87, 33], [13, 23], [73, 28], [4, 66], [38, 29], [6, 48], [14, 73], [58, 87], [96, 98], [59, 11], [47, 68], [12, 96], [47, 73], [93, 28], [82, 1], [18, 26], [15, 85], [25, 66], [100, 79], [15, 74], [60, 76], [24, 86], [36, 65], [73, 39], [88, 10], [82, 72], [19, 39], [43, 50], [92, 23], [45, 74], [80, 28]]

      },
      "CamerasConfig": {
        "minNumOfCameras": 30,
        "maxNumOfCameras": 50
      },
      "EaConfig": {
        "numOfCamerasWeight": 0.3,
        "coverWeight": 0.7,
        "selection": 4,
        "populationSize": 40,
        "maxNumOfGeneration": 100,
        "targetFitness": 0.7
      }

    }
  })
      .then((response) => {
        console.log(response);
      });
}

  return (
    <main>
    <Grid container direction = "column" spacing={1}>
      <Headline/>
      <InsertDetails updateCamerasConfig = {updateCamerasConfig}/>
      <Features/>
      <Submit onSubmit = {onSubmit}/>
    </Grid>
    </main>
  );
}

export default App;