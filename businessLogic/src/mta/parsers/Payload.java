package mta.parsers;

import mta.ea.Graph;
import mta.ea.Selection;

public class Payload {

    private final Graph graph;
    private final int minNumOfCameras;
    private final int maxNumOfCameras;
    private final double numOfCamerasWeight;
    private final double coverWeight;
    private final Selection selection;
    private final int populationSize;
    private final int maxNumOfGeneration;
    private final double targetFitness;



    public Payload(Graph graph, int minNumOfCameras, int maxNumOfCameras, double numOfCamerasWeight,
                   int selection, int populationSize, int maxNumOfGeneration, double targetFitness) {
        this.graph = graph;
        this.minNumOfCameras = minNumOfCameras;
        this.maxNumOfCameras = maxNumOfCameras;
        this.numOfCamerasWeight = numOfCamerasWeight;
        this.coverWeight = 1-numOfCamerasWeight;
        this.selection = Selection.values()[(selection -1) % Selection.values().length];
        this.populationSize = populationSize;
        this.maxNumOfGeneration =maxNumOfGeneration;
        this.targetFitness = targetFitness;
    }
    public Graph getGraph() {
        return graph;
    }

    public int getMinNumOfCameras() {
        return minNumOfCameras;
    }

    public int getMaxNumOfCameras() {
        return maxNumOfCameras;
    }

    public double getNumOfCamerasWeight() {
        return numOfCamerasWeight;
    }

    public double getCoverWeight() {
        return coverWeight;
    }

    public Selection getSelection() {
        return selection;
    }

    public int getPopulationSize() {
        return populationSize;
    }

    public int getMaxNumOfGeneration() {
        return maxNumOfGeneration;
    }

    public double getTargetFitness() {
        return targetFitness;
    }
}
