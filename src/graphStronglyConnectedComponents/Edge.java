package graphStronglyConnectedComponents;

public class Edge {
	
	private Vertex head;
	private Vertex tail;
	
	public Edge(Vertex tail, Vertex head) {
		this.tail = tail;
		this.head = head;
	}

	public Vertex getHead() {
		return head;
	}

	public void setHead(Vertex head) {
		this.head = head;
	}

	public Vertex getTail() {
		return tail;
	}

	public void setTail(Vertex tail) {
		this.tail = tail;
	}

	
	public String prettyPrint() {
		return "Edge: " + tail.getLabel() + " -> " + head.getLabel(); 
	}
	
	

}
