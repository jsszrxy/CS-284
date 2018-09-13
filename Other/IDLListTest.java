import static org.junit.Assert.*;

import java.util.NoSuchElementException;

import org.junit.Test;

//@author David Horowitz
//I pledge my honor that I have abided by the Stevens Honor System. -dhorowit

public class IDLListTest {

	@Test
	public void test() {
		IDLList<Integer> s = new IDLList<Integer>();
		
		s.add(3);
		s.add(2);
		s.add(0,1);
		int head = s.getHead();
		int tail = s.getLast();
		int middle= s.get(1);
		
		assertEquals(middle, 2);
		assertEquals(head, 1);
		assertEquals(tail,3);
		assertEquals(s.toString(), "(1, 2, 3)");
	}
	
	@Test
	public void test2() {
		IDLList<Integer> s = new IDLList<Integer>();
		
		s.append(1);
		s.append(2);
		s.add(2,3);
		int head = s.getHead();
		int tail = s.getLast();
		int size = s.size();
		
		assertEquals(head, 1);
		assertEquals(tail,3);
		assertEquals(size,3);
		assertEquals(s.toString(), "(1, 2, 3)");
	}
	
	@Test
	public void test3() {
		IDLList<Integer> s = new IDLList<Integer>();
		
		s.append(1);
		s.append(2);
		
		try {
			s.add(3,2);
			assert(false);
		} catch (IndexOutOfBoundsException e) {
			assert(true);
		}
		
		
	}
	
	@Test
	public void test4() {
		IDLList<Integer> s = new IDLList<Integer>();
		
		s.append(1);
		s.append(2);
		s.append(3);
		
		int removed = s.remove();
		assertEquals(removed, 1);
		
		s.remove();
		int head = s.getHead();
		int tail = s.getLast();
		assertEquals(head,3);
		assertEquals(tail,3);
		assertEquals(s.toString(), "(3)");
		
	}
	
	@Test
	public void test5() {
		IDLList<Integer> s = new IDLList<Integer>();
		
		s.append(1);
		s.removeAt(0);
		int size = s.size();
		
		try {
			s.getHead();
			assert(false);
		} catch(NoSuchElementException e) {
			assert(true);
		}
		
		assertEquals(size, 0);
		assertEquals(s.toString(), "()");
	}
	
	@Test
	public void test6() {
		IDLList<Integer> s = new IDLList<Integer>();
		
		s.append(1);
		s.add(0);
		s.add(2,3);
		
		int removed = s.removeLast();
		int head = s.getHead();
		int tail = s.getLast();
		int size = s.size();
		
		assertEquals(removed, 3);
		assertEquals(head, 0);
		assertEquals(tail, 1);
		assertEquals(size, 2);
		
	}
	
	@Test
	public void test7() {
		IDLList<Integer> s = new IDLList<Integer>();
		
		s.append(1);
		s.append(2);
		s.append(1);
		
		boolean remove = s.remove(1);
		int head = s.getHead();
		int size = s.size();

		
		assertEquals(remove, true);
		assertEquals(s.toString(), "(2, 1)");
		assertEquals(head, 2);
		assertEquals(size, 2);
		
		
	}
	
	@Test
	public void test8() {
		IDLList<Integer> s = new IDLList<Integer>();

		s.append(1);
		s.append(2);
		s.append(3);
		
		try {
			s.get(4);
			assert(false);
		} catch(IndexOutOfBoundsException e) {
			assert(true);
		}
	}
	
	@Test
	public void test9() {
		IDLList<Integer> s = new IDLList<Integer>();
		
		s.add(3);
		
		int removed = s.removeLast();
		
		assertEquals(removed, 3);
		
		try {
			s.remove();
			assert(false);
		} catch (NoSuchElementException e) {
			assert(true);
		}
		
		
	}
	
	@Test
	public void test10() {
		IDLList<Integer> s = new IDLList<Integer>();
		
		s.add(0,1);
		s.append(2);
		
		try {
			s.removeAt(4);
			assert(false);
		} catch(IndexOutOfBoundsException e) {
			assert(true);
		}
	}
	
}
