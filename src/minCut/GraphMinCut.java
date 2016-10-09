package minCut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GraphMinCut {
	private static final String FILE_PATH = "minCut.txt";
	private static Map<Integer, List<Integer>> adjLists = new HashMap<>();
	private static final Random rand = new Random(); 
	private static Graph graph = new Graph();
	
	private static void readGraphIntoAdjList() {
		try {
			FileReader fileReader = new FileReader(FILE_PATH);
		    BufferedReader bufferedReader = new BufferedReader(fileReader);
		    String line;
		    while((line = bufferedReader.readLine()) != null) {
		    	String[] splittedString = line.split("\\s+");
		    	Integer currentVertex = Integer.valueOf(splittedString[0]);
		    	List<Integer> adjacentVertices = new ArrayList<Integer>();
		    	for(int i=1; i<splittedString.length; i++) {
		    		adjacentVertices.add(Integer.valueOf(splittedString[i]));
		    	}
		    	adjLists.put(currentVertex, adjacentVertices);
		    }
		    bufferedReader.close();
		}
		catch(Exception e){
			System.out.println("Error while reading file line by line:" + e.getMessage());
		}
	}
	
	private static void createGraph() {
		
		for(Integer vertexName : adjLists.keySet()) {
			Vertex currentVertex = graph.getVertex(vertexName);
			for(Integer edgeTo: adjLists.get(vertexName)) {
				Vertex v2 = graph.getVertex(edgeTo);
				Edge e;
				if( (e = v2.getEdgeTo(currentVertex)) == null ) {
					e = new Edge();
					e.getEndPoints().add(v2);
					e.getEndPoints().add(currentVertex);
					graph.addEdge(e);
					v2.addIncidentEdge(e);
					currentVertex.addIncidentEdge(e);
				}
			}
		}
	}
	
	private static void contract(Edge e) {
		graph.removeEdge(e);
		Vertex v1 = e.getEndPoints().get(0);
		Vertex v2 = e.getEndPoints().get(1);
		v1 = cleanVertex(e,v1);
		v2 = cleanVertex(e,v2);
		Vertex mergedVertex = new Vertex(v1.getLabel());
		redirectEdges(v1, mergedVertex);
		redirectEdges(v2, mergedVertex);
		graph.addVertex(mergedVertex);
	}
	
	private static Edge chooseRandomEdge() {
		Edge randomEdge = graph.getListOfEdges().get(rand.nextInt(graph.getListOfEdges().size()));
		return randomEdge;
	}
	
	private static void redirectEdges(Vertex fromV, Vertex toV) {
		for(Iterator<Edge> it=fromV.getIncidentEdges().iterator(); it.hasNext(); ) {
			Edge e = it.next();
			it.remove();
			if (e.getOppositePoint(fromV) == toV) {
				graph.removeEdge(e);
				toV.removeIncidentEdge(e);
			} else {
				e.replaceEndPoint(fromV, toV);
				toV.addIncidentEdge(e);
			}
		}
	}
	
	public static int calculateMinCut() {
		while(graph.getListOfVertices().size() > 2) {
			Edge e = chooseRandomEdge();
			//System.out.println("deleted edge " + e.toString());
			contract(e);
			//System.out.println("Remaining edges are : " + graph.getListOfEdges().toString());
		}
		return graph.getListOfEdges().size();
	}
	
	private static Vertex cleanVertex(Edge e, Vertex v) {
		graph.removeVertex(v.getLabel());
		v.removeIncidentEdge(e);
		return v;
	}
	
	public static void main(String[] args) {
		int n=1;
		int minCut = 100000;
		while(n<100) {
			readGraphIntoAdjList();
			createGraph();
			int currentCut = calculateMinCut(); 
			if (currentCut < minCut) {
				minCut = currentCut;
			}
			n +=1;
		}
		System.out.println(minCut);
	}
}
