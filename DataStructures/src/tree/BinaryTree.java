package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class BinaryTree {
	BinaryTreeNode root;
	List<BinaryTreeNode>trackingList;
	
	public BinaryTree() {
		root = null;
		trackingList = new ArrayList<BinaryTreeNode>();
	}
	
	public int getHeight(BinaryTreeNode root){
		if(root == null){
			return 0;
		}
		return Math.max(getHeight(root.left), getHeight(root.right))+1;
	}
	
	public boolean checkBalanced(BinaryTreeNode root){
		if(root == null){
			return true;
		}
		
		int leftHeight = getHeight(root.left);
		int rightHeight = getHeight(root.right);
		if(Math.abs(rightHeight -leftHeight) >1){
			return false;
		}
		return checkBalanced(root.left) && checkBalanced(root.right);
	}
	
	//BUILDS A COMPLETE BINARY TREE
	public void insert(int x){
		if(root == null){
			root = new BinaryTreeNode(x);
			System.out.println(x+"Added as root");
			trackingList.add(root);
			return;
		}
		insert(x, trackingList.size());
	}
	
	private void insert( int x,int currentIndex) {
		int wouldBeparentIndex = (currentIndex-1)/2;
		BinaryTreeNode  newNode = new BinaryTreeNode(x);
		BinaryTreeNode parentNode = trackingList.get(wouldBeparentIndex);
		if(parentNode.left ==null){
			System.out.println(x+"Added as left of"+ parentNode.data);

			parentNode.left = newNode;
			trackingList.add(newNode);
		}else{
			System.out.println(x+"Added as right of"+ parentNode.data);

			parentNode.right = newNode;
			trackingList.add(newNode);
		}
	}
	
	
	//INVERT:
	public void invert(BinaryTreeNode root){
		if(root == null){
			return;
		}
		
		BinaryTreeNode temp = root.left;
		root.left = root.right;
		root.right = temp;
		
		invert(root.left);
		invert(root.right);
	}
	// PRINT1
	private static void printHelper(BinaryTreeNode root, String indent) {
	    if (root == null) {
	      System.out.println(indent + "null");
	      return;
	    }

	    // Pick a pretty indent.
	    String newIndent;
	    if (indent.equals("")) {
	      newIndent = ".. ";
	    }
	    else {
	      newIndent = "..." + indent;
	    }

	    printHelper(root.left, newIndent);
	    System.out.println(indent + root.data);
	    printHelper(root.right, newIndent);
	
	}
	
	//INORDER
	public void inOrderTraversal(BinaryTreeNode root){
		if(root == null){
			return;
		}
		inOrderTraversal(root.left);
		System.out.print(" " + root.data+ ",");
		inOrderTraversal(root.right);
	}
	//PREORDER
	public void preOrderTraversal(BinaryTreeNode root){
		if(root == null){
			return;
		}
		System.out.print(" " + root.data+ ",");
		preOrderTraversal(root.left);
		preOrderTraversal(root.right);
	}
	//POSTORDER
	public void postOrderTraversal(BinaryTreeNode root){
		if(root == null){
			return;
		}
		postOrderTraversal(root.left);
		postOrderTraversal(root.right);
		System.out.println(" " + root.data+ ",");
	}
	//LEVEL ORDER TRAVERSAL :: BFS Binary Tree
	public void levelOrderTraversal(BinaryTreeNode root){
		if(root == null){
			return;
		}
		System.out.println(" " +root.data +",");
		Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
		queue.add(root);
		while(!queue.isEmpty()){
			BinaryTreeNode currentNode = queue.poll();
			if(currentNode.left!=null){
				System.out.println(" "+currentNode.left.data);
				queue.add(currentNode.left);
			}
			if(currentNode.right != null){
				System.out.println(" "+currentNode.right.data);
				queue.add(currentNode.right);
			}
		}
	}
	
	//LEVEL ORDER TRAVERSAL :: BFS Binary Tree
		public void zigzagOrderTraversal(BinaryTreeNode root){
			// prints in Spiral/Zigzag level order  
		        if(root==null) return;   
		        Stack<BinaryTreeNode> stack=new Stack<BinaryTreeNode>();  
		        stack.push(root);  
		          
		        boolean directionflag=false;  
		        while(!stack.isEmpty())  
		        {  
		            Stack<BinaryTreeNode> tempStack=new Stack<BinaryTreeNode>();  
		          
		            while(!stack.isEmpty())  
		            {  
		            	BinaryTreeNode tempNode=stack.pop();  
		             System.out.printf("%d ",tempNode.data);  
		                if(!directionflag)   
		                {  
		                    if(tempNode.left!=null)   
		                     tempStack.push(tempNode.left);  
		                    if(tempNode.right!=null)   
		                     tempStack.push(tempNode.right);  
		                }else  
		                {  
		                    if(tempNode.right!=null)   
		                     tempStack.push(tempNode.right);  
		                    if(tempNode.left!=null)   
		                     tempStack.push(tempNode.left);  
		                }  
		            }  
		            // for changing direction  
		            directionflag=!directionflag;   
		        
		            stack=tempStack;   
		        }  
		       
		}
		
	
	// Prints all paths to leaf  
	 public static void printAllPathsToLeaf(BinaryTreeNode node, int[] path, int len) {  
	     if ( node == null )  
	         return;  
	  
	     // storing data in array  
	     path[len] = node.data;  
	     len++;  
	  
	     if(node.left == null && node.right == null) {  
	         // leaf node is reached  
	        System.out.println(path);
	         return;  
	     }  
	  
	     printAllPathsToLeaf(node.left, path, len);  
	     printAllPathsToLeaf(node.right, path, len);  
	 }  
	 //LCA
	 public static BinaryTreeNode lowestCommonAncestor(BinaryTreeNode root, BinaryTreeNode a, BinaryTreeNode b) {  
         if(root == null)  
         return null;  
         if(root.data == a.data || root.data == b.data )  
         return root;  
           
         BinaryTreeNode left=lowestCommonAncestor(root.left,a,b);  
         BinaryTreeNode right=lowestCommonAncestor(root.right,a,b);  
           
         // If we get left and right not null , it is lca for a and b  
         if(left!=null && right!=null)  
             return root;  
         if(left== null)  
         return right;  
         else  
         return left;  
               
     }  

	// A utility function to find min and max distances with respect
    // to root.
    void findMinMax(BinaryTreeNode node, Values min, Values max, int hd) 
    {
        // Base case
        if (node == null) 
            return;
  
        // Update min and max
        if (hd < min.min) 
            min.min = hd;
        else if (hd > max.max) 
            max.max = hd;
  
        // Recur for left and right subtrees
        findMinMax(node.left, min, max, hd - 1);
        findMinMax(node.right, min, max, hd + 1);
    }
  
    // A utility function to print all nodes on a given line_no.
    // hd is horizontal distance of current node with respect to root.
    void printVerticalLine(BinaryTreeNode node, int line_no, int hd) 
    {
        // Base case
        if (node == null) 
            return;
  
        // If this node is on the given line number
        if (hd == line_no) 
            System.out.print(node.data + " ");        
  
        // Recur for left and right subtrees
        printVerticalLine(node.left, line_no, hd - 1);
        printVerticalLine(node.right, line_no, hd + 1);
    }
  
    // The main function that prints a given binary tree in
    // vertical order
    void verticalOrder(BinaryTreeNode node) 
    { 	Values val = new Values();
        // Find min and max distances with resepect to root
        findMinMax(node, val, val, 0);
  
        // Iterate through all possible vertical lines starting
        // from the leftmost line and print nodes line by line
        for (int line_no = val.min; line_no <= val.max; line_no++) 
        {
            printVerticalLine(node, line_no, 0);
            System.out.println("");
        }
    }
    class Values 
    {
        int max, min;
    }
    
 // prints vertical sum in binary tree  
    public static void printVertivalSumOfBinaryTree(BinaryTreeNode startNode,TreeMap<Integer,Integer> treeNodeMap,int level) {  
     if(startNode==null)  
     {  
      return;  
     }  
       
     // Decrease level by 1 when iterating left child  
     printVertivalSumOfBinaryTree(startNode.left,treeNodeMap,level-1);  
       
     if(treeNodeMap.get(level)!=null)  
     {  
            // Adding current node data to previous stored value to get the sum  
          Integer sum=treeNodeMap.get(level)+startNode.data;  
            treeNodeMap.put(level, sum);  
     }  
     else  
     {  
       
      treeNodeMap.put(level, startNode.data);  
     }  
    // Increase level by 1 when iterating left child  
     printVertivalSumOfBinaryTree(startNode.right,treeNodeMap,level+1);  
       
    }  

    public static int getMaximumNode(BinaryTreeNode startNode) {  
    	
    	  
    	 Queue<BinaryTreeNode> queue=new LinkedList<BinaryTreeNode>();  
    	 queue.add(startNode);  
    	 int max=Integer.MIN_VALUE;  
    	 while(!queue.isEmpty())  
    	 {  
    	  BinaryTreeNode tempNode=queue.poll();  
    	  if(max < tempNode.data)  
    	   max=tempNode.data;  
    	  if(tempNode.left!=null)  
    	   queue.add(tempNode.left);  
    	  if(tempNode.right!=null)  
    	   queue.add(tempNode.right);  
    	 }  
    	 return max;  
    	} 

    
    public static void main(String[] args) {
		BinaryTree tree = new BinaryTree();
		tree.insert(30);
		tree.insert(2);

		tree.insert(88);

		tree.insert(12);

		tree.insert(5);

		tree.insert(23);

		tree.insert(32);
		
		tree.insert(1);
		
		
		System.out.println("balance : "+tree.checkBalanced(tree.root));
		printHelper(tree.root, " ");
		
		tree.preOrderTraversal(tree.root);
		//tree.invert(tree.root);
		System.out.println("\n");
		tree.preOrderTraversal(tree.root);
		
		System.out.println("\n");
		tree.levelOrderTraversal(tree.root);
		printHelper(tree.root, " ");
		SerializeDeserializeBinaryTree printer = new SerializeDeserializeBinaryTree();
		System.out.println("  ---> "+printer.serialize(tree.root));
		BinaryTreeNode root2 = printer.deserialize("30,2,88,12,5,23,32,1,#,#,#,#,#,#,#,#,#");
		BinaryTree tree2 = new BinaryTree();
		tree.root = root2;
		
		printHelper(root2, " ");
	}
 // prints in reverse level order  
    public static void reverseLevelOrderTraversal(BinaryTreeNode startNode) {  
     Queue<BinaryTreeNode> queue=new LinkedList<BinaryTreeNode>();  
     Stack<BinaryTreeNode> stack=new Stack<BinaryTreeNode>();  
     queue.add(startNode);  
     while(!queue.isEmpty())  
     {  
    	 BinaryTreeNode tempNode=queue.poll();  
      if(tempNode.right!=null)  
       queue.add(tempNode.right);  
      if(tempNode.left!=null)  
       queue.add(tempNode.left);  
        
      stack.push(tempNode);  
     }  
       
     while(!stack.empty())  
      System.out.print(stack.pop().data+" " );  
    }  

	
}
