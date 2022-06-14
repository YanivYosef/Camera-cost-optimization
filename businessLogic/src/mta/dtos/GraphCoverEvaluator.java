package mta.dtos;

import mta.dtos.Graph;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

import java.util.List;

public class GraphCoverEvaluator implements FitnessEvaluator<List<Integer>> {

    private Graph graph;
    private double numOfCamerasWeight;

    public GraphCoverEvaluator(Graph graph, double numOfCamerasWeight) {
        this.graph = graph;
        this.numOfCamerasWeight = numOfCamerasWeight;

    }

    public double getFitness(List<Integer> candidate,
                             List<? extends List<Integer>> population)
    {

        //here we want to return a number. as smaller the number, it better fits
        //we take into account two parameters: number of edges in candidate: candidate.size()
        //what is the percentage of coverage of graph

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

/*
public class StringEvaluator
{
    private final String targetString = "HELLO WORLD";

    /**
     * Assigns one "fitness point" for every character in the
     * candidate String that matches the corresponding position in
     * the target string.

    public double getFitness(String candidate,
                             List<? extends String> population)
    {
        int matches = 0;
        for (int i = 0; i < candidate.length(); i++)
        {
            if (candidate.charAt(i) == targetString.charAt(i))
            {
                ++matches;
            }
        }
        return matches;
    }

    public boolean isNatural()
    {
        return true;
    }
}

 */