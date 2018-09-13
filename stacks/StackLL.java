package stacks;

import java.util.ArrayList;
import java.util.EmptyStackException;

public class StackLL<AnyType> implements StackInt<AnyType> {
	public class Node<AnyType> {
		private AnyType data;
		private Node<AnyType> next;
		
		Node(AnyType item) {
			data=item;
			next=null;
			
		}
		
		Node(AnyType item, Node<AnyType> next) {
			data=item;
			this.next = next;
		}
		
	}
	private Node<AnyType> topmost;
	private int size;
	
	StackLL() {
		topmost=null;
		size=0;
	}
	
	public AnyType push (AnyType item) {
		topmost = new Node<AnyType>(item, topmost);
		size++;
		return item;
	}
	
	public AnyType top() {
		if (empty()) {
			throw new EmptyStackException();
		}
		return topmost.data;
	}
	
	public AnyType pop() {
		AnyType temp = top();
		topmost=topmost.next;
		size++;
		return temp;
	}
	
	public boolean empty() {
		return topmost==null;
	}
	
	
	
	public static void main(String[] args) {
//		StackInt<Integer> s = new StackLL<Integer>();
//		
//		s.push(1);
//		s.push(2);
//		s.push(3);
//		
//		System.out.println(s.pop());
//		System.out.println(s.pop());
//		System.out.println(s.pop());

		
		
		
		
	}
}
