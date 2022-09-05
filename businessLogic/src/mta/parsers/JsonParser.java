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
           "vertexAmount" : 100,
           "edges" : [[19,42],[4,31], [31,83], [77,18], [14,33], [71,99], [45,11], [82,57], [25,7], [55,9], [76,14], [93,15], [20,3], [35,25], [32,61], [6,45]]
        },
        "CamerasConfig" : {
        "minNumOfCameras" : 2 ,
        "maxNumOfCameras" : 8
        } ,
        "EaConfig" : {
            "numOfCamerasWeight" : 0.2 ,
            "coverWeight" : 0.8 ,
            "selection" : 3 ,
            "populationSize" : 40 ,
            "maxNumOfGeneration" : 100 ,
            "targetFitness" : 0.6
        }

    }
}
 */


public class JsonParser {

    /*
 create edges for checking different graphs
    private List<List<Integer>> createEdges(int vertexAmount){
        List<List<Integer>> lst = new ArrayList<>();
        Random rng = new MersenneTwisterRNG();
        for(int i=0; i <= 50; i++){
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
*/

    public Payload parseGraph(String payload) throws Exception {
        JSONObject obj = new JSONObject(payload);
        int vertexAmount = 0;
        List<List<Integer>> edges = null;
        try{
            vertexAmount = obj.getJSONObject("payload").getJSONObject("graph").getInt("vertexAmount");

        } catch(Exception e){

            throw new Exception("Vertex amount are not valid.");
        }
        try{
            JSONArray edgesArr = obj.getJSONObject("payload").getJSONObject("graph").getJSONArray("edges");
            edges = new ArrayList<>();
            for (int i = 0; i < edgesArr.length(); i++) {
                JSONArray childJsonArray = edgesArr.optJSONArray(i);
                if (childJsonArray != null && childJsonArray.length() > 0) {
                    edges.add(new ArrayList<>(2));
                    for (int j = 0; j < childJsonArray.length(); j++) {
                        edges.get(i).add(childJsonArray.optInt(j));

                    }
                }
            }
        } catch(Exception e){

            throw new Exception("Edges are not valid.");
        }

        //edges = createEdges(vertexAmount);

        Graph graph = new Graph(vertexAmount, edges);
        double numOfCamerasWeight = 0.6;
        int minCameras = 0;
        int maxCameras = 30;
        int selection = 3;
        int populationSize = 40;
        int maxNumOfGeneration = 100;
        double targetFitness = 0.7;

        try{
            minCameras = obj.getJSONObject("payload").getJSONObject("CamerasConfig").getInt("minNumOfCameras");
            maxCameras = obj.getJSONObject("payload").getJSONObject("CamerasConfig").getInt("maxNumOfCameras");

        } catch(Exception e){
            throw new Exception("Min cameras or Max cameras are not valid.");
        } try{
            numOfCamerasWeight = obj.getJSONObject("payload").getJSONObject("EaConfig").getDouble("numOfCamerasWeight");
            selection = obj.getJSONObject("payload").getJSONObject("EaConfig").getInt("selection");
            populationSize = obj.getJSONObject("payload").getJSONObject("EaConfig").getInt("populationSize");
            maxNumOfGeneration = obj.getJSONObject("payload").getJSONObject("EaConfig").getInt("maxNumOfGeneration");
            targetFitness = obj.getJSONObject("payload").getJSONObject("EaConfig").getDouble("targetFitness");
        } catch(Exception e){
            System.out.println(e.getMessage());
            throw new Exception("something went wrong while parsing the json. " + e.getMessage());
        }

        return new Payload(graph,minCameras,maxCameras, numOfCamerasWeight, selection, populationSize,
                maxNumOfGeneration, targetFitness);
    }
}

