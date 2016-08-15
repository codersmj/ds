package tree;

import java.util.LinkedList;
import java.util.Stack;

public class SerializeDeserializeBinaryTree {
	// Encodes a tree to a single string.// Java Solution 1 - Level Order Traveral


	public String serialize(BinaryTreeNode root) {
	    if(root==null){
	        return "";
	    }
	 
	    StringBuilder sb = new StringBuilder();
	 
	    LinkedList<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
	 
	    queue.add(root);
	    while(!queue.isEmpty()){
	    	BinaryTreeNode t = queue.poll();
	        if(t!=null){
	            sb.append(String.valueOf(t.data) + ",");
	            queue.add(t.left);
	            queue.add(t.right);
	        }else{
	            sb.append("#,");
	        }
	    }
	 
	    sb.deleteCharAt(sb.length()-1);
	    System.out.println(sb.toString());
	    return sb.toString();
	}
	 
	// Decodes your encoded data to tree.
	public BinaryTreeNode deserialize(String data) {
	    if(data==null || data.length()==0)
	        return null;
	 
	    String[] arr = data.split(",");
	    BinaryTreeNode root = new BinaryTreeNode(Integer.parseInt(arr[0]));
	 
	 
	    LinkedList<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
	    queue.add(root);
	 
	    int i=1;
	    while(!queue.isEmpty()){
	        BinaryTreeNode t = queue.poll();
	 
	        if(t==null)
	            continue;
	 
	        if(!arr[i].equals("#")){
	            t.left = new BinaryTreeNode(Integer.parseInt(arr[i]));    
	            queue.offer(t.left);
	 
	        }else{
	            t.left = null;
	            queue.offer(null);
	        }
	        i++;
	 
	        if(!arr[i].equals("#")){
	            t.right = new BinaryTreeNode(Integer.parseInt(arr[i]));    
	            queue.offer(t.right);
	 
	        }else{
	            t.right = null;
	            queue.offer(null);
	        }
	        i++;
	    }
	 
	    return root;
	}
	
	
	
	// Encodes a tree to a single string.
	public String serializePreOrder(BinaryTreeNode root) {
	    if(root==null)
	        return null;
	 
	    Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
	    stack.push(root);
	    StringBuilder sb = new StringBuilder();
	 
	    while(!stack.isEmpty()){
	        BinaryTreeNode h = stack.pop();   
	        if(h!=null){
	            sb.append(h.data+",");
	            stack.push(h.right);
	            stack.push(h.left);  
	        }else{
	            sb.append("#,");
	        }
	    }
	 
	    return sb.toString().substring(0, sb.length()-1);
	}
	 
	// Decodes your encoded data to tree.
	public BinaryTreeNode deserializePreOrder(String data) {
	    if(data == null)
	        return null;
	 
	    int[] t = {0};
	    String[] arr = data.split(",");
	 
	    return helper(arr, t);
	}
	 
	public BinaryTreeNode helper(String[] arr, int[] t){
	    if(arr[t[0]].equals("#")){
	        return null;
	    }
	 
	    BinaryTreeNode root = new BinaryTreeNode(Integer.parseInt(arr[t[0]]));
	 
	    t[0]=t[0]+1;
	    root.left = helper(arr, t);
	    t[0]=t[0]+1;
	    root.right = helper(arr, t);
	 
	    return root;
	}
}
