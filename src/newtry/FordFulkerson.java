package newtry;

public class FordFulkerson {
    Vertex source;
    Vertex sink;
    int visitedToken;
    double maxFlow;

    //Setting Source & Sink
    public FordFulkerson(Vertex source, Vertex sink){
        this.source =source;
        this.sink = sink;
        this.visitedToken =0;
        this.maxFlow = 0;
    }

    //Solve For max flow using Ford Fulkerson Method
    public void solve(){
        visitedToken =1;

        //Assuming initially that infinity amount can flow through the network
        double flow = Double.MAX_VALUE;

        //Finding augment path until there is none left
        do{
            //If returned value of flow is not 0 then there is augment path
            flow = hasAugmentPath(flow, source);

            //Adding flow to calculate max flow
            maxFlow += flow;
            visitedToken++; //Updating visited token for next check
        }
        while(flow != 0);
    }

    private double hasAugmentPath(double flow, Vertex vertex) {

        //If current Vertex is sink then we reached the target
        //It means flow is possible, so return possible flow
        if(vertex == sink)
            return flow;

        //Updating the current visit of the vertex to visitedToken
        //So that we do visit the same vertex again
        vertex.currentVisit =visitedToken;

        //Checking if flow can be continued through any connected adjacentEdges
        //Flow can only be continued if remaining capacity of edges is > 0
        for(Edge edge: vertex.adjacentEdges){

            if(edge.remainingCapacity() > 0 && edge.toVertex.currentVisit != visitedToken){

                //Checking min of flow and remaining capacity
                //If remaining capacity == 0, then min will be 0
                //It means no more flow is possible through the network
                double bottleneck = hasAugmentPath(Double.min(flow,edge.remainingCapacity()),edge.toVertex);

                //By Backtracking we augment each edge if there is any flow possible
                if(bottleneck > 0){
                    edge.augment(bottleneck);
                    return bottleneck;
                }
            }

        }

        //Returning 0 if no flow is possible through any connected egdes
        return 0;
    }
}

