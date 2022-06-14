package mta.dtos;

import org.uncommons.watchmaker.framework.factories.AbstractCandidateFactory;

import java.util.*;

public class CoverFactory<T> extends AbstractCandidateFactory<List<T>> {
    private final List<T> vertices;
    private final int minCameras;
    private final int maxCameras;
    public CoverFactory(List<T> vertices, int minCameras, int maxCameras) {
        this.vertices = vertices; // should be set??
        this.minCameras = minCameras;
        this.maxCameras = maxCameras;
    }

    public List<T> generateRandomCandidate(Random rng) {
        //List<T> candidate = new ArrayList<>(this.candidateSize);
        //Collections.shuffle(candidate, rng);
        Random rnd = new Random();
        int candidateSize = rnd.nextInt(maxCameras - minCameras + 1) + minCameras;
        Set<T> set = new TreeSet<T>();  // create set that make sure there are no duplicates vertices in the cover
        // select randomly vertices
        while(set.size() < candidateSize) {
            set.add(this.vertices.get(rng.nextInt(this.vertices.size())));
        }
        List<T> candidate = new ArrayList<>(candidateSize);
        for(T num : set) {
            candidate.add(num);
        }
        return candidate;
    }
}
