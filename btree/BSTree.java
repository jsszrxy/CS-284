package btree;

public class BSTree<E extends Comparable<E>> extends BinTree<E>{
	private E deletedItem;
	
	BSTree() {
		super();
	}
	
	BSTree(E data) {
		super(data);
	}
	
	BSTree(E data, BSTree<E> left, BSTree<E> right) {
		super(data,left,right);
	}
	
	boolean isEmpty() {
		return root == null;
	}
	
	private boolean find(E key, Node<E> n) {
		if(n==null) {
			return false;
		} else {
			int comparison = n.data.compareTo(key);
			if(comparison == 0) {
				return true;
			}
			if(comparison<0) {
				return find(key,n.right);
			}

			return find(key,n.left);
		}
	}
	
	public boolean find(E key) {
		return find(key, root);
	}
	
	public Node<E> add(E key, Node<E> n) {
		if (n==null) {
			return new Node<E>(key);
		} else {
			int comparison = n.data.compareTo(key);
			if(comparison == 0) {
				throw new IllegalArgumentException();
			}
			else if(comparison<0) {
				n.right = add(key,n.right);
				return n;
			} else {
				n.left = add(key,n.left);
				return n;
			}
		}
	}
	public void add(E key) {
		add(key, root);
	}
	
	private E max(Node<E> n) {
		if (n.right == null) {
			return n.data;
		} else {
			return max(n.right);
		}
	}
	
	public E max() {
		if(root==null) {
			throw new IllegalStateException();
		}
		return max(root);
	}
	
	
	
	public E removeMax() {
		if(root==null) {
			throw new IllegalStateException();
		} else {
			if(root.right==null) {
				E temp = root.data;
				root=root.left;
			} else {
				Node<E> current = root;
				while(current.right.right != null) {
					current = current.right;
				}
				E temp = current.right.data;
				current.right = current.right.left;
				return temp;
			}
		}
		return null;
	}
	
	private E findAndRemoveMax(Node<E> n) {
		if(n.right.right == null) {
			E temp = n.right.data;
			n.right = n.right.left;
			return temp;
		} else {
			return findAndRemoveMax(n.right);
		}
	}
	
	private Node<E> remove(E key, Node<E> n) {
		if(n==null) {
			deletedItem = null;
			return null;
		} else {
			int comparison = n.data.compareTo(key);
			if (comparison<0) {
				n.right = remove(key, n.right);
				return n;
			} else if(comparison > 0) {
				n.left = remove(key, n.left);
				return n;
			} else {
				if(n.left==null) {
					deletedItem= n.data;
					return n.right;
				} else if(n.right == null) {
					deletedItem = n.data;
					return n.left;
				} else if(n.left.right ==null) {
					deletedItem=key;
					n.data = n.left.data;
					n.left = n.left.left;
					return n;
				} else {
					E temp = findAndRemoveMax(n.left);
					n.data = temp;
					deletedItem = key;
					return n;
					
				}
			}
		}
		
		
	}
	
	public E remove(E key) {
		root = remove(key, root);
		return deletedItem;
	}
	
	
	public static void main(String[] args) {
	BSTree<Integer> t1 = new BSTree<Integer>(32,new BSTree<Integer>(),new BSTree<Integer>(35));
	BSTree<Integer> t2 = new BSTree<Integer>(55,new BSTree<Integer>(47),new BSTree<Integer>(74,new BSTree<Integer>(62),new BSTree<Integer>()));
	BSTree<Integer> t = new BSTree<Integer>(44,t1,t2);
	
	t.add(77);
	System.out.println(t);
	t.remove(44);
	System.out.println(t);

	}
}
