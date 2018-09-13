import static org.junit.Assert.*;

import org.junit.Test;

//David Horowitz
//I pledge my honor that I have abided by the Stevens Honor System.

public class AnagramsTest {
	@Test
	public void test() {
		String[] args = null;
		Anagrams.main(args);
		assertEquals(Anagrams.maxEntriesTest, 
				"[236204078=[alerts, alters, artels, estral, laster, lastre, rastle, ratels, relast, resalt, salter, slater, staler, stelar, talers]]");
	}

}
