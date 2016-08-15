package heap;

import java.util.Arrays;

public class BinaryMinHeap {
	private int[] heapArray;
	private int currentHeapSize;
	int maxSize;
	
	public BinaryMinHeap(int size) {
		maxSize  = size;
		this.heapArray = new int[size];
		currentHeapSize = 0;
		Arrays.fill(heapArray, -1);
	}
	public boolean isEmpty() {
	    return currentHeapSize == 0;
	  }
	
	public boolean insertElement(int ele){
		if(currentHeapSize == maxSize){
			return false;
		}
		heapArray[currentHeapSize]= ele;
		trickleUp(currentHeapSize++);
		return true;
	}
	
	private void trickleUp(int index){
		int parent = (index-1)/2;
		while (parent >=0 && heapArray[parent] > heapArray[index]){
			int bottomEle = heapArray[index];
			heapArray[index] = heapArray[parent];
			heapArray[parent] = bottomEle;
			index = parent ;
			parent = (index-1)/2;
		}
	}

	public int deleteElement(){
		int root = heapArray[0];
		heapArray[0] = heapArray[--currentHeapSize];
		heapArray[currentHeapSize] = -1;
		trickleDown(0);
		return root;
	}
	
	private void trickleDown(int index){
		int smallerChild ;
		int top = heapArray[index];
		while(index <= currentHeapSize/2){
			int leftChild = 2*index+1;
			int rightChild = leftChild +1;
			
			if(rightChild <= currentHeapSize && heapArray[leftChild] < heapArray[rightChild]){
				smallerChild = leftChild;
			}else{
				smallerChild = rightChild;
			}
			if(top < heapArray[smallerChild]){
				break;
			}
			heapArray[index] = heapArray[smallerChild];
			index = smallerChild;
		}
		heapArray[index] = top;
	}
	
	public int getMin(){
		return heapArray[0];
	}
	

	public static void main(String[] args)  {
	    int value, value2;
	    BinaryMinHeap h = new BinaryMinHeap(31); // make a Heap; max size 31
	    boolean success;

	    h.insertElement(70); // inserElement 10 items
	    h.insertElement(40);
	    h.insertElement(50);
	    h.insertElement(20);
	    h.insertElement(60);
	    h.insertElement(100);
	    h.insertElement(80);
	    h.insertElement(30);
	    h.insertElement(10);
	    h.insertElement(90);
	    System.out.println("heap : " +Arrays.toString( h.heapArray));
	    System.out.println(" Minimum :"+ h.getMin());
	    value = 140;
	    success = h.insertElement(value);
	    if (!success)
	      System.out.println("Can't inserElement; heap full");
	    if (!h.isEmpty()){
	      h.deleteElement();
	    System.out.println("heap : " +Arrays.toString( h.heapArray));
	    System.out.println(" Minimum :"+ h.getMin());
	    
	    }
	    else
	      System.out.println("Can't remove; heap empty");
	    
	  }
}
