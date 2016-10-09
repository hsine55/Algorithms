package minCut;
import java.util.ArrayList;
import java.util.List;


public class Edge {
	
	private List<Vertex> endPoints = new ArrayList<Vertex>();
	
	public List<Vertex> getEndPoints() {
		return endPoints;
	}

	public void setEndPoints(List<Vertex> endPoints) {
		this.endPoints = endPoints;
	}
	
	public boolean contains(Vertex v1, Vertex v2) {
		return endPoints.contains(v1) && endPoints.contains(v2);
	}
	
	public Vertex getOppositePoint(Vertex v1) {
		return endPoints.get(1- endPoints.indexOf(v1));
	}
	
	public void replaceEndPoint(Vertex v1, Vertex v2) {
		endPoints.remove(v1);
		endPoints.add(v2);
	}

	@Override
	public String toString() {
		return "Edge [endPoints=" + endPoints.get(0).getLabel() + ", "+ endPoints.get(1).getLabel()+ "]";
	}
	
	
}
