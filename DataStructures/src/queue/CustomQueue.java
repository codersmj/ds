package queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CustomQueue<T> implements Iterable<T> {
    private Node<T> first;    // beginning of queue
    private Node<T> last;     // end of queue
    private int n;               // number of elements on queue

    // helper linked list class
    private static class Node<T> {
        private T item;
        private Node<T> next;
    }

    /**
     * Initializes an empty queue.
     */
    public CustomQueue() {
        first = null;
        last  = null;
        n = 0;
    }

    /**
     * Returns true if this queue is empty.
     *
     * @return <tt>true</tt> if this queue is empty; <tt>false</tt> otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the number of items in this queue.
     *
     * @return the number of items in this queue
     */
    public int size() {
        return n;
    }

    /**
     * Returns the item least recently added to this queue.
     *
     * @return the item least recently added to this queue
     * @throws NoSuchElementException if this queue is empty
     */
    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    /**
     * Adds the item to this queue.
     *
     * @param  item the item to add
     */
    public void enqueue(T item) {
        Node<T> oldlast = last;
        last = new Node<T>();
        last.item = item;
        last.next = null;
        if (isEmpty()){
        	first = last;
        }
        else{
        	oldlast.next = last;
        }
        n++;
    }

    /**
     * Removes and returns the item on this queue that was least recently added.
     *
     * @return the item on this queue that was least recently added
     * @throws NoSuchElementException if this queue is empty
     */
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        T item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    /**
     * Returns a string representation of this queue.
     *
     * @return the sequence of items in FIFO order, separated by spaces
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (T item : this)
            s.append(item + " ");
        return s.toString();
    } 

    /**
     * Returns an iterator that iterates over the items in this queue in FIFO order.
     *
     * @return an iterator that iterates over the items in this queue in FIFO order
     */
    public Iterator<T> iterator()  {
        return new ListIterator<T>(first);  
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator<T> implements Iterator<T> {
        private Node<T> current;

        public ListIterator(Node<T> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.item;
            current = current.next; 
            return item;
        }
    }


    /**
     * Unit tests the <tt>Queue</tt> data type.
     */
    public static void main(String[] args) {
    	CustomQueue<String> queue = new CustomQueue<String>();
//        while (!StdIn.isEmpty()) {
//            String item = StdIn.readString();
//            if (!item.equals("-"))
//                queue.enqueue(item);
//            else if (!queue.isEmpty())
//                StdOut.print(queue.dequeue() + " ");
//        }
//        StdOut.println("(" + queue.size() + " left on queue)");
    }
}
