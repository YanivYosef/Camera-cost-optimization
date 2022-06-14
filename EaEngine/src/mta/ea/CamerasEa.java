package mta.ea;

import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.*;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.selection.*;
import org.uncommons.watchmaker.framework.termination.TargetFitness;

import java.io.IOException;
import java.util.*;

/*
http://localhost:8080/camerasMap
{
    "payload" : {
        "graph" : {
           "vertexAmount" : 10,
           "edges" : [[1,2], [1,3], [3,7], [3,10], [2,5], [2,8], [8,9], [8,10], [1,4], [2,4], [6,7]]
        },
        "CamerasConfig" : {
        "minNumOfCameras" : 2 ,
        "maxNumOfCameras" : 6
        } ,
        "EaConfig" : {
            "numOfCamerasWeight" : 0.6 ,
            "coverWeight" : 0.4
        }
    }
}
 */


public class CamerasEa {
    private Graph graph;
    private int minCameras;
    private int maxCameras;
    private double numOfCamerasWeight;
    private double coverWeight;
    private int selectionStrategy = 4;
    private final double prob = 0.6;
    private double score = 0.0;

    public CamerasEa(Graph graph, int minCameras, int maxCameras, double numOfCamerasWeight, double coverWeight) throws IOException {

        this.graph = graph;
        this.maxCameras=maxCameras;
        this.minCameras=minCameras;
        this.coverWeight=coverWeight;
        this.numOfCamerasWeight=numOfCamerasWeight;

    }

    /* One item in population will be a possible cover of the Graph that we got in C'tor (Graph was part of json)
     a possible cover of the graph will be implemented as an ArrayList<Integer>
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
                if(populationData.getGenerationNumber() > 199) {
                    System.out.println("That's enough for now");
                    return true;
                } else {
                    return false;
                }
            }
        };
        List<Integer> result = engine.evolve(10, 3, tc, new TargetFitness(0.5, true));

        System.out.println(result);
        return result.toString() + "\nscore = " + score;

    }
}
