package mta.engine;

import com.fasterxml.jackson.core.JsonProcessingException;
import mta.ea.CamerasEa;
import mta.parsers.JsonParser;
import mta.parsers.Payload;

import java.io.IOException;

public class Engine {
public String produceBestCover(String payloadString){
    Payload payload = (new JsonParser()).parseGraph(payloadString);
    String returnValue = "";
    try {
        CamerasEa camEa = new CamerasEa(payload.getGraph(), payload.getMinNumOfCameras(),payload.getMaxNumOfCameras(), payload.getNumOfCamerasWeight(), payload.getCoverWeight());
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
