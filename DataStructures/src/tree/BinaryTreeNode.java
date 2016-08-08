package tree;

public class BinaryTreeNode {
	Integer data;
	BinaryTreeNode left;
	 BinaryTreeNode right;
	 int numOfNodesInSubtree;
	public BinaryTreeNode(Integer num) {
		this.data = num;
		numOfNodesInSubtree = 0;
	}

	public Integer getData() {
		return data;
	}

	public void setData(Integer data) {
		this.data = data;
	}

	public BinaryTreeNode getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}

	public BinaryTreeNode getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}
	
	@Override
	public String toString() {
		return data != null ? data.toString() : "#"; 
	}
	
	
}
