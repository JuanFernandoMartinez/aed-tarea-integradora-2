package structs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BSTTest {

	private BST<Character,Integer> a;
	
	private void setup1()
	{
		a = new BST<>();
	}
	
	private void setup2() {
		a.add('t', 305);
		a.add('r', 35);
		a.add('h', 23);
	}
	
	@Test
	public void testAdd()
	{
		setup1();
		assertEquals(0,a.size());
		a.add('a', 5);
		a.add('A', 4);
		a.add('z', 8);
		
		assertEquals(3,a.size());
		assertEquals(4,a.search('A'));
		
	}
	
	@Test
	public void testSearch()
	{
		setup1();
		setup2();
		
		assertEquals(23, a.search('h'));
		assertNull(a.search('H'));
		
	}
	
	@Test
	public void testRemove()
	{
		setup1();
		setup2();
		
		assertEquals(false,a.remove('H'));
		
		a.remove('t');
		assertEquals(2,a.size());
		assertNull(a.search('t'));
		assertEquals('r',a.getRoot().getKey());
		
		a.add('t', 305);
		
		a.remove('h');
		
		assertEquals(2,a.size());
		assertNull(a.getRoot().getLeft());
		
	}
	
	@Test
	public void testIsEmpty()
	{
		setup1();
		assertTrue(a.isEmpty());
		a.add('f', 404);
		assertFalse(a.isEmpty());
	}
	
	@Test
	public void testKeyExists()
	{
		setup1();
		a.add('f', 404);
		a.add('a', 202);
		assertFalse(a.keyExists('b'));
		assertTrue(a.keyExists('f'));
	}

}
