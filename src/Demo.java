import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Demo {
	public UndirectedGraph<Integer, DefaultEdge> graph;
	private String file = "input.txt";
	public int num_vertices, num_edges;
	public Set<Integer> graph1, graph2;
	
 	public void run() {
		graph = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
		graph1 = new HashSet<Integer>();
		graph2 = new HashSet<Integer>();
		read();
	}
	
	private void read() {
		try {
			FileReader reader = new FileReader(file);
			Scanner scanner = new Scanner(reader);
			num_vertices = scanner.nextInt();
			num_edges = scanner.nextInt();
			
			for(int i = 1; i <= num_vertices; i++) {
				graph.addVertex(i);
			}
			
			while(scanner.hasNextLine()) {
				graph.addEdge(scanner.nextInt(), scanner.nextInt());
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private void split() {
		
	}
	
	public static void main(String[] args) {
		Demo demo = new Demo();
		demo.run();
		System.out.println(demo.num_edges);
		System.out.println(demo.graph.edgeSet());
	}
}
