package newtry;

public class Edge {
    Vertex fromVertex;
    Vertex toVertex;
    double capacity;
    double flow;
    Edge residual;

    public Edge(Vertex s, Vertex t, double c){
        this.fromVertex = s;
        this.toVertex = t;
        this.capacity =c;
    }

    private boolean isResidual(){
        return capacity == 0;
    }

    public double remainingCapacity(){
        return capacity - flow;
    }

    //Augment path/edge
    public void augment(double bottleneck){
        //Updating the flow of the edge
        flow += bottleneck;

        //Decreasing the flow of the residual/opposite edge
        residual.flow -= bottleneck;
    }
}
