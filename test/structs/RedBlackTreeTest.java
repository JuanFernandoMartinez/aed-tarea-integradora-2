package structs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RedBlackTreeTest {
	private RedBlackTree<Integer, Integer> a;
	
	private void setup1()
	{
		a = new RedBlackTree<>();
	}

	@Test
	public void testInsert()
	{
		setup1();
		assertEquals(0,a.size());
		a.insert(10, 1);
		assertEquals(1,a.size());
		a.insert(12, 2);
		
		assertEquals(2,a.size());
		
		assertEquals(2,a.search(12).getValue());
		
		assertEquals(1,a.search(10).getValue());
		
	}

}
