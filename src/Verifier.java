import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class Verifier {
	String file = "output.txt";
	int test_edge = 0;
	public boolean verify(UndirectedGraph<Integer,DefaultEdge> graph, Set<Integer> graph1, Set<Integer> graph2, int cross_edges) {
		for(Integer node : graph1) {
			for(Integer node2: graph2) {
				if(graph.containsEdge(node, node2)) test_edge++;
			}
		}
		
		if(test_edge == cross_edges) return true;
		return false;
	}
}
