package mta.server;

//import com.fasterxml.jackson.core.JsonParser;
import mta.dtos.*;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.*;
import org.uncommons.watchmaker.framework.termination.TargetFitness;

import java.io.IOException;
import java.util.*;

/*
{
    "payload" : {
        "graph" : {
           "vertexAmount" : 10,
           "edges" : [[1,2], [1,3]]
        },
        "minNumOfCameras" : 4 ,
        "maxNumOfCameras" : 8 ,
        "EaConfig" : {
            "numOfCamerasWeight" : 0.7 ,
            "coverWeight" : 0.3
        }
    }
}
 */


public class CamerasEa {
    private Graph graph;
    private int minCameras = 2;
    private int maxCameras = 8;
    private double numOfCamerasWeight = 0.4;
    private double coverWeight = 1-numOfCamerasWeight;
    private int selectionStrategy = 4;
    private final double prob = 0.6;
    private double score = 0.0;

    //the c'tor gets a json and it's got is to parse the json into the different members of class
    public CamerasEa(String json) throws IOException {
        /*ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> map = objectMapper.readValue(json, new TypeReference<Map<String,Object>>(){});
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        JsonParser jsonParser = new JsonParser();
        json = "{\"vertexAmount\" : 10, \"edges\" : [[1,2], [1,3]] } ";
        graph = jsonParser.parseGraph(json);*/
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();
        ArrayList<Integer> arr3 = new ArrayList<>();
        ArrayList<Integer> arr4 = new ArrayList<>();
        ArrayList<Integer> arr5 = new ArrayList<>();
        ArrayList<Integer> arr6 = new ArrayList<>();
        ArrayList<Integer> arr7 = new ArrayList<>();
        ArrayList<Integer> arr8 = new ArrayList<>();
        arr1.add(1);arr1.add(2);list.add(arr1);
        arr2.add(1);arr2.add(3);list.add(arr2);
        arr3.add(3);arr3.add(7);list.add(arr3);
        arr4.add(3);arr4.add(10);list.add(arr4);
        arr5.add(2);arr5.add(5);list.add(arr5);
        arr6.add(2);arr6.add(8);list.add(arr6);
        arr7.add(8);arr7.add(9);list.add(arr7);
        arr8.add(8);arr8.add(10);list.add(arr8);
        graph = new Graph(10, list);

    }

    /* One item in population will be a possible cover of the Graph that we got in C'tor (Graph was part of json)
     a possible cover of the graph will be implemented as an ArrayList<int>
     number of elements in the PossibleCover array will be between MinCameras and MaxCameras
     values of integers in the PossibleCover array will be between 1 and graph.vertexAmount
     */

    public String startEA() throws Exception {
        List<Integer> vertices = new ArrayList<>();
        for(int i=1;i<=graph.vertexAmount;i++) {vertices.add(i);}

        // Create a factory that is responsible to create a candidate
        CandidateFactory<List<Integer>> factory = new CoverFactory<>(vertices, minCameras, maxCameras);

        // Create a pipeline that applies cross-over then mutation.
        List<EvolutionaryOperator<List<Integer>>> operators
                = new LinkedList<>();
        operators.add(new CoverCrossover(minCameras, graph.vertexAmount));
        operators.add(new CoverMutation(2,1, maxCameras, graph.vertexAmount));
        EvolutionaryOperator<List<Integer>> pipeline
                = new EvolutionPipeline<>(operators);

        FitnessEvaluator<List<Integer>> fitnessEvaluator = new CoverEvaluator(graph, numOfCamerasWeight);
        SelectionStrategy<Object> selection = null;
        switch(selectionStrategy) {
            case 1:
                selection = new RouletteWheelSelection();
                break;
            case 2:
                selection = new SigmaScaling();
                break;
            case 3:
                selection = new StochasticUniversalSampling();
                break;
            case 4:
                Probability probability = new Probability(prob);
                selection = new TournamentSelection(probability);
                break;
            case 5:
                selection = new TruncationSelection(prob);
                break;
            default:
               selection = new RankSelection();
        }

        Random rng = new MersenneTwisterRNG();

        EvolutionEngine<List<Integer>> engine
                = new GenerationalEvolutionEngine<>(factory,
                pipeline,
                fitnessEvaluator,
                selection,
                rng);


        engine.addEvolutionObserver(new EvolutionObserver<List<Integer>>()
        {
            public void populationUpdate(PopulationData<? extends List<Integer>> data)
            {
                System.out.printf("Generation %d: %s with fitness %f\n",
                        data.getGenerationNumber(),
                        data.getBestCandidate(),
                        data.getBestCandidateFitness());
                score = data.getBestCandidateFitness();
            }
        });
        TerminationCondition tc = new TerminationCondition() {
            @Override
            public boolean shouldTerminate(PopulationData<?> populationData) {
                if(populationData.getGenerationNumber() > 99) {
                    System.out.println("That's enough for now");
                    return true;
                } else {
                    return false;
                }
            }
        };
        List<Integer> result = engine.evolve(10, 5, tc, new TargetFitness(0.8, true));

        System.out.println(result);
        return result.toString() + "\nscore = " + score;

    }
}
