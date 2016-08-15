package graph2;

import graph.GraphNode.State;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Vertex implements Comparable<Vertex>{

	  String name;
	  Map<Vertex,Integer> connectedTo;
	  int distance;
	  Vertex previous;
	  State state;
	  public static enum State{
			UNVISITED,
			VISITING,
			VISITED;
	  }
	  public Vertex(String id) {
	    this.name = id;
	    this.connectedTo = new HashMap<Vertex, Integer>();
	  }
	  
	  public void addNeighbor(Vertex to){
		  connectedTo.put(to, 0);
	  } 
	  
	  public void addNeighbor(Vertex to, int weight){
		  connectedTo.put(to, weight);
	  }
	  
	  
	  public Set<Vertex> getConnections(){
		 return connectedTo.keySet();
	  }
	  
	  public int compareTo(Vertex other) {
	         return Integer.compare(distance, other.distance);
      }
	  
	  public Integer getWeight(Vertex v){
		  return connectedTo.get(v);
	  }
	  public String getName() {
	    return name;
	  }
	  

      void printPath() {
         if (this == this.previous) {
            System.out.printf("%s", this.name);
         } else if (this.previous == null) {
            System.out.printf("%s(unreached)", this.name);
         } else {
            this.previous.printPath();
            System.out.printf(" -> %s(%d)", this.name, this.distance);
         }
      }
	  
	  @Override
	  public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((name == null) ? 0 : name.hashCode());
	    return result;
	  }
	  
	  @Override
	  public boolean equals(Object obj) {
	    if (this == obj)
	      return true;
	    if (obj == null)
	      return false;
	    if (getClass() != obj.getClass())
	      return false;
	    Vertex other = (Vertex) obj;
	    if (name == null) {
	      if (other.name != null)
	        return false;
	    } else if (!name.equals(other.name))
	      return false;
	    return true;
	  }

	  @Override
	  public String toString() {
	    return name;
	  }
}
