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
		a.insert(12, 2);
		a.insert(15, 3);
		a.insert(5, 4);
		a.insert(6, 5);
		
		assertEquals(1,a.searchValue(10));
		assertEquals(2,a.searchValue(12));
		assertEquals(3,a.searchValue(15));
		assertEquals(4,a.searchValue(5));
		assertEquals(5,a.searchValue(6));
		assertEquals(null,a.searchValue(21));

	}
	
	@Test
	public void testSearch() {
		setup1();
		a.insert(12, 2);
		a.insert(5, 7);
		a.insert(6, 9);
		a.insert(77, 4);
		
		assertEquals(2, a.searchValue(12));
		assertEquals(9, a.searchValue(6));
		assertNull(a.searchValue(21));
	}

}
