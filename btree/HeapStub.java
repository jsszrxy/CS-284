package heaps;
import java.util.AbstractQueue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Comparator;
import java.util.Queue;
/**
 * The Heap implements the Queue interface
 *  by building a heap in an ArrayList. The heap is structured
 *  so that the "smallest" item is at the top.
 *  @author Koffman and Wolfgang
 */
public class HeapStub<E> extends AbstractQueue<E> implements Queue<E> {
    // Data Fields
    /** The ArrayList to hold the data. */
    private ArrayList<E> theData;
    /** An optional reference to a Comparator object. */
    Comparator<E> comparator = null;
    // Methods
    // Constructor
    public HeapStub() {
        theData = new ArrayList<E>();
    }
    @Override
    public int size() {
        return theData.size();
    }
    @Override
    public Iterator<E> iterator() {
        return theData.iterator();
    }
    @Override
    public E peek() {
        return theData.get(0);
    }
    public String toString() {
        StringBuilder stb = new StringBuilder();
        stb.append("[ ");
        for (int i = 0; i < theData.size(); i++) {
            stb.append(theData.get(i));
            stb.append(" | ");
        }
        stb.delete(stb.length()-3,stb.length()-1);
        stb.append("]");
        return stb.toString();
    }
    /**
     * Compare two items using either a Comparator object�s compare method
     * or their natural ordering using method compareTo.
     * @pre If comparator is null, left and right implement Comparable<E>.
     * @param left One item
     * @param right The other item
     * @return Negative int if left less than right,
     *         0 if left equals right,
     *         positive int if left > right
     * @throws ClassCastException if items are not Comparable
     */
    @SuppressWarnings("unchecked")
    private int compare(E left, E right) {
        if (comparator != null) { // A Comparator is defined.
            return comparator.compare(left, right);
        } else { // Use left's compareTo method.
            return ((Comparable<E>) left).compareTo(right);
        }
    }
    public void swap(int a, int b) {
        E temp = theData.get(a);
        theData.set(a, theData.get(b));
        theData.set(b, temp);
    }
    /**
     * Insert an item into the priority queue.
     * @pre The ArrayList theData is in heap order.
     * @post The item is in the priority queue and
     *       theData is in heap order.
     * @param item The item to be inserted
     * @throws NullPointerException if the item to be inserted is null.
     */
    @Override
    public boolean offer(E item) {
        // Add the item to the heap.
        theData.add(item);
        // child is newly inserted item.
        int child = theData.size() - 1;
        int parent = (child - 1) / 2; // Find child�s parent.
        // Reheap
        while (parent >= 0 && compare(theData.get(parent),
                theData.get(child)) > 0) {
            swap(parent, child);
            child = parent;
            parent = (child - 1) / 2;
        }
        return true;
    }
    /**
     * Remove an item from the priority queue
     * @pre The ArrayList theData is in heap order.
     * @post Removed smallest item, theData is in heap order.
     * @return The item with the smallest priority value or null if empty.
     */
    @Override
    public E poll() {
        //TODO: implement a remove function for heaps
        if (isEmpty()) {
        		return null;
        }
        E result = theData.get(0);
        if (theData.size() == 1) {
        		theData.remove(0);
        		return result;
        }
        theData.set(0, theData.remove(theData.size() - 1));
        int parent = 0;
        while (true) {
        		int leftChild = 2 * parent + 1;
        		if (leftChild >= theData.size()) {
        			break;
        		}
        		int rightChild = leftChild + 1;
        		int minChild = leftChild;
        		if (rightChild < theData.size() && compare(theData.get(leftChild), theData.get(rightChild)) > 0) {
        			minChild = rightChild;
        		}
        		if(compare(theData.get(parent), theData.get(minChild)) > 0) {
        			swap(parent, minChild);
        			parent = minChild;
        		}
        		else {
        			break;
        		}
        }
        return result;
    }


    /**
     * Basic "Naive" implementation of the heap sort.
     * Involves insertion of a set of numbers and a series of polling from that set.
     * @param listOfNumbers - a set of numbers in the form of an ArrayList
     * @return - a sorted ArrayList of numbers.
     */
    public ArrayList<E> heapSort(ArrayList<E> listOfNumbers) {
        //TODO: Implement a sorting algorithm that uses heaps to sort a list of numbers
        if (listOfNumbers.isEmpty()) {
        		return null;
        }
        for (int i = 0; i < listOfNumbers.size(); i++) {
        		this.offer(listOfNumbers.get(i));
        }
        ArrayList<E> result = new ArrayList<E>();
        while (!theData.isEmpty()) {
        		result.add(this.poll());
        }
        return result; 
    }

    public static void main(String[] args) {
    }
}