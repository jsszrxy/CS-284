package stacks;

public class PalindromeChecker {
	
	private String inputStr;
	private StackLL<Character> charStack;
	
	PalindromeChecker(String str) {
		inputStr = str;
		charStack = new StackLL<Character>();
		fillStack(str);
	}
	
	private void fillStack(String str) {
		for(int i = 0; i<str.length(); i++) {
			charStack.push(str.charAt(i));
		}
	}
	private String buildReverse() {
		StringBuilder s = new StringBuilder();
		
		while(!charStack.empty()) {
			s.append(charStack.pop());
		}
		return s.toString();
	}
	
	
	
	public boolean isPalindrome() {
		String revTemp = buildReverse();
		String temp = inputStr;
		boolean result =true;
		int i = 0;
		int j = 0;
		
		while(i<temp.length() && result) {
			
			while(i< temp.length() && temp.charAt(i)== ' ') {
				i++;
			}
			while(j<revTemp.length() && revTemp.charAt(j) == ' ') {
				j++;
			}
			
			if(i< temp.length() && j<revTemp.length()) {
				result = result & Character.toLowerCase(temp.charAt(i)==);
				i++;
				j++;
			}
 		}
		
	}
	
	public static void main(String[] args) {
		PalindromeChecker pc = new PalindromeChecker("kayak");
		PalindromeChecker pc1 = new PalindromeChecker("kayaK");
		PalindromeChecker pc2 = new PalindromeChecker("kaya k");
		PalindromeChecker pc3 = new PalindromeChecker("cayak");
		
		System.out.println(pc.isPalindrome());
		System.out.println(pc1.isPalindrome());
		System.out.println(pc2.isPalindrome());
		System.out.println(pc3.isPalindrome());

	}
}
