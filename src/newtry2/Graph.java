package newtry2;

public class Graph
{
	private int VertexCount, EdgeCount;
	private boolean digraph;
	private boolean adjacencies[][];
	public GraphDetails graphDetails;

	Graph(int numVertices, boolean flag)
	{
		VertexCount = numVertices;
		EdgeCount = 0;
		digraph = flag;
		adjacencies = new boolean[VertexCount][VertexCount];

		graphDetails = new GraphDetails();
	}

	int numVertices() {
		return VertexCount;
	}
	int numEdges() {
		return EdgeCount;
	}
	boolean directed() {
		return digraph;
	}

	void insertEdge(Edge e)
	{
		int v = e.v;
		int w = e.w;

		if (adjacencies[v][w] == false)
		{
			EdgeCount++;
			adjacencies[v][w] = true;
		}

		if (!digraph)
		{
			adjacencies[w][v] = true;
		}
	}

	void removeEdge(Edge e)
	{
		int v = e.v;
		int w = e.w;

		if (adjacencies[v][w] == true)
		{
			EdgeCount--;
			adjacencies[v][w] = false;
		}

		if (!digraph)
		{
			adjacencies[w][v] = false;
		}
	}

	boolean edgeTest(int v, int w)
	{
		return adjacencies[v][w];
	}

	AdjArray getAdjList(int v)
	{
		return new AdjArray(v);
	}

	private class AdjArray
	{
		private int i, v;

		AdjArray(int v)
		{
			this.v = v;
			i = -1;
		}

		public int beginning()
		{
			i = -1;
			return next();
		}

		public int next()
		{
			for (i++; i < numVertices(); i++)
			{
				if (edgeTest(v, i) == true)
				return i;
			}

			return -1;
		}

		public boolean end()
		{
			return i >= numVertices();
		}
	}

	private class GraphDetails
	{
		public void display(Graph g)
		{
			System.out.println("Graph details");

			for (int i = 0; i < g.numVertices(); i++)
			{
				System.out.print(i + ": ");
				AdjArray A = g.getAdjList(i);

				for (int j = A.beginning(); !A.end(); j = A.next())
				System.out.print(j + " ");
				System.out.println("");
			}
		}
	}

	public static void main(String[] args)
	{
		Graph myGraph = new Graph(7, false);
		myGraph.insertEdge(new Edge(0, 1));
		myGraph.insertEdge(new Edge(0, 2));
		myGraph.insertEdge(new Edge(0, 5));
		myGraph.insertEdge(new Edge(5, 3));
		myGraph.insertEdge(new Edge(3, 4));
		myGraph.insertEdge(new Edge(4, 6));
		myGraph.insertEdge(new Edge(6, 1));

		myGraph.graphDetails.display(myGraph);

		myGraph.removeEdge(new Edge(0, 1));

		myGraph.graphDetails.display(myGraph);
	}
}