import React, {useEffect, useState} from 'react';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';

function InsertDetails({updateCamerasConfig}) {

    let minNumOfCameras = 20;
    const [camerasConfig, setCamerasConfig] = useState({});
    const onMinFieldChange = (e) => {
        setCamerasConfig({...camerasConfig, minNumOfCameras: e.target.value});
        updateCamerasConfig(camerasConfig);
    };
    useEffect(()=> {
        console.log(minNumOfCameras);
        setCamerasConfig({...camerasConfig, minNumOfCameras: minNumOfCameras});
        updateCamerasConfig(camerasConfig);
    }, [minNumOfCameras]) ;

return (<Grid item style={{ height: "230px", color: 'black', backgroundColor: '#c0d8f0' }} >
                <Typography variant="h5" gutterBottom component="div">
      Insert details
      </Typography>
         <Typography variant="subtitle1" gutterBottom component="div">
   send us a message and we'll respond within 24 hours.
      </Typography>
  <TextField id="filled-basic" label="min numbers of cameras: " variant="filled" defaultValue={minNumOfCameras} onChange={onMinFieldChange}/>
    <TextField id="filled-basic" label="max numbers of cameras: " variant="filled" defaultValue={40} />
  <Typography variant="subtitle1" gutterBottom component="div">
   Insert a graph
      </Typography>
<Button variant="contained" color="success">
  Upload
</Button>
    </Grid>)
}

export default InsertDetails;