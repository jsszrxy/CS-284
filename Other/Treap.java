import java.util.Random;
import java.util.Stack;

//David Horowitz
//I pledge my honor that I have abided by the Stevens Honor System.

/**
 *Class that implements Treaps and their corresponding operations
 * @param <E> - Any Comparable type
 */
public class Treap<E extends Comparable> {
	/**
	 *Node class that is used to construct Treaps.
	 * @param <E> - Any comparable type 
	 */
	private class Node<E> {
		//Data fields
		public E data;
		public int priority;
		public Node<E> left;
		public Node<E> right;
		
		/**
		 * Constructs a Node with a data element and a priority with null left and right children.
		 * @param data - type E to be stored in node
		 * @param priority - int to be stored as the node's priority value
		 */
		public Node(E data, int priority) {
			if (data == null) {
				throw new IllegalArgumentException("Data cannot be null");
			}
			this.data = data;
			this.priority = priority;
			left=null;
			right=null;
		}
		/**
		 * Rotates the node to the right in order to maintain tree structure
		 * @return Node<E> that has been rotated
		 */
		Node<E> rotateRight() {
			if (left == null) {
				return this; 
			}
			else {
				Node<E> newRoot = left;
				left = left.right;
				newRoot.right = this;
				return newRoot;
			}
		}
		/**
		 * Rotates the node to the left in order to maintain tree structure
		 * @return Node<E> that has been rotated
		 */
		Node<E> rotateLeft() {
			if (right == null) {
				return this; 
			}
			else {
				Node<E> newRoot = right;
				right = right.left;
				newRoot.left = this;
				return newRoot;
			}
		}
		/**
		 * Returns a readable string representation of the Node
		 */
		public String toString() {
			return "(key=" + data + ", priority=" + priority + ")";
		}
	}
	//Data fields 
	private Random priorityGenerator;
	private Node<E> root;
	
	/**
	 * Constructs an empty treap
	 */
	public Treap() {
		priorityGenerator = new Random();
		root=null;
	}
	
	/**
	 * Constructs an empty treap with a seeded random priority
	 * @param seed - a long that is used to seed the random generation of priorities 
	 */
	public Treap(long seed) {
		priorityGenerator = new Random(seed);
		root=null;
	}
	
	/**
	 * Adds a key to the treap with a random priority. Returns true if successful and false otherwise
	 * @param key - type E to be stored in the treap
	 * @return true if added successfully, false if not
	 */
	public boolean add(E key) {
		int priority = priorityGenerator.nextInt();
		return add(key, priority);
	}
	
	/**
	 * Adds a key to the treap with a given priority. Returns true if successful and false otherwise
	 * @param key - type E to be stored in the treap
	 * @param priority - int to be stored as the added key's priority value
	 * @return true if added successfully, false if not
	 */
	public boolean add(E key, int priority) {
        Stack<Node<E>> stack = new Stack<Node<E>>();
        Node<E> temp = new Node<E>(key, priority);
        if (root == null) {
            root = temp;
            return true;
        }
        Node<E> parent = null;
        Node<E> n = root;
        while (n != null) {
            parent = n;
            int comp = key.compareTo(n.data);            
            if (comp > 0) {
            	n = n.right;
                stack.push(parent);
            }
            else if (comp < 0) {
            	n = n.left;
                stack.push(parent);
            }
            else {
            	return false;
            }
        }
        int comp2 = key.compareTo(parent.data);
        if (comp2 > 0) {
        	parent.right  = temp;
        }
        else {
        	parent.left = temp;
        }
        reheap(stack, temp);
        return true;
    }
	/**
	 * Used to reformat a newly added to treap to make sure it follows the rules of a heap
	 * @param stack - A stack of nodes that are the path taken to add the new node to the treap
	 * @param current - the newly added key to the treap
	 */
	private void reheap(Stack<Node<E>> stack, Node<E> current) {
        if(stack.isEmpty()) {
        	root = current;
        } else if(current.priority < stack.peek().priority) {
			return;
		} else {
			if (stack.peek().right == current) {
				Node<E> removed = stack.pop();
				removed.rotateLeft();
				if (!stack.isEmpty()) {
					if (stack.peek().right == removed) {
						stack.peek().right = current;
					}
					else {
						stack.peek().left = current;
					}
				}
			}
			else {
				Node<E> removed = stack.pop();
				removed.rotateRight();
				if (!stack.isEmpty()) {
					if (stack.peek().right == removed) {
						stack.peek().right = current;
					}
					else {
						stack.peek().left = current;
					}
				}
			}
			reheap(stack, current);
		}
	}
	
	/**
	 * Deletes the given key from the treap. If the value cannot be found, returns false, and returns true if successful
	 * @param key - type E to be deleted from the treap
	 * @return true if deleted successfully, false if not
	 */
    public boolean delete(E key) {
		if (!find(key)) {
			return false;
		}
    	root = delete(root, key);
    	return true;
    }
    
    /**
     * helper function that recursively traverses the tree and deletes the value.
     * @param n - root of given node
     * @param key - type E to be deleted from the treap
     * @return the updated treap
     */
    private Node<E> delete(Node<E> n, E key) {
    	if (n != null) {
			int comp = key.compareTo(n.data);
            if (comp > 0) {
                n.right = delete(n.right, key);
            } else if (comp < 0) {
                n.left = delete(n.left, key);            
            } else {
                if (n.right == null) {
                    return n.left;
                } else if (n.left == null) {
                    return n.right;
                } else {
                    Node<E> temp = n.right;
                    while(temp.left != null) {
                    	temp = temp.left;
                    }
                	n.data = temp.data;
                    n.right = delete(n.right, n.data);
                }
            }
        }
        return n;
    }
    /**
     * Searches the treap for the given key. If it is found, returns true and false otherwise
     * @param key - Type E to be searched for in the treap
     * @return true if key is found, false otherwise
     */
	public boolean find(E key) {
		return find(root, key);
	}
	/**
	 * Helper function that searches the treap for the given key. If it is found, returns true and false otherwise
	 * @param root - root of the treap that will be searched on 
	 * @param key - Type E to be searched for in the treap
	 * @return true if the key is found, false otherwise
	 */
    private boolean find(Node<E> root, E key) {
		if(root==null) {
			return false;
		} else {
			int comp = root.data.compareTo(key);
			if(comp == 0) {
				return true;
			}
			if(comp < 0) {
				return find(root.right, key);
			}

			return find(root.left, key);
		}
    }
	
    /**
     * Helper function that returns a string representation of the treap
     * @param n - Root of the treap to be turned into a string
     * @param depth - level to start at in the treap
     * @return a readable string representation of a treap
     */
	private String toString(Node<E> n, int depth) {
		StringBuilder r = new StringBuilder();
		// add indentation
		for (int i=0;i<depth;i++) {
			r.append("  ");
		}
		if (n==null) {
			r.append("null");
		} else {
			r.append(n.toString());
			r.append("\n");
			r.append(toString(n.left, depth+1));
			r.append("\n");
			r.append(toString(n.right, depth+1));
		}
		return r.toString();
		
	}
	/**
	 * Returns a readable string representation of the treap
	 */
	public String toString() {
		return toString(root,0);
	}
	
}
