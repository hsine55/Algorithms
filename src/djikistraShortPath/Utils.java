package djikistraShortPath;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utils {

	public static Graph createGraph(String FILE_PATH) {
		Graph graph = new Graph();
		try (Stream<String> stream = Files.lines(Paths.get(FILE_PATH))) {
			List<String> lines = stream.collect(Collectors.toList());
			for (String line : lines) {
				if (line.isEmpty()) continue;
				String[] vertexData = line.split("\\s+");
				updateGraph(vertexData, graph);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return graph;
	}

	private static void updateGraph(String[] vertexData, Graph graph) {
		Integer currentVertexLabel = Integer.valueOf(vertexData[0]);
		for (int i = 1; i < vertexData.length; i++) {
			String adjacentVertexEdge = vertexData[i];
			updateGraphWithCurrentVertex(currentVertexLabel,adjacentVertexEdge, graph);
		}

	}

	private static void updateGraphWithCurrentVertex(Integer currentVertexLabel, String adjacentVertexEdge, Graph graph) {
		Integer adjacentVertexLabel = Integer
				.valueOf(extractVertexLabel(adjacentVertexEdge));
		Integer edgeWeight = Integer
				.valueOf(extractEdgeWeight(adjacentVertexEdge));
		Vertex currentVertex = graph.getVertex(currentVertexLabel);
		Vertex adjacentVertex = graph.getVertex(adjacentVertexLabel);
		if (currentVertex.getEdgeTo(adjacentVertex) == null) {
			Edge e = new Edge(edgeWeight, currentVertex, adjacentVertex);
			currentVertex.addIncidentEdge(e);
			adjacentVertex.addIncidentEdge(e);
		}
	}

	private static String extractVertexLabel(String vertexEdge) {
		return vertexEdge.substring(0, vertexEdge.indexOf(','));
	}

	private static String extractEdgeWeight(String vertexEdge) {
		return vertexEdge.substring(vertexEdge.indexOf(',') + 1);
	}

}
