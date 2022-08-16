import React, {useEffect, useState} from 'react';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';

function InsertDetails({updateCamerasConfig, payloadCamerasConfig, onUpload}) {

    const [camerasConfig, setCamerasConfig] = useState(payloadCamerasConfig);

    const[minNumOfCameras, setMinNumOfCameras] = useState(payloadCamerasConfig.minNumOfCameras) ;
    const[maxNumOfCameras, setMaxNumOfCameras] = useState(payloadCamerasConfig.maxNumOfCameras) ;

    useEffect(() => {
        setCamerasConfig({ ...camerasConfig, minNumOfCameras });
    }, [minNumOfCameras]);
    useEffect(() => {
        setCamerasConfig({ ...camerasConfig, maxNumOfCameras });
    }, [maxNumOfCameras]);

    useEffect(() => {
        updateCamerasConfig(camerasConfig);
    }, [camerasConfig]);

    const onMinFieldChange =  (e) => {

        setMinNumOfCameras(+e.target.value);

    };

    const onMaxFieldChange =  (e) => {

        setMaxNumOfCameras(+e.target.value);

    };



return (<Grid item style={{ height: "230px", color: 'black', backgroundColor: '#c0d8f0' }} >
                <Typography variant="h5" gutterBottom component="div">
      Insert details
      </Typography>
         <Typography variant="subtitle1" gutterBottom component="div">
   send us a message and we'll respond within 24 hours.
      </Typography>
  <TextField id="filled-basic" label="min numbers of cameras: " variant="filled" defaultValue={minNumOfCameras} onChange={onMinFieldChange}/>
    <TextField id="filled-basic" label="max numbers of cameras: " variant="filled" defaultValue={maxNumOfCameras} onChange={onMaxFieldChange}/>
  <Typography variant="subtitle1" gutterBottom component="div">
   Insert a graph
      </Typography>
<Button variant="contained" color="success" onClick={onUpload}>
  Upload
</Button>
    </Grid>)
}

export default InsertDetails;