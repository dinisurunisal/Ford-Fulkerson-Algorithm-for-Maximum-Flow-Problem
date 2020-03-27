package newtry;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    String name;
    List<Edge> adjacentEdges;
    int currentVisit;

    public Vertex(String name) {
        this.name = name;
        this.adjacentEdges = new ArrayList<>();
        this.currentVisit = 0;
    }

    public void addNeighbour(Edge edge){
        adjacentEdges.add(edge);
    }
}
