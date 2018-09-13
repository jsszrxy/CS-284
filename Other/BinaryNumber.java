import java.util.Arrays;
/**
 * @author David Horowitz:
 * I pledge my honor that I have abided by the Stevens honor system.
 *
 */
public class BinaryNumber {

	//data fields 
	private int data[];
	private boolean overflow;
	/**
	 * Constructor for a binary number that has a length of (int length) and is made up of all zeros.
	 * @param length - length of binary number 
	 */
	public BinaryNumber(int length) {
		this.data = new int[length];
	}
	/**
	 * Constructor for a binary number based on a string made up of 1's and 0's.
	 * @param str - String of 1's and 0's
	 */
	public BinaryNumber(String str) {
		this.data = new int[str.length()];
		for(int i = 0; i < str.length(); i++) {
			this.data[i] = Character.getNumericValue(str.charAt(i));
		}
		
	}
	/**
	 * Getter for length of a binary number.
	 * @return length of a BinaryNumber
	 */
	public int getLength() {
		return this.data.length;
	}
	/**
	 * Getter for a specific digit of a binary number. If index is out of bounds, a message is printed.
	 * @param index - Index of BinaryNumber
	 * @return Value of the digit at the selected index.
	 */
	public int getDigit(int index) {
		if (index < 0 || index >= this.getLength()) {
			System.out.println("Given index is out of bounds!");
			System.exit(1);
		}
		return this.data[index];
	}
	/**
	 * Converts a binary number into decimal form.
	 * @return Decimal form of a BinaryNumber
	 */
	public int toDecimal() {
		int sum = 0;
		for(int i=0; i < this.getLength(); i++) {
			sum += (this.getDigit(i) * (int)Math.pow(2, i));
		}
		return sum;
	}
	/**
	 * Changes an array to a copy of itself that is expanded to the requested new length.
	 * @param array - Original array that is to be expanded
	 * @param newlength - Length of new array
	 * @return A new array that has been expanded
	 */
	private int[] reallocate(int array[], int newlength) {
		int newArray[] = Arrays.copyOf(array, newlength);
		return newArray;
		
	}
	/**
	 * Shifts an array to the right based on the given amount and fills in the empty spaces left over with zeros.
	 * @param amount How far to shift an array to the right
	 */
	public void shiftR(int amount) {
		int oldindexlength = this.getLength() - 1;
		this.data = reallocate(this.data, this.data.length + amount);
		int newindexlength = this.getLength() - 1;
		int count = 0;
		for(int i = oldindexlength; i >= 0; i--) {
			this.data[newindexlength - count] = this.data[i];
			this.data[i] = 0;
			count++;
		}
		
		
	}
	/**
	 * Adds two binary numbers together and replaces the receiving binary number with the result of the addition. If the result of the addition causes an overflow, this.overflow is set to True.
	 * @param aBinaryNumber - Binary number to be added to the receiving binary number.
	 */
	public void add(BinaryNumber aBinaryNumber) {
		if (this.getLength() != aBinaryNumber.getLength()) {
			System.out.println("Those two Binary Numbers are not the same length!");
		}
		else {
			int[] temparray = new int[this.getLength()+1];
			for(int i = 0; i < this.getLength(); i++) {
				temparray[i] += this.getDigit(i) + aBinaryNumber.getDigit(i);
				if (temparray[i] == 2 ) {
					temparray[i+1] += 1;
					temparray[i] = 0;
				}
				else if (temparray[i] == 3) {
					temparray[i+1] += 1;
					temparray[i] = 1;					
					
				}				
			}
			if (temparray[temparray.length - 1] == 1) {
				this.overflow = true;
				if (this.data.length != temparray.length) {
					this.shiftR(temparray.length - this.data.length);
				}
			}
			else {
				if (this.data.length != temparray.length-1) {
					this.shiftR(temparray.length- 1 - this.data.length);
				}
			}

			for(int i = 0; i < this.data.length; i++) {
				this.data[i] = temparray[i];
			}
		}
	}
	/**
	 * Clears this.overflow by setting it to false.
	 */
	public void clearOverflow() {
		this.overflow = false;
	}
	/**
	 * If the binary number has this.overflow = true, the string "Overflow" is returned. 
	 * Otherwise, the binary number is returned as a string.
	 */
	public String toString() {
		if(this.overflow == true) {
			return "Overflow";
		}
		else {
			int indexlength = this.getLength();
			String s = "";
			for(int i =0; i < indexlength; i++) {
				s += this.getDigit(i);
			}
			return s;
		}

	}
	
	
}
