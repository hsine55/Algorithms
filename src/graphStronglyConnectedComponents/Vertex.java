package graphStronglyConnectedComponents;

import java.util.ArrayList;
import java.util.List;

public class Vertex {

	private Integer label;
	private List<Edge> outGoingEdges = new ArrayList<Edge>();
	private boolean marked = false;
	
	public boolean isMarked() {
		return marked;
	}

	public void setMarked(boolean marked) {
		this.marked = marked;
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
	
	public List<Edge> getOutgoingEdges() {
		return outGoingEdges;
	}
	
	public void setOutGoingEdges(List<Edge> outGoingEdges) {
		this.outGoingEdges = outGoingEdges;
	}
	
	public void addOutGoingEdge(Edge e) {
		outGoingEdges.add(e);
	}
	
	public void removeIncidentEdge(Edge e) {
		outGoingEdges.remove(e);
	}
	
	
	@Override
	public String toString() {
		return "Vertex [label=" + label + ", outGoingEdges=" + outGoingEdges
				+ "]";
	}

	public Edge getEdgeTo(Vertex v) {
		for(Edge edge : outGoingEdges) {
			if (edge.getHead().getLabel().equals(v.getLabel())) {
				return edge;
			} 
		}
		return null;
	}
}
