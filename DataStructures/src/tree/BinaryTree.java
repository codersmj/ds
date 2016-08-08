package tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
	BinaryTreeNode root;
	List<BinaryTreeNode>trackingList;
	public BinaryTree() {
		root = null;
		trackingList = new ArrayList<BinaryTreeNode>();
	}

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
	public void inOrderTraversal(BinaryTreeNode root){
		if(root == null){
			return;
		}
		inOrderTraversal(root.left);
		System.out.print(" " + root.data+ ",");
		inOrderTraversal(root.right);
	}
	
	public void preOrderTraversal(BinaryTreeNode root){
		if(root == null){
			return;
		}
		System.out.print(" " + root.data+ ",");
		preOrderTraversal(root.left);
		preOrderTraversal(root.right);
	}
	
	public void postOrderTraversal(BinaryTreeNode root){
		if(root == null){
			return;
		}
		postOrderTraversal(root.left);
		postOrderTraversal(root.right);
		System.out.println(" " + root.data+ ",");
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
		printHelper(tree.root, " ");
		
		tree.preOrderTraversal(tree.root);
		tree.invert(tree.root);
		System.out.println("\n");
		tree.preOrderTraversal(tree.root);
		
		printHelper(tree.root, " ");


	}
	
	
}
