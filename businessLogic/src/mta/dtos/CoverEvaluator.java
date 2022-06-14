package mta.dtos;

import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.List;

public class CoverEvaluator implements FitnessEvaluator<List<Integer>> {

    private Graph graph;
    private double numOfCamerasWeight;

    public CoverEvaluator(Graph graph, double numOfCamerasWeight) {
        this.graph = graph;
        this.numOfCamerasWeight = numOfCamerasWeight;

    }

    public double getFitness(List<Integer> candidate,
                             List<? extends List<Integer>> population)
    {

        //here we want to return a number. as bigger the number, it better fits
        //we take into account two parameters:
        //1. number of edges in candidate
        //2. what is the percentage of coverage of graph

        //check how many edges are covered in candidate
        int edgesInCover = 0;
        for (int i = 0; i < graph.edges.size(); i++)
        {
            List<Integer> edge = graph.edges.get(i);
            int v1 = edge.get(0);
            int v2 = edge.get(1);
            if(candidate.contains(v1) || candidate.contains(v2)) {edgesInCover++;}

        }
        double coverScore = (double)edgesInCover/graph.edges.size() * (1-numOfCamerasWeight);
        double camerasScore = 1/(double)candidate.size() * numOfCamerasWeight;

        return coverScore + camerasScore;
    }

    public boolean isNatural()
    {
        return true;
    }
}

