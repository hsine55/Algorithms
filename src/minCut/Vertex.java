package minCut;
import java.util.ArrayList;
import java.util.List;


public class Vertex {
	
	private Integer label;
	private List<Edge> incidentEdges = new ArrayList<Edge>();
	
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
		return "Vertex [label=" + label + ", incidentEdges=" + incidentEdges
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
