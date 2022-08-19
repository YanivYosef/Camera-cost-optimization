import React, {useEffect, useState} from 'react';
import Grid from '@mui/material/Grid';
import Typography from '@mui/material/Typography';
import TextField from '@mui/material/TextField';
import Button from '@mui/material/Button';

function InsertDetails({updateCamerasConfig, payloadCamerasConfig, onUpload}) {

    const [camerasConfig, setCamerasConfig] = useState(payloadCamerasConfig);
    const [graphConfig, setGraphConfig] = useState({});

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

    useEffect(() => {
        onUpload(graphConfig);
    }, [graphConfig]);

    const onMinFieldChange =  (e) => {

        setMinNumOfCameras(+e.target.value);

    };

    const onMaxFieldChange =  (e) => {

        setMaxNumOfCameras(+e.target.value);

    };



    // Create a reference to the hidden file input element
    const hiddenFileInput = React.useRef(null);

    // Programatically click the hidden file input element
    // when the Button component is clicked
    const handleClick = event => {
        hiddenFileInput.current.click();
    };
    // Call a function (passed as a prop from the parent component)
    // to handle the user-selected file
    const handleChange = event => {
        const fileUploaded = event.target.files[0];
        const fileReader = new FileReader();
        fileReader.readAsText(fileUploaded, "UTF-8");
        fileReader.onload = e => {
            const content = e.target.result;
           const parsedConfig = JSON.parse(content);
            setGraphConfig(parsedConfig);
        };
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
    <Button variant="contained" color="success" onClick={handleClick}>
        Upload
    </Button>
    <input
        type="file"
        ref={hiddenFileInput}
        onChange={handleChange}
        style={{display: 'none'}}
        accept=".json"
    />
    </Grid>)
}

export default InsertDetails;