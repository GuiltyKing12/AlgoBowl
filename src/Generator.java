import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Generator {
	int num_vertices = 1000;
	int num_edges = 99999;
	Set<Pair> pairs = new HashSet<Pair>();
	List<String> lines = new ArrayList<String>();
	Set<String> line = new HashSet<String>();
	Path file = Paths.get("input.txt");
	
	public void generate() {
		Random rand = new Random();
		lines.add(num_vertices + " " + num_edges);
		while(line.size() < (num_edges)) {
			boolean can_add = true;
			int point1 = rand.nextInt(num_vertices) + 1;
			int point2 = rand.nextInt(num_vertices) + 1;
			if(point1 == point2) continue;
			
			for(Pair pair : pairs) {
				if(pair.equals(new Pair(point1, point2))) {
					can_add = false;
					break;
				}
			}
			
			if(can_add) {
				pairs.add(new Pair(point1, point2));
				pairs.add(new Pair(point2, point1));
				line.add(point1 + " " + point2);
			}
		}
		lines.addAll(line);
		try {
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		Generator generate = new Generator();
		generate.generate();
	}
}
