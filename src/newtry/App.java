package newtry;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Author : Dinisuru Nisal Gunaratna
 * IIT No : 2018559
 * UOW NO : w1742246
 * Source : https://pencilprogrammer.com/algorithms/ford-fulkerson-max-flow/
 *
 */

public class App {

    public static void main(String args[]){

        //Creating Vertexs

        List<Vertex> vertexs = new ArrayList<>();

        vertexs.add(new Vertex("A"));          //0
        vertexs.add(new Vertex("B"));          //1
        vertexs.add(new Vertex("C"));          //2
        vertexs.add(new Vertex("D"));          //3

        //Creating Edges

        List<Edge> edges = new ArrayList<>();

        edges.add(new Edge(vertexs.get(0),vertexs.get(1),10));
        edges.add(new Edge(vertexs.get(1),vertexs.get(3),4));
        edges.add(new Edge(vertexs.get(2),vertexs.get(3),6));
        edges.add(new Edge(vertexs.get(0),vertexs.get(2),6));
        edges.add(new Edge(vertexs.get(2),vertexs.get(1),10));

        //Edges and vertices are created but not connected

        for(Edge edge: edges){

            //Creating opposite edge
            Edge opposite = new Edge(edge.toVertex,edge.fromVertex,0);

            edge.residual = opposite;
            opposite.residual = edge;

            //Connecting vertices with edges and residual edges
            edge.fromVertex.addNeighbour(edge);
            edge.toVertex.addNeighbour(opposite);
        }

        //Source - 0 -> A
        //Sink - 3 -> D
        FordFulkerson fordFulkerson = new FordFulkerson(vertexs.get(0),vertexs.get(3));
        fordFulkerson.solve();

        System.out.println("Max Flow:"+fordFulkerson.maxFlow);
    }
}