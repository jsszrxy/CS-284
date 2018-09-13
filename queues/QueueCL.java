package queues;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class QueueCL<any> {

	//data fields
	private any[] data;
	private int front;
	private int rear;
	private int size;
	private int capacity;
	private static final int INITIAL_CAPACITY=10;
	
	QueueCL() {
		data = (any[]) new Object[INITIAL_CAPACITY];
		capacity = INITIAL_CAPACITY;
		front = 0;
		rear= capacity-1;
		size = 0;
	}
	
	public any remove() {
		if(size==0) {
			throw new NoSuchElementException();
		} else {
			any temp = data[front];
			front = (front+1)%capacity;
			size--;
			return temp;
		}
	}
	
	public any element() {
		if(size==0) {
			throw new NoSuchElementException();
		} else {
			return data[front];
		}
	}
	
	private void resize() {
		any[] newData = (any[]) new Object[capacity*2];
		for(int i =0; i<size; i++) {
			newData[i] = data[(i+front)%capacity];
		}
		front=0;
		rear = size-1;
		data=newData;
		capacity=capacity*2;
	}
	
	
	public boolean offer(any item) {
		if(size==capacity) {
			resize();
		}
		rear=(rear+1)%capacity;
		data[rear] = item;
		size++;
		return true;
	}
	
	public int size() {
		return size;
	}
	
	public String toString() {
		return Arrays.toString(data);
	}
	
	public static void main(String[] args) {
		QueueCL<Integer> q = new QueueCL<Integer>();
		
		for(int i =0; i<10; i++) {
			q.offer(i);
		}
		System.out.println(q);
		System.out.println(q.front);
		System.out.println(q.rear);
		q.remove();
		System.out.println(q);
		System.out.println(q.front);
		System.out.println(q.rear);
		q.offer(10);
		System.out.println(q);
		System.out.println(q.front);
		System.out.println(q.rear);
	}
	
	
}
