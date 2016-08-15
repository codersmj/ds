package graph;

import java.util.HashSet;
import java.util.Set;

public class GraphNode {
	String data;
	Set<String> adjacentNodes;
	State state;
	public GraphNode(String data) {
		this.data =  data;
		adjacentNodes = new HashSet<String>();
	}
	
	public void addPathTo(String toNode){
		adjacentNodes.add(toNode);
	}
	
	public boolean isConnectedTo(String node){
		return adjacentNodes.contains(node);
	}
	
	public Set<String> getAllAdjacentNodes(){
		return adjacentNodes;
	}
	
	public static enum State{
		UNVISITED,
		VISITING,
		VISITED;
	}
}
