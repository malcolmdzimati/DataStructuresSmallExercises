// Name: Batsirai Malcolm Dzimati
// Student number: u20456078
import java.security.DigestException;
import java.util.ArrayList;
import java.util.List;
 
public class Graph {
 
	private List<Vertex> verticesList;

	public Graph() {
		this.verticesList = new ArrayList<>();
	}

	public void addVertex(Vertex vertex) {
		this.verticesList.add(vertex);
	}

	public void reset() {
		for(Vertex vertex : verticesList) {
			vertex.setVisited(false);
			vertex.setPredecessor(null);
			vertex.setDistance(Double.POSITIVE_INFINITY);
		}
	}

	////// Implement the methods below this line //////

	public double[][] fw(){
		int vsi = this.verticesList.size();
		double[][] dist=new double[vsi][];
		for(int i=0; i<vsi; i++){
			dist[i]= new double[vsi]; 
			for(int j=0; j<vsi; j++){
				dist[i][j]=Double.POSITIVE_INFINITY;
			}
		}

		for(int i=0; i<verticesList.size(); i++){
			Vertex v = verticesList.get(i);
			for(int j=0; j<v.getAdjacenciesList().size(); j++){
				Edge e = v.getAdjacenciesList().get(j);
				dist[i][j]=e.getWeight();
			}
		}

		for(int i=0; i<verticesList.size(); i++){
			dist[i][i]=0;
		}

		for(int i=0; i<vsi; i++){
			for(int j=0; j<vsi; j++){
				for(int k=0; k<vsi; k++){
					if(dist[j][k]>dist[j][i]+dist[i][k]){
						dist[j][k]=dist[j][i]+dist[i][k];
					}
				}
			}
		}
		return dist;
	}


	public List<Vertex> getShortestPath(Vertex sourceVertex, Vertex targetVertex){
		sourceVertex.setDistance(0);

		for(int i=0; i<verticesList.size()-1; i++){
			for(Vertex v:verticesList){
				List<Edge> edges = v.getAdjacenciesList();
				for(Edge e:edges){
					if(e.getStartVertex().getDistance()+e.getWeight()<e.getEndVertex().getDistance()){
						//if(!e.getEndVertex().isVisited()){
							e.getEndVertex().setDistance(e.getStartVertex().getDistance()+e.getWeight());
							e.getEndVertex().setPredecessor(e.getStartVertex());
					//	}
						e.getEndVertex().setVisited(true);
					}
				}
			}
		}

		if(targetVertex.getDistance()==Double.POSITIVE_INFINITY){
			List<Vertex> result=new ArrayList<>();
			return result;
		}

		for(Vertex v:verticesList){
			List<Edge> edges = v.getAdjacenciesList();
			for(Edge e:edges){
				if(e.getStartVertex().getDistance()+e.getWeight()<e.getEndVertex().getDistance()){
						return null;
				}
				e.getEndVertex().setVisited(true);
			}
		}

		List<Vertex> result=new ArrayList<>();
		Vertex cur=targetVertex;
		while(cur!=sourceVertex){
			result.add(cur);
			cur=cur.getPredecessor();
		}
		result.add(sourceVertex);
		List<Vertex> f = new ArrayList<>();
		for(int i=0; i<result.size(); i++){
			f.add(result.get(result.size()-i-1));
		}
		return f;
	}

	public double getShortestPathDistance(Vertex sourceVertex, Vertex targetVertex) {
		sourceVertex.setDistance(0);

		for(int i=0; i<verticesList.size()-1; i++){
			for(Vertex v:verticesList){
				List<Edge> edges = v.getAdjacenciesList();
				for(Edge e:edges){
					if(e.getStartVertex().getDistance()+e.getWeight()<e.getEndVertex().getDistance()){
						//if(!e.getEndVertex().isVisited()){
							e.getEndVertex().setDistance(e.getStartVertex().getDistance()+e.getWeight());
							e.getEndVertex().setPredecessor(e.getStartVertex());
						//}
						e.getEndVertex().setVisited(true);
					}
				}
			}
		}

		for(Vertex v:verticesList){
			List<Edge> edges = v.getAdjacenciesList();
			for(Edge e:edges){
				if(e.getStartVertex().getDistance()+e.getWeight()<e.getEndVertex().getDistance()){
						return Double.NEGATIVE_INFINITY;
				}
				e.getEndVertex().setVisited(true);
			}
		}

		return targetVertex.getDistance();
	}

}