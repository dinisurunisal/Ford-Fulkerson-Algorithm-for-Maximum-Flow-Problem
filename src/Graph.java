/**
 *
 * Author : Dinisuru Nisal Gunaratna
 * IIT No : 2018559
 * UOW NO : w1742246
 * Source : https://steemit.com/programming/@drifter1/programming-java-graph-maximum-flow-algorithm-ford-fulkerson
 *
 */


import java.util.List;
import java.util.ArrayList;

public class Graph {
    private int vCount;
    private float[][] adj;

    public int getvCount() {
        return vCount;
    }

    public float[][] getAdj() {
        return adj;
    }

    public Graph(int vCount) {
        this.vCount = vCount;
        adj = new float[vCount][vCount];
        for (int i = 0; i < vCount; i++) {
            for (int j = 0; j < vCount; j++) {
                adj[i][j] = 0;
            }
        }
    }

    public void addEdge(int i, int j, float weight) {
        adj[i][j] = weight;
    }

    public void removeEdge(int i, int j) {
        adj[i][j] = 0;
    }

    public boolean hasEdge(int i, int j) {
        if (adj[i][j] != 0) {
            return true;
        }
        return false;
    }

    public List<Integer> neighbours(int vertex) {
        List<Integer> edges = new ArrayList<Integer>();
        for (int i = 0; i < vCount; i++)
            if (hasEdge(vertex, i))
                edges.add(i);
        return edges;
    }

    public void printGraph() {
        for (int i = 0; i < vCount; i++) {
            List<Integer> edges = neighbours(i);
            System.out.print(i + ": ");
            for (int j = 0; j < edges.size(); j++) {
                System.out.print(edges.get(j) + " ");
            }
            System.out.println();
        }
    }
}