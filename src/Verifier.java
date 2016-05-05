import java.io.FileReader;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Verifier {
	int test_edge = 0;
	int cross_edge = 0;
	private String file = "inputs/input_group14.txt";
	private String output = "outputs/output_from_14_to_14.txt";
	public int num_vertices, num_edges, previous_max1, previous_max2;
	public Set<Integer> graph1, graph2;
	public Set<Pair> pairs;

	private void read() {
		pairs = new HashSet<Pair>();
			
		try {
			FileReader reader = new FileReader(file);
			Scanner scanner = new Scanner(reader);
			num_vertices = scanner.nextInt();
			num_edges = scanner.nextInt();

			while(scanner.hasNextLine()) {
				pairs.add(new Pair(scanner.nextInt(), scanner.nextInt()));
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void generate() {
		graph1 = new HashSet<Integer>();
		graph2 = new HashSet<Integer>();
		try {
			FileReader reader = new FileReader(output);
			Scanner scanner = new Scanner(reader);
			cross_edge = scanner.nextInt();
			
			while(scanner.hasNextInt()) {
				if(graph1.size() < num_vertices / 2) {
						graph1.add(scanner.nextInt());
				}
				else graph2.add(scanner.nextInt());	
			}
			
			System.out.println(graph1.size());
			System.out.println(graph1.toString());
			System.out.println(graph2.size());
			System.out.println(graph2.toString());
			
			System.out.println("Graphs set up");
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public boolean verify() {
		boolean next = false;
		for(Pair pair : pairs) {
			for(Integer node : graph1) {
				for(Integer node2: graph2) {
					if(pair.equals(new Pair(node, node2))) {
						test_edge++;
						next = true;
					}
					if(next) break;
				}
				if(next) break;
			}
			if(next) next = false;
		}
		
		if(test_edge == cross_edge) return true;
		return false;
	}
	
	public static void main(String[] args) {
		Verifier verifier = new Verifier();
		verifier.read();
		verifier.generate();
		if(verifier.verify()) System.out.println("Yes: Test_Edge: " + verifier.test_edge + " Cross_edges: " + verifier.cross_edge);
		else System.out.println("No: Test_Edge: " + verifier.test_edge + " Cross_edges: " + verifier.cross_edge);
	}
	
}
