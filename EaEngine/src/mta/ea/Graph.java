package mta.ea;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Graph {
    public int vertexAmount;
    public List<List<Integer>> edges;

    public Graph(int vertexAmount, List<List<Integer>> edges){
        this.vertexAmount = vertexAmount;
        this.edges = edges;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Graph graph = (Graph) o;
        return vertexAmount == graph.vertexAmount && Objects.equals(edges, graph.edges);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertexAmount, edges);
    }
}
