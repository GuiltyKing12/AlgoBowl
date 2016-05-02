import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

public class Demo {
	public UndirectedGraph<Integer, DefaultEdge> graph;
	private String file = "input.txt";
	public int num_vertices, num_edges;
	public Set<Node> graph1, graph2;
	Map<Integer, Node> vertices;
	
 	public void run() {
		graph = new SimpleGraph<Integer, DefaultEdge>(DefaultEdge.class);
		vertices = new HashMap<Integer, Node>();
		graph1 = new HashSet<Node>();
		graph2 = new HashSet<Node>();
		read();
		split();
	}
	
	private void read() {
		try {
			FileReader reader = new FileReader(file);
			Scanner scanner = new Scanner(reader);
			num_vertices = scanner.nextInt();
			num_edges = scanner.nextInt();
			
			for(int i = 1; i <= num_vertices; i++) {
				graph.addVertex(i);
				vertices.put(i, new Node(i));
			}
			
			while(scanner.hasNextLine()) {
				graph.addEdge(scanner.nextInt(), scanner.nextInt());
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private void split() {
		for(Node node : vertices.values()) {
			if(graph1.size() < graph2.size()) {
				node.graph = "1";
				graph1.add(node);
			}
			else {
				node.graph = "2";
				graph2.add(node);
			}
		}
		for(DefaultEdge edge : graph.edgeSet()) {
			int source = graph.getEdgeSource(edge);
			int target = graph.getEdgeTarget(edge);
			
			if(vertices.get(source).graph != vertices.get(target).graph) {
				vertices.get(source).cross_edge++;
				vertices.get(target).cross_edge++;
			}
		}
	}
	
	private void print() {
		for(Node node : vertices.values()) {
			System.out.print(node.toString() + " ");
		}
		System.out.println();
		for(Node node : graph1) {
			System.out.print(node.toString() + " ");
		}
		System.out.println();
		for(Node node : graph2) {
			System.out.print(node.toString() + " ");
		}
	}
	
	public static void main(String[] args) {
		Demo demo = new Demo();
		demo.run();
		System.out.println(demo.num_edges);
		System.out.println(demo.graph.edgeSet());
		demo.print();
	}
}
