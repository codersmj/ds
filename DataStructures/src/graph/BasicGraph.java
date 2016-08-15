package graph;

import graph.GraphNode.State;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class BasicGraph {
	Map<String,GraphNode> nodes;
	
	public BasicGraph() {
		nodes = new HashMap<String, GraphNode>();
	}
	
	public void addNode(String data){
		GraphNode node = new GraphNode(data);
		nodes.put(data, node);
	}
	
	public void addEdge(String fromData, String toData){
		GraphNode fromNode = nodes.get(fromData);
		if(fromNode == null){
			fromNode = new GraphNode(fromData);
			nodes.put(fromData, fromNode);
		}
		GraphNode toNode = nodes.get(toData);
		if(toNode == null){
			toNode = new GraphNode(toData);
			nodes.put(toData, toNode);
		}
		fromNode.addPathTo(toData);
	}
	
	//BFS
	public boolean findPath(String from, String to){
		GraphNode fromNode = nodes.get(from);
		if(fromNode == null){
			return false;
		}
		for(GraphNode n : nodes.values()){
			n.state = State.UNVISITED;
		}
		
		fromNode.state = State.VISITING;
		Queue<GraphNode>q = new LinkedList<GraphNode>();
		q.offer(fromNode);
		while(!q.isEmpty()){
			GraphNode currentNode = q.poll();
			for(String connectedData : currentNode.adjacentNodes){
				GraphNode n = nodes.get(connectedData);
				if(n == null){
					return false;
				}
				if(n.state != State.VISITED && connectedData.equals(to)){
					return true;
				}
				
				n.state = State.VISITING;
				q.offer(n);
			}
			currentNode.state = State.VISITED;
		}
		return false;
	}
	public boolean doDFS(String from, String to){
		for(GraphNode n : nodes.values()){
			n.state = State.UNVISITED;
		}
		return findPathDFS(from, to);

	}
	
	public boolean findPathDFS(String from, String to){

		GraphNode fromNode = nodes.get(from);
		if(fromNode == null){
			return false;
		}
		fromNode.state = State.VISITING;
		for(String n : fromNode.adjacentNodes){
				GraphNode node = nodes.get(n);
				if(node == null){
					return false;
				}
				if(node.state != State.VISITED && n.equals(to)){
					return true;
				}
				node.state = State.VISITING;
				doDFS(n, to)	;
		}
		fromNode.state = State.VISITED;
		return false;
	
	}
	
	public static void main(String[] args) {
		BasicGraph graph = new BasicGraph();
		graph.addEdge("A", "B");
//		graph.addEdge("A", "D");

		graph.addEdge("B", "C");

		graph.addEdge("C", "K");

		graph.addEdge("D", "E");

	System.out.println(graph.doDFS("A", "K"));

	}
}
