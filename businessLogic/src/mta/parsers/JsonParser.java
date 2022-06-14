package mta.parsers;

import mta.ea.Graph;
import org.json.*;
import org.uncommons.maths.random.MersenneTwisterRNG;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
{
    "payload" : {
        "graph" : {
           "vertexAmount" : 10,
           "edges" : [[1,2], [1,3], [3,7], [3,10], [2,5], [2,8], [8,9], [8,10]]
        },
        "CamerasConfig" : {
        "minNumOfCameras" : 4 ,
        "maxNumOfCameras" : 8
        } ,
        "EaConfig" : {
            "numOfCamerasWeight" : 0.7 ,
            "coverWeight" : 0.3
        }
    }
}
 */


public class JsonParser {

    //public JsonParser(String payload){}

    private List<List<Integer>> createEdges(int vertexAmount){
        List<List<Integer>> lst = new ArrayList<>();
        Random rng = new MersenneTwisterRNG();
        for(int i=0; i <= 15; i++){
            int numOfVertice1 = rng.nextInt(vertexAmount) + 1;
            int numOfVertice2 = rng.nextInt(vertexAmount) + 1;
            while(numOfVertice2 == numOfVertice1){
                numOfVertice2 = rng.nextInt(vertexAmount) + 1;
            }
            lst.add(new ArrayList<Integer>(2));
            lst.get(i).add(numOfVertice1);
            lst.get(i).add(numOfVertice2);
            System.out.print("["+numOfVertice1+","+ numOfVertice2+"], ");
        }
        System.out.println();
        return lst;
    }

    public Payload parseGraph(String payload) {
        String jsonString = payload ; //assign your JSON String here
        JSONObject obj = new JSONObject(jsonString);
        int vertexAmount = obj.getJSONObject("payload").getJSONObject("graph").getInt("vertexAmount");
        JSONArray edgesArr = obj.getJSONObject("payload").getJSONObject("graph").getJSONArray("edges");

        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < edgesArr.length(); i++) {
            JSONArray childJsonArray = edgesArr.optJSONArray(i);
            if (childJsonArray != null && childJsonArray.length() > 0) {
                edges.add(new ArrayList<>(2));
                for (int j = 0; j < childJsonArray.length(); j++) {
                    edges.get(i).add(childJsonArray.optInt(j));

                }
            }
        }

        //List<List<Integer>> edges = createEdges(vertexAmount);

        Graph graph = new Graph(vertexAmount, edges);
        double numOfCamerasWeight = obj.getJSONObject("payload").getJSONObject("EaConfig").getDouble("numOfCamerasWeight");
        int minCameras = obj.getJSONObject("payload").getJSONObject("CamerasConfig").getInt("minNumOfCameras");
        int maxCameras = obj.getJSONObject("payload").getJSONObject("CamerasConfig").getInt("maxNumOfCameras");
        Payload p = new Payload(graph,minCameras,maxCameras, numOfCamerasWeight);
        return p;

    }
}

