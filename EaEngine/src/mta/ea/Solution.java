package mta.ea;

import java.util.List;

public class Solution {
    private List<Integer> vertices;

    public Solution(List<Integer> vertices) {
        this.vertices = vertices;
    }

    public void setVertices(List<Integer> vertices) {
        this.vertices = vertices;
    }

    public List<Integer> getVertices() {
        return vertices;
    }
    public void add(int vertex){
        this.vertices.add(vertex);
    }
}
