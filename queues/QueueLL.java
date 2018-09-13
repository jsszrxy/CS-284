package queues;

import java.util.NoSuchElementException;

public class QueueLL<any extends Comparable<any>>{
	
	public class Node<any> {
		
		private any data;
		Node<any> next;
		
		Node(any item) {
			data=item;
			next=null;
		}
		Node(any item, Node<any> next) {
			data=item;
			this.next=next;
		}
	}
	Node<any> front;
	Node<any> rear;
	int size=0;
	
	QueueLL() {
		front=null;
		rear=null;
		size=0;
	}
	// Methods
	
	/**
	 * Returns entry at front of queue without removing it. If the
	   queue is empty, throws NoSuchElementException
	 * @return entry at the front of the queue
	 */
	public any element() {
		if(front == null) {
			throw new NoSuchElementException();
		}
		return front.data;
	}
	
	/**
	 * Insert an item at the rear of a queue
	 * @param item
	 * @return true if the operation was successful, false otherwise
	 */
	public boolean offer(any item) {
		if(front==null) {
			front = new Node<any>(item);
			rear=front;
		} else {
		rear.next = new Node<any>(item);
		rear=rear.next;
		}
		size++;
		return true;
	}
	/**
	 * Removes an entry from the front of the 
	 * queue if it is not empty. If the
	   queue is empty, throws NoSuchElementException
	 * @return Entry at the front of the queue
	 */
	public any remove() {
		any temp;
		if(front==null) {
			throw new NoSuchElementException();
		} else {
			temp = front.data;
			front=front.next;
			if(front==null) { //queue is empty after removal
				rear = null;
			}
			size--;
		}
		return temp;
	}
	
	public any remove2() {
		if(front == null) { //queue is empty
			throw new NoSuchElementException();
		}
		any temp = front.data;
		if(size ==1) { //one size queue
			front = null;
			rear = null;
			size=0;
			return temp;
		}
		Node<any> current = rear;
		while(current.next != front) {
			current = current.next;
		}
		current.next=null;
		front=current;
		return temp;
	}
	
	/**
	 * Returns the size of the queue
	 * @return size of the queue
	 */
	public int size() {
		return this.size;
	}
	
	public boolean empty() {
		return front==null;
	}
	
	public QueueLL<any> merge(QueueLL<any> q2) {
		QueueLL<any> result = new QueueLL<any>();
		
		while(!this.empty() && !q2.empty()) {
			
			if(this.element().compareTo(q2.element())<0) {
				result.offer(this.remove());
			} else {
				result.offer(q2.remove());;
			}
			
		}
		if(!(this.empty())) {
			while(!(this.empty())) {
				result.offer(this.remove());
			}
		}
		if(!(q2.empty())) {
			while(!(q2.empty())) {
				result.offer(q2.remove());
			}
		}
		
		
		return result;
		
	}
	
	
	public String toString() {
		String s = "(";
		if(front == null) {
			return s = s + ")";
		}
		Node<any> temp = front;
		while(temp.next != null) {
			s = s + temp.data + " <- ";
			temp=temp.next;
		}
		return s = s + temp.data + ")";
	}
	public static void main(String[] args) {
		QueueLL<Integer> s = new QueueLL<Integer>();
		System.out.println(s.size());

		s.offer(1);
		s.offer(4);
		s.offer(7);
//		System.out.println(s);
//		System.out.println(s.remove());
//		System.out.println(s);
//		System.out.println(s.element());
//		System.out.println(s.size());
//		System.out.println(s);
//		System.out.println(s.remove());
//		System.out.println(s);
//		System.out.println(s.remove());
//		System.out.println(s);

		QueueLL<Integer> z = new QueueLL<Integer>();

		z.offer(2);
		z.offer(5);
		z.offer(8);
		
		System.out.println(s.merge(z));
	}
}
