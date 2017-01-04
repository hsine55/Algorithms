package djikistraShortPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


public class Main {
	
	private static final String FILE_PATH = "testFiles/djikistra/djikistra.txt";
	private static Graph graph = Utils.createGraph(FILE_PATH);

	public static void main(String[] args) {
		List<Integer> assignmentValues = Arrays.asList(7,37,59,82,99,115,133,165,188,197);
		
		//withHeap
		long startTimeWithHeap = System.currentTimeMillis();
		Map<Integer, Integer> shortPaths = djikistraWithHeap(graph, 1);
		long stopTimeWithHeap = System.currentTimeMillis();
	    long elapsedTimeWithHeap = stopTimeWithHeap - startTimeWithHeap;
	    System.out.println("Execution time of djikistra with heap implementation = " + elapsedTimeWithHeap + " ms");
		for(Integer assValue : assignmentValues) {
				System.out.print(shortPaths.get(assValue)+",");
		}
		
		//WithoutHeap
		long startTimeWithoutHeap = System.currentTimeMillis();
		shortPaths = djikistraWithoutHeap(graph, 1);
		long stopTimeWithoutHeap = System.currentTimeMillis();
	    long elapsedTimeWithoutHeap = stopTimeWithoutHeap - startTimeWithoutHeap;
	    System.out.println();
	    System.out.println("Execution time of djikistra with naive implementation = " + elapsedTimeWithoutHeap + " ms");
		for(Integer assValue : assignmentValues) {
				System.out.print(shortPaths.get(assValue)+",");
		}
	}
	
	private static Map<Integer, Integer> djikistraWithHeap(Graph graph, Integer sourceVertexLabel) {
		List<Vertex> visitedVertices = new ArrayList<>();
		Map<Integer, Integer> shortPaths = new HashMap<>();
		Vertex sourceVertex = graph.getVertex(sourceVertexLabel);
		visitedVertices.add(sourceVertex);
		initializeShortPaths(sourceVertex, shortPaths);
		PriorityQueue<Vertex> minimums = new PriorityQueue<>((Vertex v1,Vertex v2) -> v1.getGreedyScore() - v2.getGreedyScore());
		initializeGreedyScores(sourceVertex, graph.listOfVertices.values(), minimums);
		
		while(visitedVertices.size() < graph.listOfVertices.values().size()) {
			Vertex chosenVertex = minimums.poll();
			
			for (Edge e : chosenVertex.getIncidentEdges()) {
					Vertex adjacentVertex = e.getOppositePoint(chosenVertex);
					if(!visitedVertices.contains(e.getOppositePoint(chosenVertex))) {
						int oldGreedyScore = adjacentVertex.getGreedyScore();
						int newGreedyScore = chosenVertex.getGreedyScore() + e.getWeigh();
						if (newGreedyScore < oldGreedyScore) {
							 adjacentVertex.setGreedyScore(newGreedyScore);
							 minimums.remove(adjacentVertex);
							 minimums.add(adjacentVertex);
						}
					}
			}
			shortPaths.put(chosenVertex.getLabel(), chosenVertex.getGreedyScore());
			visitedVertices.add(chosenVertex);
		}
		return shortPaths;
			
	}
	
	private static Map<Integer, Integer> djikistraWithoutHeap(Graph graph, Integer sourceVertexLabel) {
		List<Vertex> visitedVertices = new ArrayList<>();
		Map<Integer, Integer> shortPaths = new HashMap<>();
		Vertex sourceVertex = graph.getVertex(sourceVertexLabel);
		visitedVertices.add(sourceVertex);
		initializeShortPaths(sourceVertex, shortPaths);
		
		while(visitedVertices.size() < graph.listOfVertices.values().size()) {
			int minimumGreedyScore = 1000000;
			Vertex chosenVertex = null;
			for(Vertex v : visitedVertices) {
				for (Edge e : v.getIncidentEdges()) {
					if(!visitedVertices.contains(e.getOppositePoint(v))) {
						int greedyScore = shortPaths.get(v.getLabel()) + e.getWeigh();
						if (greedyScore < minimumGreedyScore) {
							minimumGreedyScore = greedyScore;
							chosenVertex = e.getOppositePoint(v);
						}
					}
				}
			}
			shortPaths.put(chosenVertex.getLabel(), minimumGreedyScore);
			visitedVertices.add(chosenVertex);
		}
		return shortPaths;
			
	}
	

	private static void initializeShortPaths(Vertex sourceVertex, Map<Integer, Integer> shortPaths) {
		shortPaths.put(sourceVertex.getLabel(), 0);
		for (Vertex v : graph.getListOfVertices().values()) {
			if (v.getLabel() == sourceVertex.getLabel()) continue;
			shortPaths.put(v.getLabel(), 1000000);
		}
	}
	
	private static void initializeGreedyScores(Vertex sourceVertex, Collection<Vertex> collection, PriorityQueue minimums) {
		for (Vertex v : collection) {
			if (v.getLabel() == sourceVertex.getLabel()) continue;
			Edge e = v.getEdgeTo(sourceVertex);
			if (e != null) {
				v.setGreedyScore(e.getWeigh());
			} else {
				v.setGreedyScore(1000000);
			}
			minimums.add(v);
		}
	}
	


}
