package djikistraShortPath;

import java.util.ArrayList;
import java.util.List;

import djikistraShortPath.Edge;

public class Vertex {
	
	private Integer label;
	private List<Edge> incidentEdges = new ArrayList<Edge>();
	private int greedyScore;
	
	public int getGreedyScore() {
		return greedyScore;
	}

	public void setGreedyScore(int greedyScore) {
		this.greedyScore = greedyScore;
	}

	public Vertex(Integer name) {
		this.label = name;
	}
	
	public Integer getLabel() {
		return label;
	}
	
	public void setLabel(Integer label) {
		this.label = label;
	}
	
	public List<Edge> getIncidentEdges() {
		return incidentEdges;
	}
	
	public void setIncidentEdges(List<Edge> incidentEdges) {
		this.incidentEdges = incidentEdges;
	}
	
	public void addIncidentEdge(Edge e) {
		incidentEdges.add(e);
	}
	
	public void removeIncidentEdge(Edge e) {
		incidentEdges.remove(e);
	}
	
	
	@Override
	public String toString() {
		return "Vertex [label=" + label + ", greedyScore=" + greedyScore
				+ "]";
	}

	public Edge getEdgeTo(Vertex v) {
		for(Edge edge : incidentEdges) {
			if (edge.contains(this,v)) {
				return edge;
			} 
		}
		return null;
	}
	
}
