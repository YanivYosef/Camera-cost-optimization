package mta.parsers;

import mta.ea.Graph;

public class Payload {


    private final Graph graph;
    private final int minNumOfCameras;
    private final int maxNumOfCameras;
    private final double numOfCamerasWeight;
    private final double coverWeight;



    public Payload(Graph graph, int minNumOfCameras, int maxNumOfCameras, double numOfCamerasWeight) {
        this.graph = graph;
        this.minNumOfCameras = minNumOfCameras;
        this.maxNumOfCameras = maxNumOfCameras;
        this.numOfCamerasWeight = numOfCamerasWeight;
        this.coverWeight = 1-numOfCamerasWeight;
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
}
