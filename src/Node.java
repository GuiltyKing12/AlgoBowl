public class Node {
	int id;
	int cross_edge = 0;
	String graph;
	
	Node() {}
	Node(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Node [id=" + id + ", cross_edge=" + cross_edge + ", graph=" + graph + "]";
	}
	
}
