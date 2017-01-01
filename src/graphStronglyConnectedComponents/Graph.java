package graphStronglyConnectedComponents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;




public class Graph {
	Map<Integer, Vertex> listOfVertices = new HashMap<>();
	
	List<Edge> listOfEdges = new ArrayList<Edge>();
	
	public Vertex getVertex(Integer vertexName) {
		if (listOfVertices.containsKey(vertexName)) {
			return listOfVertices.get(vertexName);
		} else {
			Vertex newVertex = new Vertex(vertexName);
			listOfVertices.put(vertexName, newVertex);
			return newVertex;
		}
	}

	public void addEdge(Edge e) {
		listOfEdges.add(e);
	}
	
	public void addVertex(Vertex v) {
		listOfVertices.put(v.getLabel(), v);
	}
	
	public void removeEdge(Edge e) {
		listOfEdges.remove(e);
	}
	
	public Vertex removeVertex(Integer vLabel) {
		return listOfVertices.remove(vLabel);
	}

	public Map<Integer, Vertex> getListOfVertices() {
		return listOfVertices;
	}

	public void setListOfVertices(Map<Integer, Vertex> listOfVertices) {
		this.listOfVertices = listOfVertices;
	}

	public List<Edge> getListOfEdges() {
		return listOfEdges;
	}

	public void setListOfEdges(List<Edge> listOfEdges) {
		this.listOfEdges = listOfEdges;
	}
}
