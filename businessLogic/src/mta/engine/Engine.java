package mta.engine;


import mta.ea.CamerasEa;
import mta.parsers.JsonParser;
import mta.parsers.Payload;



import java.util.HashMap;
import java.util.Map;

public class Engine {
public String produceBestCover(String payloadString){
    Payload payload = null;
    Map<String, Object> returnValue = new HashMap<>();
    try{
        payload = (new JsonParser()).parseGraph(payloadString);
    } catch(Exception e){
        returnValue.put("error", e.getMessage());
    }

    try {
        CamerasEa camEa = new CamerasEa(payload.getGraph(), payload.getMinNumOfCameras(),payload.getMaxNumOfCameras(),
                payload.getNumOfCamerasWeight(), payload.getCoverWeight(), payload.getSelection(), payload.getPopulationSize(),
                payload.getMaxNumOfGeneration(), payload.getTargetFitness());
        returnValue = camEa.startEA();
    } catch(Exception | NoSuchMethodError e) {
        returnValue.put("error", e.getMessage());
    }

    return returnValue.toString();



}
}
