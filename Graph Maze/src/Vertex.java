import java.util.ArrayList;



public class Vertex {
	char name;
	ArrayList<Edge> edges;
	
	public Vertex(char newName){
		edges = new ArrayList<Edge>();
		this.name = newName;
	}
	
	public void addEdge(Edge newEdge){
		edges.add(newEdge);
	}
	
	public ArrayList<Edge> possibleRoutes(Edge entrance){
		ArrayList<Edge> routes = new ArrayList<Edge>();
		for (Edge current : edges){
			if (current.canIRide(entrance)){
				routes.add(current);
			}
		}
		return routes;
		
	}
	public ArrayList<Edge> getEdges(){
		return edges;
	}
	public String toString(){
		return name + " : " + edges;
	}
	
}
