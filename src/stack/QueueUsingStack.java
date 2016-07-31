package stack;
import java.util.Stack;


public class QueueUsingStack<T> {

	Stack<T> oldStack;
	Stack<T> newStack;
	int currSize;
	public QueueUsingStack(){
		oldStack = new Stack<T>();
		newStack = new Stack<T>();
		currSize = 0;
	}
	
	public T poll(){
		if(currSize == 0){
			return null;
		}
		rearrangeStack();
		return oldStack.pop();
	}
	
	public void enqueue(T ele){
		newStack.push(ele);
		currSize++;
	}
	
	public void rearrangeStack(){
		if(oldStack.isEmpty()){
			while(!newStack.isEmpty()){
				oldStack.push(newStack.pop());
			}
		}
	}
}
