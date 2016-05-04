import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Node {
	int id;
	int cross_edge = 0;
	Set<Integer> adjacent_nodes;
	String graph;
	
	Node() {
		adjacent_nodes = new HashSet<Integer>();
	}
	
	Node(int id) {
		this.id = id;
		adjacent_nodes = new HashSet<Integer>();
	}
	
	public void update(Map<Integer, Node> vertices) {
		cross_edge = 0;
		for(Integer node : adjacent_nodes) {
			if(vertices.get(node).graph != this.graph) {
				cross_edge++;
			}
		}
	}
	
	@Override
	public String toString() {
		return "Node [id=" + id + ", cross_edge=" + cross_edge + ", graph=" + graph + "]";
	}
	
}
