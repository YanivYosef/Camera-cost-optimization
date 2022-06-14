package mta.dtos;

import org.uncommons.watchmaker.framework.operators.ListCrossover;

import java.util.*;

public class CoverCrossover extends ListCrossover<Integer> {
    private final int minCameras;
    private final int vertexAmount;
    public CoverCrossover(int minCameras, int vertexAmount) {
        super();
        this.minCameras = minCameras;
        this.vertexAmount = vertexAmount;
    }
    @Override
    protected List<List<Integer>> mate(List<Integer> parent1, List<Integer> parent2, int numberOfCrossoverPoints, Random rng) {
        List<List<Integer>> superResult = super.mate(parent1, parent2, numberOfCrossoverPoints, rng);
        List<List<Integer>> result = new ArrayList(2);
        for (List<Integer> elem: superResult) {
            Set<Integer> set = new TreeSet<>(elem);
            while(set.size()<this.minCameras) {
                set.add(rng.nextInt(this.vertexAmount)+1);
            }
            List<Integer> list = new ArrayList(set);

            result.add(list);

        }
        return result;
    }
}
