package lists;

public class NodeInt {
	//data fields
	private Integer data;
	private NodeInt next;
	
	NodeInt(Integer data) {
		this.data = data;
		this.next = null;
	}
	NodeInt(Integer data, NodeInt next) {
		this.data = data;
		this.next = next;
	}
	
	public int size() {
		if(this.next == null) {
			return 1;
		}
		return 1 + next.size(); 
	}
	
	public boolean allPositive() {
		if(this.next == null) {
			return data>=0;
		}
		if(this.data > 0) {
			return next.allPositive();
		} else {
			return false;
		}
	}
	
	public NodeInt bump() {
		if(this.next==null) {
			return new NodeInt(data + 1);
		}
		return new NodeInt(data+1, next.bump());
	}
	 
	public NodeInt filterEven() { //remove odd elements retain even ones 
		return null;
	}
	
	public String toString() {
		if (next == null) {
			return data.toString();
		} else {
			return data.toString() + ',' + next.toString();
		}
	}
	
	
	public boolean sorted() {
		if(this.next == null) {
			return true;
		}
		if(this.data <= next.data) {
			return next.sorted();
		} else {
			return false;
		}
	}
	
	public NodeInt repeat(int n, Integer item) {
		NodeInt head = new NodeInt(item);
		NodeInt current = head;

		while (n > 1) {
			current.next = new NodeInt(item);
			current = current.next;
			n--;
		}
		return head;
	}
	
	public NodeInt stutter(int n) {
		if(this.next == null) {
			return this.repeat(n, this.data);
		} else {
			NodeInt result = this.repeat(n, data);
			NodeInt head = result;
			
			while (result.next != null) {
				result = result.next;
			}
			result.next = next.stutter(n);
			
			return head;
		}
	}
	
	
	public static void main(String[] args) {
		NodeInt n1 = new NodeInt(1);
		NodeInt n2 = new NodeInt(2,n1);
		NodeInt n3 = new NodeInt(1,n2);
		System.out.println(n3);
		//System.out.println(repeat(5,0));
		System.out.println(n3.sorted());
		System.out.println(n3.stutter(3));
		//System.out.println(n3.allPositive());
		//System.out.println(n3.bump());
	}
}
