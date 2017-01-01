package graphStronglyConnectedComponents;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

		

public class Main {

	private static final String FILE_PATH = "testFiles/scc/SCC.txt";
	private static Graph graph = new Graph();
	private static int size = 0; // global variable to track the size of of an
									// SCC during DFS
	private static List<Vertex> orderedVertices = new ArrayList<Vertex>(); // global
																			// variable
																			// to
																			// track
																			// the
																			// topological
																			// order

	public static void main(String[] argv) {
		createGraph();
		calculateSccs();
	}

	private static void calculateSccs() {
		// rev g
		reverseGraph();

		// dfs on rev g
		for (Vertex v : graph.getListOfVertices().values()) {
			if (!v.isMarked()) {
				dfs(v);
			}
		}

		// unrev g
		reverseGraph();

		// unmark the nodes
		unmarkNodes();

		// dfs on g, this time keeping track of the scc's sizes and respecting
		// the topological order
		List<Integer> sizes = new ArrayList<Integer>();

		for (int i = orderedVertices.size() - 1; i >= 0; i--) {
			Vertex currentVertex = orderedVertices.get(i);
			if (!currentVertex.isMarked()) {
				size = 0;
				dfs(currentVertex);
				int sizeCurrentScc = size;
				sizes.add(sizeCurrentScc);
			}
		}
		sizes.sort((Integer a, Integer b) -> b - a);
		System.out.println(sizes.toString());
	}

	private static void dfs(Vertex root) {
		root.setMarked(true);
		size++;
		List<Edge> outGoingEdges = root.getOutgoingEdges();
		for (Edge e : outGoingEdges) {
			Vertex adjacentVertex = e.getHead();
			if (!adjacentVertex.isMarked()) {
				dfs(e.getHead());
			}
		}
		orderedVertices.add(root);
	}

	private static void reverseEdge(Edge e) {
		Vertex head = e.getHead();
		Vertex tail = e.getTail();
		tail.getOutgoingEdges().remove(e);
		head.addOutGoingEdge(e);
		e.setHead(tail);
		e.setTail(head);
	}

	private static void reverseGraph() {
		for (Edge e : graph.getListOfEdges()) {
			reverseEdge(e);
		}
	}

	private static void unmarkNodes() {
		for (Vertex v : graph.getListOfVertices().values()) {
			v.setMarked(false);
		}
	}

	private static void bfs(Vertex root) {
		Queue<Vertex> unexploredNodes = new LinkedList<>();
		unexploredNodes.add(root);
		while (!unexploredNodes.isEmpty()) {
			Vertex firstEnteredVertex = unexploredNodes.poll();
			if (!firstEnteredVertex.isMarked()) {
				firstEnteredVertex.setMarked(true);
				System.out.print(firstEnteredVertex.getLabel() + " => ");
				for (Edge e : firstEnteredVertex.getOutgoingEdges()) {
					Vertex nextNode = e.getHead();
					if (!nextNode.isMarked()) {
						unexploredNodes.add(e.getHead());
					}
				}
			}

		}
	}

	private static void createGraph() {
		try {
			FileReader fileReader = new FileReader(FILE_PATH);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.isEmpty())
					continue;
				String[] splittedString = line.split("\\s+");
				Integer currentVertexLabel = Integer.valueOf(splittedString[0]);
				Integer adjacentVertexLabel = Integer
						.valueOf(splittedString[1]);
				// adding vertexes
				Vertex currentVertex = graph.getVertex(currentVertexLabel);
				Vertex adjacentVertex = graph.getVertex(adjacentVertexLabel);
				Edge e = new Edge(currentVertex, adjacentVertex);
				currentVertex.addOutGoingEdge(e);
				graph.addEdge(e);
				System.out.println(e.prettyPrint());
			}
			bufferedReader.close();
		} catch (Exception e) {
			System.out.println("Error while reading file line by line: "
					+ e.getMessage());
		}
	}

}
