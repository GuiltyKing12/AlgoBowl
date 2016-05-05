import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class Verifier {
	String file = "output.txt";
	int test_edge = 0;
	public boolean verify(Set<Pair> pairs, Set<Integer> graph1, Set<Integer> graph2, int cross_edges) {
		for(Pair pair : pairs) {
			for(Integer node : graph1) {
				for(Integer node2: graph2) {
					if(pair.equals(new Pair(node, node2))) {
						test_edge++;
						continue;
					}
				}
			}
		}
		
		if(test_edge == cross_edges) return true;
		return false;
	}
}
