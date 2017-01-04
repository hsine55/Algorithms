package djikistraShortPath;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Main {
	
	private static final String FILE_PATH = "testFiles/djikistra/djikistra.txt";
	private static Graph graph = Utils.createGraph(FILE_PATH);

	public static void main(String[] args) {
		List<Integer> assignmentValues = Arrays.asList(7,37,59,82,99,115,133,165,188,197);
		
		Map<Integer, Integer> shortPaths = djikistra(graph, 1);
		for(Integer assValue : assignmentValues) {
				System.out.print(shortPaths.get(assValue)+",");
		}
	}
	
	private static Map<Integer, Integer> djikistra(Graph graph, Integer sourceVertexLabel) {
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
	


}
