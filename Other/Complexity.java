/**
 * David Horowitz && Rachel Cipkins
 * 
 * This class defines methods of various times complexities.
 * I pledge my honor that I have abided by the Stevens Honor System. -dhorowit -rcipkins
 * @author dhorowit && rcipkins
 *
 */
public class Complexity {
	
	/**
	 * This is a method with time complexity O(n^2).
	 * @param n - Will run n^2 times.
	 */
	public void method1(int n) {
		int counter =0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				 System.out.println("Operation: "+ counter);
				 counter ++;

			}
		}
	}
	/**
	 * This is a method with time complexity O(n^3).
	 * @param n - Will run n^3 times.
	 */
	public void method4(int n) {
		int counter = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				for(int k=0; k<n; k++) {
					 System.out.println("Operation: "+ counter);
					 counter ++;
				}
			}
		}
	}
	/**
	 * This is a method with time complexity O(log n).
	 * @param n - Will run log n times.
	 */
	public void method2(int n) {
		int counter = 0;
		for(int j = 1; j<n; j = j * 2) {
			System.out.println("Operation: "+ counter);
			counter ++;
		}
	}
	/**
	 * This is a method with time complexity O(n*log n).
	 * @param n - Will run n*log n times.
	 */
	public void method3(int n) {
		int counter = 0;
		for(int i = 0; i<n; i++) {
			for(int j = 1; j<n; j = j * 2) {
				 System.out.println("Operation: "+ counter);
				 counter ++;
			}
		}
	}
	/**
	 * This is a method with time complexity O(log log n).
	 * @param n - Will run log log n times.
	 */
	public void method5(int n) {
		int counter = 0;
		for(int i = n; i>1; i = (int)(Math.sqrt(i))) {
			 System.out.println("Operation: "+ counter);
			 counter ++;
		}
	}
	
	/**
	 * This is a counter for method 6.
	 */
	private int counter = 0;	
	/**
	 * This is a method with time complexity O(2^n).
	 * @param n - Will run 2^n times.
	 */
	public int method6(int n) {
		if (n==0) {
			System.out.println("Operation: " + counter);
			counter++;
			return 2;
		}
		return method6(n-1) + method6(n-1);
	}
	

}
