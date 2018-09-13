package Maze;

//David Horowitz
//I pledge my honor that I have abided by the Stevens honor system.

public class PairInt implements Comparable{
	
	//data fields
	private int x;
	private int y;
	
	/**
	 * Constructs a new PairInt
	 * @param x
	 * @param y
	 */
	public PairInt(int x,int y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Getter for x.
	 * @return x
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Getter for y.
	 * @return y
	 */
	public int getY() {
		return y;
	}
	/**
	 * Setter for x.
	 * @param x - new int to set x to.
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Setter for y.
	 * @param y - new int to set y to.
	 */
	public void setY(int y) {
		this.y = y;
	}
	/**
	 * Returns true if the object receiving the message is equal to p, and false otherwise.
	 */
	public boolean equals(Object p) {
		if(this.compareTo(p) == 0)
			return true;
		return false;
	}
	
	/**
	 * Returns a string version of a PairInt.
	 */
	public String toString() {
		return "(" + x + "," + y + ")";
	}
	
	/**
	 * Creates a new copy of a PairInt.
	 * @return PairInt(x,y)
	 */
	public PairInt copy() {
		return new PairInt(x, y);
	}
	
	/**
	 * Returns 0 if the PairInt p is equal to the PairInt receiving the message. Otherwise returns -1.
	 */
	public int compareTo(Object p) {
		if(!(p instanceof PairInt)) {
			throw new IllegalArgumentException("Object must be a PairInt");
		}
		if(this.getX() == ((PairInt) p).getX() && this.getY() == ((PairInt) p).getY()) {
			return 0;
		} else {
			return -1;
		}
	}
}
