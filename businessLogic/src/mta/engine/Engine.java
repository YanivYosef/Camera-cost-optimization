package mta.engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import mta.ea.CamerasEa;
import mta.parsers.JsonParser;
import mta.parsers.Payload;

import java.io.IOException;

public class Engine {
public String produceBestCover(String payloadString){
    Payload payload;
    try{
        payload = (new JsonParser()).parseGraph(payloadString);
    } catch(Exception e){
        return e.getMessage();
    }
    String returnValue = "";
    try {
        CamerasEa camEa = new CamerasEa(payload.getGraph(), payload.getMinNumOfCameras(),payload.getMaxNumOfCameras(),
                payload.getNumOfCamerasWeight(), payload.getCoverWeight(), payload.getSelection(), payload.getPopulationSize(),
                payload.getMaxNumOfGeneration(), payload.getTargetFitness());
        returnValue = camEa.startEA();
    } catch(JsonProcessingException e) {
        System.out.println("Exception:"+e);
    } catch (IOException e) {
        e.printStackTrace();
    } catch (NoSuchMethodError e) {
        System.out.println("Exception:"+e);
    }
    catch (Exception e){
        System.out.println("Exception:"+e);
    }
    return returnValue;

}
}
