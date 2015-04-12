
public class Edge {

	public char company;
	public char type;
	public char destination;
		
	public Edge(char newCompany, char newType, char newDestination){
		this.company = newCompany;
		this.type = newType;
		this.destination = newDestination;
	}
		
	public boolean canIRide(Edge otherEdge){
		return ((this.company==otherEdge.company)||(this.type==otherEdge.type));
	}
	
	public String toString(){
		return (">"+destination);
		//return (company + " " + type + " " + destination);
	}
}
