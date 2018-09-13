package stacks;

import java.util.EmptyStackException;

public class BalancedChecker {

	
	private static final String OPEN="({[<";
	private static final String CLOSE=")}]>";
	
	public static boolean isOpen(Character c) {
		return OPEN.indexOf(c)>-1;
	}
	
	public static boolean isClose(Character c) {
		return CLOSE.indexOf(c)>-1;
	}
	public static boolean isBalanced(String str) {
		boolean balanced = true;
		int i = 0;
		StackLL<Character> s = new StackLL<Character>();
		
		try {
		while(balanced && i<str.length()) {
			if(isOpen(str.charAt(i))) {
				s.push(str.charAt(i));
			} else {
				Character c = s.pop();
				balanced = balanced && OPEN.indexOf(c)==CLOSE.indexOf(str.charAt(i));
			}
			i++;
		} 
		} catch(EmptyStackException e){
			balanced = false;
		}
		
		return balanced && s.empty();
	}
	
	public static void main(String[] args) {
		System.out.println(isBalanced("()()"));
		System.out.println(isBalanced("((()))"));
		System.out.println(isBalanced("([)(])"));
		System.out.println(isBalanced(")("));

	}
}
