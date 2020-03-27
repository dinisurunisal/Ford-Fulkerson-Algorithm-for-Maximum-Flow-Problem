/**
 *
 * Author : Dinisuru Nisal Gunaratna
 * IIT No : 2018559
 * UOW NO : w1742246
 * Source : https://algorithms.tutorialhorizon.com/max-flow-problem-ford-fulkerson-algorithm/
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class try2 {
    static class Graph {
        int vertices;
        int graph[][]; //Multidimensional arrays

        //maps array index 0 to "s", & successive indexes to a string equivalent
        private String[] arrayIndexStringEquivalents;	//arrayIndexStringEquivalents[0]="S" & arrayIndexStringEquivalents[vertexCount-1]="T"

        public Graph(int vertex, int graph[][], String[] arrayIndexStringEquivalents) {
            this.vertices = vertex;
            this.graph = graph;
            this.arrayIndexStringEquivalents=arrayIndexStringEquivalents;	//pass by reference, but don't care since main doesn't modify this
        }

        public int findMaxFlow(int source, int sink) {
            //residual graph
            int[][] residualGraph = new int[vertices][vertices];

            //initialize residual graph same as original graph
            for (int i = 0; i <vertices ; i++) {
                for (int j = 0; j <vertices ; j++) {
                    residualGraph[i][j] = graph[i][j];
                }
            }

            //initialize parent [] to store the path Source to destination
            int [] parent = new int[vertices];

            int max_flow = 0; //initialize the max flow

            while(isPathExist_BFS(residualGraph, source, sink, parent)){
                //if here means still path exist from source to destination

                //parent [] will have the path from source to destination
                //find the capacity which can be passed though the path (in parent[])

                String pathString = "";		//Shows the augmented path taken

                int flow_capacity = Integer.MAX_VALUE;

                int t = sink;
//
//                while(t!=source){
//                    int s = parent[t];
//                    flow_capacity = Math.min(flow_capacity, residualGraph[s][t]);
//                    t = s;
//
//                    pathString = " --> "+arrayIndexStringEquivalents[t]+ pathString;
//                }

                for (int v=t; v!=source; v=parent[v])
                {
                    int s = parent[v];
                    flow_capacity = Math.min(flow_capacity, residualGraph[s][v]);

                    pathString = " --> "+arrayIndexStringEquivalents[v]+ pathString;
                }

                pathString= "S"+pathString;		//loop stops before it gets to S, so add S to the beginning
                System.out.println("Augmentation path \n"+pathString);
                System.out.println("bottleneck (min flow on path added to max flow) = "+flow_capacity +"\n");

                //update the residual graph
                //reduce the capacity on fwd edge by flow_capacity
                //add the capacity on back edge by flow_capacity
                t = sink;

//                while(t!=source){
//                    int s = parent[t];
//                    residualGraph[s][t]-=flow_capacity;
//                    residualGraph[t][s]+=flow_capacity;
//                    t = s;
//                }

                for (int v=t; v != source; v=parent[v])
                {
                    int s = parent[v];
                    residualGraph[s][v] -= flow_capacity;
                    residualGraph[v][s] += flow_capacity;
                }

                //add flow_capacity to max value
                max_flow+=flow_capacity;
            }
            return max_flow;
        }

        public boolean isPathExist_BFS(int residualGraph [][], int src, int dest, int parent []){
            boolean pathFound = false;

            //create visited array [] to
            //keep track of visited vertices
            boolean [] visited = new boolean[vertices];

            //Create a queue for BFS
            Queue<Integer> queue = new LinkedList<>();

            //insert the source vertex, mark it visited
            queue.add(src);
            parent[src] = -1;
            visited[src] = true;

            while(queue.isEmpty()==false){
                int u = queue.poll();

                //visit all the adjacent vertices
                for (int v = 0; v <vertices ; v++) {
                    //if vertex is not already visited and u-v edge weight >0
                    if(visited[v]==false && residualGraph[u][v]>0) {
                        queue.add(v);
                        parent[v] = u;
                        visited[v] = true;
                    }
                }
            }
            //check if dest is reached during BFS
            pathFound = visited[dest];
            return pathFound;
        }
    }

    // Driver program to test above functions
    public static void main(String[] args) throws FileNotFoundException {

        String[] arrayIndexStringEquivalents = {"S", "1", "2", "3", "4", "T"};	//map human readable names to each vertex, not just array indexes

        int vertices = 6;
        File file =  new File("C:\\Users\\dinis\\IdeaProjects\\AlgoCoursework\\src\\k1.txt");
        Scanner scan = new Scanner(file);
        int row = scan.nextInt();
        int col = scan.nextInt();
        int[][] graph = new int[row][col];
        for(int r=0; r<row; r++) {
            for(int c=0; c<col; c++) {
                graph[r][c] = scan.nextInt();

            }
        }
//        int graph[][] = { {0, 10, 8, 0, 0, 0},
//                {0, 0, 5, 5, 0, 0},
//                {0, 4, 0, 0, 10, 0},
//                {0, 0, 9, 0, 10, 3},
//                {0, 0, 0, 6, 0, 14},
//                {0, 0, 0, 0, 0, 0}
//        };
        Graph g = new Graph(vertices, graph, arrayIndexStringEquivalents);
        int source = 0;
        int destination = 5;
        int max_flow = g.findMaxFlow(source,destination);
        System.out.println("Maximum flow from source: " + source + " to destination: " + destination
                + " is: " + max_flow);
    }
}