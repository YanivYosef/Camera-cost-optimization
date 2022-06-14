package mta.ea;


import org.uncommons.maths.number.ConstantGenerator;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;

import java.util.*;

public class CoverMutation implements EvolutionaryOperator<List<Integer>> {
    private final NumberGenerator<Integer> mutationCountVariable;
    private final NumberGenerator<Integer> mutationAmountVariable;
    private final int maxCameras;
    private final int vertexAmount;

    public CoverMutation(int maxCameras, int vertexAmount) {
        this(1, 1,maxCameras, vertexAmount);
    }

    public CoverMutation(int mutationCount, int mutationAmount, int maxCameras, int vertexAmount) {
        this(new ConstantGenerator(mutationCount), new ConstantGenerator(mutationAmount), maxCameras, vertexAmount);
    }

    public CoverMutation(NumberGenerator<Integer> mutationCountVariable, NumberGenerator<Integer> mutationAmountVariable, int maxCameras, int vertexAmount) {
        this.mutationCountVariable = mutationCountVariable;
        this.mutationAmountVariable = mutationAmountVariable;
        this.maxCameras = maxCameras;
        this.vertexAmount = vertexAmount;
    }

    public List<List<Integer>> apply(List<List<Integer>> selectedCandidates, Random rng){
        List<List<Integer>> result = new ArrayList(selectedCandidates.size());
        Iterator i$ = selectedCandidates.iterator();

        while(i$.hasNext()) {
            List<Integer> candidate = (List)i$.next();
            List<Integer> newCandidate = new ArrayList(candidate.size());
            int mutationCount = Math.abs((Integer)this.mutationCountVariable.nextValue());

            for(int i = 0; i < mutationCount; ++i) {
                Set<Integer> set = new TreeSet<>(candidate);
                final int setSize = set.size();
                while(set.size() == setSize && set.size() < maxCameras) {
                    set.add(rng.nextInt(this.vertexAmount)+1);
                }
                set.remove(rng.nextInt(this.vertexAmount)+1);
                newCandidate = new ArrayList(set);
            }

            result.add(newCandidate);
        }

        return result;
    }
}
