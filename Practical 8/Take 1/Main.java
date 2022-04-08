public class Main {

    public static void main(String[] args) {

        Vertex vertexV = new Vertex("V");
        Vertex vertexU = new Vertex("U");
        Vertex vertexW = new Vertex("W");
        Vertex vertexX = new Vertex("X");

        vertexV.addNeighbour(new Edge(vertexV, vertexU, 4));
        vertexV.addNeighbour(new Edge(vertexV, vertexW, -3));
        vertexU.addNeighbour(new Edge(vertexU, vertexW, -2));
        vertexW.addNeighbour(new Edge(vertexW, vertexX, 2));
        vertexX.addNeighbour(new Edge(vertexX, vertexV, -1));

        Graph graph = new Graph();
        graph.addVertex(vertexV);
        graph.addVertex(vertexU);
        graph.addVertex(vertexW);
        graph.addVertex(vertexX);

        Vertex startVertex = vertexV;
        Vertex endVertex = vertexU;;

        System.out.println("Minimum distance from " + startVertex.getName() + " to " + endVertex.getName() + " : " + graph.getShortestPathDistance(startVertex, endVertex));
        System.out.println("Shortest Path from " + startVertex.getName() + " to " + endVertex.getName() + " : " + graph.getShortestPath(startVertex, endVertex));
    }
}