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
           "vertexAmount" : 100 ,
           "edges" : [[75,40], [52,32], [27,7], [4,46], [10,99], [51,58], [39,49], [44,52], [88,79], [32,78], [15,3], [27,9], [84,22], [96,19], [74,81], [83,6], [23,24], [16,1], [70,88], [67,79], [87,33], [13,23], [73,28], [4,66], [38,29], [6,48], [14,73], [58,87], [96,98], [59,11], [47,68], [12,96], [47,73], [93,28], [82,1], [18,26], [15,85], [25,66], [100,79], [15,74], [60,76], [24,86], [36,65], [73,39], [88,10], [82,72], [19,39], [43,50], [92,23], [45,74], [80,28]]

        },
        "CamerasConfig" : {
        "minNumOfCameras" : 10 ,
        "maxNumOfCameras" : 30
        } ,
        "EaConfig" : {
            "numOfCamerasWeight" : 0.2 ,
            "coverWeight" : 0.8 ,
            "selection" : 3 ,
            "populationSize" : 40 ,
            "maxNumOfGeneration" : 100 ,
            "targetFitness" : 0.8
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
    private Selection selectionStrategy;
    private final double prob = 0.6;
    private double score = 0.0;
    private int generation = 0;
    private int populationSize;
    private int maxNumOfGeneration;
    private double targetFitness;

    public CamerasEa(Graph graph, int minCameras, int maxCameras, double numOfCamerasWeight, double coverWeight,
                     Selection selection, int populationSize, int maxNumOfGeneration, double targetFitness) throws IOException {

        this.graph = graph;
        this.maxCameras=maxCameras;
        this.minCameras=minCameras;
        this.coverWeight=coverWeight;
        this.numOfCamerasWeight=numOfCamerasWeight;
        this.selectionStrategy = selection;
        this.populationSize = populationSize;
        this.maxNumOfGeneration = maxNumOfGeneration;
        this.targetFitness = targetFitness;


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
            case RouletteWheelSelection:
                selection = new RouletteWheelSelection();
                break;
            case SigmaScaling:
                selection = new SigmaScaling();
                break;
            case StochasticUniversalSampling:
                selection = new StochasticUniversalSampling();
                break;
            case TournamentSelection:
                Probability probability = new Probability(prob);
                selection = new TournamentSelection(probability);
                break;
            case TruncationSelection:
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
                        generation = data.getGenerationNumber(),
                        data.getBestCandidate(),
                        data.getBestCandidateFitness());
                        score = data.getBestCandidateFitness();
            }
        });
        TerminationCondition tc = new TerminationCondition() {
            @Override
            public boolean shouldTerminate(PopulationData<?> populationData) {
                if(populationData.getGenerationNumber() >= maxNumOfGeneration) {
                    System.out.println("That's enough for now");
                    return true;
                } else {
                    return false;
                }
            }
        };
        List<Integer> result = engine.evolve(populationSize, 3, tc, new TargetFitness(targetFitness, true));

        System.out.println(result);
        return  "Cameras location: " + result.toString() + "\nScore: " + score + "\nNum of generation: " + generation + "\nNum of cameras: " + result.size();

    }
}
