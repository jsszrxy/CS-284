package lists;

public class MyListLL<E> {
	
	private class Node<E> {
		//data fields
		private E data;
		private Node<E> next;
		
		Node(E data)
		{
			this.data= data;
			this.next= null;
		}
		Node(E data, Node<E> next) {
			this.data= data;
			this.next= next;
		}
	}
	//Data fields
	private Node<E> head;
	private int size;
	
	//Constructor
	MyListLL()	{
		head=null;
		size=0;
	}
	
	
	public boolean add(E item) {
		head = new Node<E>(item,head);
		size++;
		return true;
	}
	
	public boolean addLast(E item) {
		if(head==null) { //list is empty
			head = new Node<E>(item);
			
		} else {
			Node<E> current = head;
			while (current.next != null) {
				current = current.next;
			}
			current.next = new Node<E>(item);
		}
		size++;
		return true;
	}
	public E removeFirst() {
		if(head == null) {
			throw new IllegalArgumentException("List is empty");
		}
		E temp = head.data;
		head = head.next;
		size--;
		return temp;
		
	}
	public E removeLast() {
		if (head == null) {
			throw new IllegalArgumentException("List is empty");
		} 
		if (head.next == null) { //list has one element
			return this.removeFirst();
		}
		Node<E> current = head;
		while (current.next.next != null) {
			current = current.next;
		}
		E temp = current.next.data;
		current.next = null;
		size--;
		return temp;
		
		
	}
	
	public E remove(int index) {
		if (index > size -1 || index < 0) {
			throw new IllegalArgumentException("Index is out of bounds");
		}
		if (index == 0) {
			return this.removeFirst();
		}
		Node<E> current = head;
		for(int i = 0; i < index-1; i++) {
			current = current.next;
		}
		E temp = current.next.data;
		current.next = current.next.next;
		size--;
		return temp;
	}
	
	private MyListLL<Pair<E,E>>.Node<Pair<E,E>> zip(Node<E> current1, Node<E> current2){
		if(current1==null || current2==null) {
			return null;
		} else {
			return null;
		}
		
	}
	
	public MyListLL<Pair<E,E>> zip(MyListLL<E> l2){
		MyListLL <Pair<E,E>> r = new MyListLL<Pair<E,E>>();
		r.size = Math.min(size, l2.size);
		r.head = zip(this.head, l2.head);
		return r;
	}
	
	
	
	
	private Node<E> take(int n, Node<E> current){
		int ln=n;
		Node<E> lcurrent=current;
		Node<E> result= new Node<E>(null);
		Node<E> dummyHead = result;
		
		while(ln>0 && lcurrent!=null) {
			result.next = new Node<E>(lcurrent.data);
			ln--;
			lcurrent=lcurrent.next;
			result=result.next;
		}
		return dummyHead.next;
	}
	
	private Node<E> take2(int n, Node<E> current) {
		if (n==0 || current==null) {
			return null;
		} else {
			return new Node<E>(current.data, take2(n-1, current.next));
		}
	}
	 
	public MyListLL<E> take(int n){
		MyListLL<E> result = new MyListLL<E>();
		result.size = Math.min(size, n);
		result.head = take(n,head);
		return result;
	}
	
	
	
	private boolean isSingleton(Node<E> list) {
		return list!= null && list.next==null;
		
	}
	
	public boolean isSingleton() {
		return isSingleton(this.head);
	}
	
	public String toString() {
		String r="";
		Node<E> current=head;
		
		while (current != null) {
			r = r + "," + current.data;
			current = current.next;
		}
		return r;
	}
	
	public static void main(String[] args) {
		MyListLL<String> l = new MyListLL<String>();
		l.add("David");
		l.add("Rachel");
		l.add("xd");
		l.add("jeff");
		l.addLast("dog");
		System.out.println(l);
		//l.removeFirst();
		//System.out.println(l);
		//l.removeLast();
		//l.remove(3);
		System.out.println(l.take(2));
		System.out.println(l);


	}



}
