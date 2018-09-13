package stacks;

public interface StackInt<E> {
	E push(E item);
	
	E top();
	
	E pop();
	
	boolean empty();
}
