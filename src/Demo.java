import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Demo {
	private String file = "input_group14.txt";
	public int num_vertices, num_edges, previous_max1, previous_max2;
	public Set<Integer> graph1, graph2;
	public Set<Pair> pairs;
	Map<Integer, Node> vertices;
	public int cross_edge;
	
 	public void run() {
		vertices = new HashMap<Integer, Node>();
		graph1 = new HashSet<Integer>();
		graph2 = new HashSet<Integer>();

		pairs = new HashSet<Pair>();
		read();
		split();
		find_min();
		for(Node node : vertices.values()) {
			node.update(vertices);
		}
	}
	
	private void read() {
		try {
			FileReader reader = new FileReader(file);
			Scanner scanner = new Scanner(reader);
			num_vertices = scanner.nextInt();
			num_edges = scanner.nextInt();
			
			for(int i = 1; i <= num_vertices; i++) {
				vertices.put(i, new Node(i));
			}
			
			while(scanner.hasNextLine()) {
				pairs.add(new Pair(scanner.nextInt(), scanner.nextInt()));
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	private void split() {
		for(Node node : vertices.values()) {
			Random rand = new Random();
			int send = rand.nextInt(100);
			if(send % 2 == 2) {
				if(graph1.size() < num_vertices / 2) {
					node.graph = "1";
					graph1.add(node.id);
				}
				else {
					node.graph = "2";
					graph2.add(node.id);
				}
			}
			else {
				if(graph2.size() < num_vertices / 2) {
					node.graph = "2";
					graph2.add(node.id);
				}
				else {
					node.graph = "1";
					graph1.add(node.id);
				}
			}
		}
		
		for(Pair pair : pairs) {
			int source = pair.point1;
			int target = pair.point2;
			
			if(vertices.get(source).graph != vertices.get(target).graph) {
				vertices.get(source).cross_edge++;
				vertices.get(target).cross_edge++;
			}
			vertices.get(source).adjacent_nodes.add(vertices.get(target).id);
			vertices.get(target).adjacent_nodes.add(vertices.get(source).id);
		}
	}
	
	private void find_min() {
		int count = 0;
		while(count < num_edges) {
			int idmax1 = 0;
			int max_edge1 = 0;
			for(Integer node : graph1) {
				if(vertices.get(node).cross_edge > max_edge1 && node != previous_max2) {
					idmax1 = node;
					max_edge1 = vertices.get(node).cross_edge;
				}
			}
			
			if(idmax1 == 0) break;
			
			int idmax2 = 0;
			int max_edge2 = 0;
			for(Integer node : graph2) {
				if(vertices.get(node).cross_edge > max_edge2 && node != previous_max1) {
					idmax2 = node;
					max_edge2 = vertices.get(node).cross_edge;
				}
			}
			
			if(idmax2 == 0) break;
			
			if(pairs.contains(new Pair(idmax2, idmax1)) || pairs.contains(new Pair(idmax1, idmax2))) {
				//System.out.println(idmax1 + " " + idmax2);
				for(Integer node : graph2) {
					if(!vertices.get(idmax1).adjacent_nodes.contains(node) && node != idmax2) {
						idmax2 = node;
					}
				}
				//System.out.println(idmax1 + " " + idmax2);
			}
			
			previous_max1 = idmax2;
			previous_max2 = idmax1;
			
			graph1.remove(idmax1);
			graph2.remove(idmax2);
			
			vertices.get(idmax1).graph = "2";
			vertices.get(idmax2).graph = "1";
			
			graph1.add(idmax2);
			graph2.add(idmax1);
			
			update(vertices.get(idmax1));
			update(vertices.get(idmax2));
			
			count++;
			if(count % 10000 == 0) System.out.println("here");
		}
	}
	
	
	private void update(Node node) {
		node.update(vertices);
		for(Integer adj_node : node.adjacent_nodes) {
			vertices.get(adj_node).update(vertices);
		}
	}
	
	private int sum_cross_edges() {
		int cross_edge = 0;
		for(Integer node : graph1) {
			cross_edge += vertices.get(node).cross_edge;
		}
		return cross_edge;
	}
	
	
	private void print() {
		cross_edge = sum_cross_edges();
		ArrayList<String> lines = new ArrayList<String>();
		
		for(Node node : vertices.values()) {
			node.update(vertices);
			if(node.cross_edge != 0) System.out.print(node.toString() + " ");
		}
		
		System.out.println();
		
		lines.add(cross_edge + " ");
		System.out.println();
		System.out.println("graph1 " + graph1.size());
		String line1 = "";
		for(Integer node : graph1) {
			System.out.print(node.toString() + " ");
			line1 += (node + " ");
		}
		lines.add(line1);
		
		String line2 = "";
		System.out.println();
		System.out.println("graph2 " + graph2.size());
		for(Integer node : graph2) {
			System.out.print(node.toString() + " ");
			line2 += (node + " ");
		}
		lines.add(line2);
		System.out.println();
		System.out.println("edges " + pairs.size());
		System.out.println("cross_edge " + cross_edge);
		
		Path file = Paths.get("output.txt");
		
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		Demo demo = new Demo();
		demo.run();
		System.out.println("num_edges " + demo.num_edges);
		//System.out.println(demo.pairs.toString());
		demo.print();
	}
}
