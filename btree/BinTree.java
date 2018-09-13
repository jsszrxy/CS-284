package btree;

public class BinTree<E> {
	protected class Node<F> {
		protected F data;
		protected Node<F> left;
		protected Node<F> right;
		public Node(F data, Node<F> left, Node<F> right) {
			super();
			this.data = data;
			this.left = left;
			this.right = right;
		}
		public Node(F data) {
			super();
			this.data = data;
		}
		
	}
	// Data fields
	protected Node<E> root;
	// Constructors
	BinTree() {
		root=null;
	}
	BinTree(E data) {
		root = new Node<E>(data);
	}
	BinTree(E data, BinTree<E>leftTree, BinTree<E>rightTree) {
		root = new Node<E>(data,leftTree.root,rightTree.root);
	}
	
//	height(Empty) = 0
//	height(Node(i,lt,rt)) = 1 + max(height(lt),height(rt))
	
	private int height(Node<E> n) {
		if (n==null) {
			return 0;
		} else {
			return 1 + Math.max(height(n.left), height(n.right));
		}
	}
	public int height() {
		return height(root);
	}
	
	private int noOfNodes(Node<E> n) {
		if (n==null) {
			return 0;
		} else {
			return 1 + noOfNodes(n.left)+noOfNodes(n.right);
		}
	}
	public int noOfNodes() {
		return noOfNodes(root);
	}
	
	public boolean isLeaf() {
		return  root!=null && root.left==null && root.right==null;
	}

	private boolean isFull(Node<E> n) {
		if (n==null) {
			return true;
		} else {
			return isLeaf() || (n.left!=null && n.right!=null && isFull(n.left) && isFull(n.right)) ;
		}
	}
	
	public boolean isFull() {
		return isFull(root);
	
	}
	
	private boolean isBalanced(Node<E> n) {
		if(n==null) {
			return true;
		} else {
			return Math.abs(height(n.left) - height(n.right)) <= 1 && isBalanced(n.left) && isBalanced(n.right);
		}
	}
	
	public boolean isBalanced() {
		return isBalanced(root);
	}
	
	private boolean isPerfect(Node<E> n) {
		return noOfNodes()==Math.pow(2, height())-1;
	}
	
	public boolean isPerfect() {
		return isPerfect(root);
	}
	
	private int no_of_leaves(Node<E> n) {
		if(n == null) {
			return 0;
		} else{
			if(n.left==null && n.right==null) {
				return 1;
			} else {
				return no_of_leaves(n.left) + no_of_leaves(n.right);
			}
		}
		
	}
	
	public int no_of_leaves() {
		return no_of_leaves(root);
	}
	
	private BinTree<E> copy(Node<E> n) {
		if(n==null) {
			return new BinTree<E>();
		} else {
		return new BinTree<E>(n.data, copy(n.left), copy(n.right));
		}
	}
	
	public BinTree<E> copy() {
		return copy(root);
	}
	
	private BinTree<E> mirror(Node<E> n) {
		if(n==null) {
			return new BinTree<E>();
		} else {
		return new BinTree<E>(n.data, mirror(n.right), mirror(n.left));
		}
	}
	
	public BinTree<E> mirror() {
		return mirror(root);
	}
	
	private void prune(Node<E> n, int level) {
		if(level <= 1) {
			n.left=null;
			n.right=null;
		} else {
			if(n.left != null) {
				prune(n.left, level-1);
			}
			if(n.right != null) {
				prune(n.right, level-1);
			}
		}
	}
	
	public void prune(int level) {
		if(root == null) {
			throw new IllegalStateException();
		}
		prune(root, level-1);
	}
	
	
	private String toString(Node<E> n, int depth) {
		StringBuilder r = new StringBuilder();
		// add indentation
		for (int i=0;i<depth;i++) {
			r.append("--");
		}
		if (n==null) {
			r.append("null");
		} else {
			r.append(n.data.toString());
			r.append("\n");
			r.append(toString(n.left, depth+1));
			r.append("\n");
			r.append(toString(n.right, depth+1));
		}
		return r.toString();
		
	}
	
	public String toString() {
		return toString(root,0);
	}
	public static void main(String[] args) {
		BinTree<Integer> t1 = new BinTree<Integer>(23,new BinTree<Integer>(12),new BinTree<Integer>(27));
		BinTree<Integer> t2 = new BinTree<Integer>(72,new BinTree<Integer>(43),new BinTree<Integer>());
		BinTree<Integer> t = new BinTree<Integer>(34,t1,t2);
		
		System.out.println(t);
//		System.out.println(t.height());
//		System.out.println(t.noOfNodes());
//		System.out.println(t.isLeaf());
//		System.out.println((new BinTree<Integer>(4)).isLeaf());
//		System.out.println(t.isFull());
//		System.out.println(t.no_of_leaves());
//		System.out.println(t.isBalanced());
		t.prune(3);
		System.out.println(t);

	}
}