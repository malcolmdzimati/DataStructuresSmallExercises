import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeightedDirectedGraph {

	private List<Vertex> verticesList; //contains all vertices in this graph

	public WeightedDirectedGraph() {
		this.verticesList = new ArrayList<>();
	}

	public void addVertex(Vertex vertex) {
		this.verticesList.add(vertex);
	}

	////// Implement the methods below this line //////

	public List<Vertex> getShortestPath(Vertex sourceVertex, Vertex targetVertex) {
		
		return null;
	}
}