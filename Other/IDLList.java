import java.util.ArrayList;
import java.util.NoSuchElementException;

//@author David Horowitz
//I pledge my honor that I have abided by the Stevens Honor System. -dhorowit

public class IDLList<E> {
	/**
	 * Private class implementation of a double linked list
	 * @param <E> - Generic type
	 */
	private class Node<E> {
		//data fields
		E data;
		Node<E> next;
		Node<E> prev;
		
		/**
		 * Constructor for a single node
		 * @param elem - data element of generic type
		 */
		Node(E elem){
			data = elem;
			next = null;
			prev = null;
		}
		
		/**
		 * Constructor for a node with references to previous and next nodes
		 * @param elem - data element of generic type
		 * @param prev - previous node in list
		 * @param next - next node in list
		 */
		Node(E elem, Node<E> prev, Node<E> next) {
			data = elem;
			this.prev = prev;
			this.next = next;
		}
	}
	
	//data fields
	private Node<E> head;
	private Node<E> tail;
	private int size;
	private ArrayList<Node<E>> indices;
	
	/**
	 * Constructs an empty IDLList
	 */
	public IDLList() {
		head = null;
		tail = null;
		size = 0;
		indices = new ArrayList<Node<E>>();
	}
	
	/**
	 * Adds elem to selected index, as long as the index is in bounds.
	 * @param index - index to add at
	 * @param elem - data of generic type
	 * @return true if successful
	 */
	public boolean add(int index, E elem) { //finish
		if(index > size || index < 0) {
			throw new IndexOutOfBoundsException();
		} else if(size==0 || index==0) {
			this.add(elem);
		} else if(index==size) {
			this.append(elem);
		} else {	
			indices.add(index, new Node<E>(elem, indices.get(index).prev, indices.get(index).next));
			size++;
		}
		return true;
	}
	
	/**
	 * Adds elem to the front of the list
	 * @param elem - data of generic type
	 * @return true if successful
	 */
	public boolean add(E elem) {
		if (size==0) { //list is empty
			head = new Node<E>(elem);
			tail=head;
			indices.add(head);
			size = 1;
		} else {
			head.prev = new Node<E>(elem, null, head);
			head=head.prev;
			size++;
			indices.add(0, head);
		}
		return true;
	}
	
	/**
	 * Adds elem to the end of the list
	 * @param elem - data of generic type
	 * @return true if successful
	 */
	public boolean append(E elem) {
		if (size==0) { //list is empty
			this.add(elem);
		} else {
			tail.next = new Node<E>(elem, tail, null);
			tail=tail.next;
			size++;
			indices.add(tail);
		}
		return true;
	}
	
	/**
	 * Retrieves the data stored at the given index
	 * @param index - index of the list
	 * @return data stored at given index
	 */
	public E get(int index) {
		if(index > size-1 || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		return indices.get(index).data;
	}
	
	/**
	 * Retrieves the data stored at the head of the list
	 * @return data stored at head
	 */
	public E getHead() {
		if(size==0) {
			throw new NoSuchElementException();
		}
		return head.data;
	}
	
	/**
	 * Retrieves the data stored at the tail of the list
	 * @return data stored at the last element in the list
	 */
	public E getLast() {
		if(size==0) {
			throw new NoSuchElementException();
		}
		return tail.data;
	}
	
	/**
	 * Returns the size of the list
	 * @return int size
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes the first element in the list and returns it 
	 * @return the removed data of generic type 
	 */
	public E remove() {
		if(size==0) {
			throw new NoSuchElementException();
		} else if(size == 1) { 
			E temp = head.data;
			head=null;
			tail=null;
			indices.clear();
			size = 0;
			return temp;
			
		} else {
			E temp = head.data;
			head=head.next;
			head.prev=null;
			indices.remove(0);
			size--;
			return temp;
		}
	}
	/**
	 * Removes the last element in the list and returns it 
	 * @return the removed data of generic type 
	 */
	public E removeLast() {
		if(size==0) {
			throw new NoSuchElementException();
		} else if(size == 1) { 
			E temp = tail.data;
			head=null;
			tail=null;
			indices.clear();
			size = 0;
			return temp;
			
		} else {
			E temp = tail.data;
			tail = tail.prev;
			tail.next=null;
			indices.remove(size-1);
			size--;
			return temp;
		}
	}
	
	/**
	 * Removes the element at the given index and returns it
	 * @param index - index of list
	 * @return the removed data of generic type 
	 */
	public E removeAt(int index) {
		if(index > size-1 || index < 0) {
			throw new IndexOutOfBoundsException();
		} else {
			Node<E> current = indices.get(index);
			E temp = indices.get(index).data;
			if(current.prev==null) {
				this.remove();
				current = null;
				return temp;
			} else if(current.next==null) {
				this.removeLast();
				current = null;
				return temp;
			} else {
				indices.get(index-1).next = indices.get(index-1).next.next;
				indices.get(index+1).prev = indices.get(index+1).prev.prev;
				indices.remove(index);
				current = null;
				size--;
				return temp;
			}
		}
	}
	
	/**
	 * Removes the first instance of the given element in the list
	 * @param elem - data of generic type 
	 * @return true if successful and false if the element was not found 
	 */
	public boolean remove(E elem) { //finish
		if(size==0) {
			throw new NoSuchElementException();
		} else {
			for(int i = 0; i<size; i++) {
				if(indices.get(i).data == elem) {
					this.removeAt(i);
					return true;
				}
			}
		return false;
		}
	}
	/**
	 * Returns a string representation of the list
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		if(size==0) {
			return s.append("()").toString();
		} else {
			s.append("(");
			for(int i = 0; i < size-1; i++) {
				s.append(indices.get(i).data);
				s.append(", ");
			}
			s.append(indices.get(size-1).data);
			s.append(")");
			return s.toString();
		}
	}

}
