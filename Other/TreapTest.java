import static org.junit.Assert.*;
import org.junit.Test;

//David Horowitz
//I pledge my honor that I have abided by the Stevens Honor System.

public class TreapTest {
		@Test
		public void test() {
			Treap<Integer> testTree = new Treap<Integer>();
			testTree.add (4 ,19);
			testTree.add (2 ,31);
			testTree.add (6 ,70);
			testTree.add (1 ,84);
			testTree.add (3 ,12);
			testTree.add (5 ,83);
			assertEquals(true,testTree.add(7 ,26)); //Tests that add returns true when added to tree
			assertEquals(false,testTree.add(7 ,26)); //Tests that adding a duplicate returns false
			assertEquals("(key=1, priority=84)\n" //Tests the toString() method
					+"  null\n"
					+"  (key=5, priority=83)\n"
					+"    (key=2, priority=31)\n"
					+"      null\n"
					+"      (key=4, priority=19)\n"
					+"        (key=3, priority=12)\n"
					+"          null\n"
					+"          null\n"
					+"        null\n"
					+"    (key=6, priority=70)\n"
					+"      null\n"
					+"      (key=7, priority=26)\n"
					+"        null\n"
					+"        null", testTree.toString());
			
			assertEquals(true, testTree.find(7)); //Tests that find returns true if they key is in the treap
			assertEquals(false, testTree.find(32)); //Tests that find returns false if the key is not found
			
			assertEquals(false, testTree.delete(32)); //Tests that delete returns false if the key cannot be deleted.
			assertEquals(true, testTree.delete(7)); //Tests that delete returns true if the key is deleted.
			
			assertEquals("(key=1, priority=84)\n" //Tests that the delete ran successfully
					+"  null\n"
					+"  (key=5, priority=83)\n"
					+"    (key=2, priority=31)\n"
					+"      null\n"
					+"      (key=4, priority=19)\n"
					+"        (key=3, priority=12)\n"
					+"          null\n"
					+"          null\n"
					+"        null\n"
					+"    (key=6, priority=70)\n"
					+"      null\n"
					+"      null", testTree.toString());
			
			assertEquals(true, testTree.delete(1)); //Tests that delete works on the root of the tree
			
			assertEquals("(key=5, priority=83)\n" //Tests that the delete correctly rearranged the tree after deletion of the root 
					+"  (key=2, priority=31)\n"
					+"    null\n"
					+"    (key=4, priority=19)\n"
					+"      (key=3, priority=12)\n"
					+"        null\n"
					+"        null\n"
					+"      null\n"
					+"  (key=6, priority=70)\n"
					+"    null\n"
					+"    null", testTree.toString());
			

		}
}

