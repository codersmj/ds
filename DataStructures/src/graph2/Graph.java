package graph2;

import graph2.Vertex.State;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NavigableSet;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;


public class Graph {
  private final Map<String, Vertex> vertices;
  private int numOfVertices;
  public Graph() {
    this.vertices = new HashMap<String, Vertex>()	;
    this.numOfVertices = 0;
    
  }

  public Set<String> getVertices() {
    return vertices.keySet();
  }

  public Vertex addVertex(String v){
	  numOfVertices++;
	  Vertex vertex = new Vertex(v);
	  vertices.put(v, vertex);
	  return vertex;
  }
  
  public void addEdge(String fromStr, String toStr, Integer weight){
	  Vertex fromVertex = vertices.get(fromStr);
	  if(fromVertex == null){
		  fromVertex =addVertex(fromStr);
	  }
	  Vertex toVertex = vertices.get(toStr);
	  if(toVertex == null){
		  toVertex = addVertex(toStr);
	  }
	  fromVertex.addNeighbor(toVertex, weight);
  }
  public void dijkstra(String startName) {
      if (!vertices.containsKey(startName)) {
         System.err.printf("vertices doesn't contain start vertex \"%s\"\n", startName);
         return;
      }
      final Vertex source = vertices.get(startName);
      NavigableSet<Vertex> q = new TreeSet<>();
 
      // set-up vertices
      for (Vertex v : vertices.values()) {
         v.previous = v == source ? source : null;
         v.distance = v == source ? 0 : Integer.MAX_VALUE;
         q.add(v);
      }
 
      dijkstra(q);
   }
 
   /** Implementation of dijkstra's algorithm using a binary heap. */
   private void dijkstra(final NavigableSet<Vertex> q) {      
      Vertex u, v;
      while (!q.isEmpty()) {
 
         u = q.pollFirst(); // vertex with shortest distanceance (first iteration will return source)
         if (u.distance == Integer.MAX_VALUE) break; // we can ignore u (and any other remaining vertices) since they are unreachable
 
         //look at distanceances to each neighbour
         for (Map.Entry<Vertex, Integer> a : u.connectedTo.entrySet()) {
            v = a.getKey(); //the neighbour in this iteration
 
            final int alternatedistance = u.distance + a.getValue();
            if (alternatedistance < v.distance) { // shorter path to neighbour found
               q.remove(v);
               v.distance = alternatedistance;
               v.previous = u;
               q.add(v);
            } 
         }
      }
   }
 
   /** Prints a path from the source to the specified vertex */
   public void printPath(String endName) {
      if (!vertices.containsKey(endName)) {
         System.err.printf("vertices doesn't contain end vertex \"%s\"\n", endName);
         return;
      }
 
      vertices.get(endName).printPath();
      System.out.println();
   }
   /** Prints the path from the source to every vertex (output order is not guaranteed) */
   public void printAllPaths() {
      for (Vertex v : vertices.values()) {
         v.printPath();
         System.out.println();
      }
   }
   public void resetState(){
	   for(Vertex v : vertices.values()){
		   v.state = State.UNVISITED;
	   }
   }
   
   public void dfs(String from, String to){
	   Vertex v = vertices.get(from);
	   resetState();
	   DFSUtil(v, to);
   }
   
// A function used by DFS
   boolean DFSUtil(Vertex v,String to){ 	
       // Mark the current node as visited and print it
	   v.state = State.VISITED;
	   System.out.print(v+" ");

       // Recur for all the vertices adjacent to this vertex
       Iterator<Vertex> i = v.getConnections().iterator();
       while (i.hasNext())
       {
           Vertex nextVertex  = i.next();
           if(nextVertex.name.equals(to)){
        	   return true;
           }
           if (nextVertex.state != State.VISITED)
              return DFSUtil(nextVertex, to);
       }
       return false;
   }
   
   public Stack<Vertex> buildOrder(Set<Vertex> vertices){
	    Stack<Vertex> stack = new Stack<Vertex>();
	    for(Vertex v : vertices){
	    	if(!doDfs(v, stack)){
	    		return null;
	    	}
	    }
	    return stack;
   }
   
   private boolean doDfs(Vertex v, Stack<Vertex> stack) {
	   if(v.state == State.VISITING){
		   return false;
	   }
	   if(v.state == State.UNVISITED){
		   v.state = State.VISITING;
		   for(Vertex next : v.getConnections()){
			   if(!doDfs(next, stack)){
				   return false;
			   }
		   }
		   v.state = State.VISITED;
		   stack.push(v);
	   }
	   return true;
   }

public static void main(String args[])
   {
       Graph g = new Graph();

       g.addEdge("25", "13", 0);
       g.addEdge("25", "10", 0);
       g.addEdge("13", "16", 0);
       g.addEdge("13", "22", 0);

       System.out.println("Following is Depth First Traversal "+
                          "(starting from vertex 25)");

       g.dfs("25","10");
   }

//private static final Graph.Edge[] GRAPH = {
//    new Graph.Edge("a", "b", 7),
//    new Graph.Edge("a", "c", 9),
//    new Graph.Edge("a", "f", 14),
//    new Graph.Edge("b", "c", 10),
//    new Graph.Edge("b", "d", 15),
//    new Graph.Edge("c", "d", 11),
//    new Graph.Edge("c", "f", 2),
//    new Graph.Edge("d", "e", 6),
//    new Graph.Edge("e", "f", 9),
// };
// private static final String START = "a";
// private static final String END = "e";
//
// public static void main(String[] args) {
//    Graph g = new Graph(GRAPH);
//    g.dijkstra(START);
//    g.printPath(END);
//    //g.printAllPaths();
// }
}

