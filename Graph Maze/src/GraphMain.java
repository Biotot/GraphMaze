import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class GraphMain {

	HashMap<Character, Vertex> vertices;
	String vertexNames = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghij";
	String route;
	
	
	public void depthFirstSearch(char lastVertex, ArrayList<Edge> edges){
		Edge lastEdge = edges.get(edges.size()-1);
		//break point;
		if (lastEdge.destination == 'j'){
			String output = "A";
			for (Edge current : edges){
				output+=current;
			}
			System.out.println(output);
			System.out.println("Length of route: " + (edges.size()+1));
			return;
		}
		
		Vertex currentVert = vertices.get(lastEdge.destination);
		ArrayList<Edge> possibleRoutes = currentVert.possibleRoutes(lastEdge);
		
		
		for (Edge possible : possibleRoutes){
			//avoid loops
			if ((!edges.contains(possible)) && (possible.destination != lastVertex)){
				ArrayList<Edge> currentRoute = new ArrayList<Edge>(edges);
				currentRoute.add(possible);
				depthFirstSearch(currentVert.name, currentRoute);
			}
		}
		
	}
	
	public GraphMain(){
		try {
			loadFile();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Vertex vert : vertices.values()){
			System.out.println(vert);
		}
		//seed with the starting point. This method is example specific.
		depthFirstSearch('A', vertices.get('A').getEdges());
	}
	
	public void loadFile() throws FileNotFoundException{
		vertices = new HashMap<Character, Vertex>();
		for (char name : vertexNames.toCharArray()){
			vertices.put(name, new Vertex(name));
		}
		
		Scanner scan = new Scanner(new File("src/defaultInput.txt"));
		//throw out the first line
		scan.nextLine();
		
		while (scan.hasNextLine()){
			Scanner lineScanner = new Scanner(scan.nextLine());
			char left = lineScanner.next().charAt(0);
			char right = lineScanner.next().charAt(0);
			char company = lineScanner.next().charAt(0);
			char type = lineScanner.next().charAt(0);
			
			//Adds 2 directional edges. Helps for cycle detection
			(vertices.get(left)).addEdge(new Edge(company, type, right));
			(vertices.get(right)).addEdge(new Edge(company, type, left));
			
			lineScanner.close();
		}
		scan.close();
	}
	
	public static void main(String [] args){
		GraphMain tommy = new GraphMain();
		System.out.println(tommy);
	}
}
