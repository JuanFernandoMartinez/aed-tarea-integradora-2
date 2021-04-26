package structs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class BSTTest {

	private BST<Character,Integer> a;
	
	private void setup1()
	{
		a = new BST<>();
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
		a.add('t', 305);
		a.add('r', 35);
		a.add('h', 23);
		
		assertEquals(23, a.search('h'));
		assertEquals(null,a.search('H'));
		
	}
	
	@Test
	public void testRemove()
	{
		setup1();
		a.add('t', 305);
		a.add('r', 35);
		a.add('h', 23);
		
		assertEquals(false,a.remove('H'));
		
		a.remove('t');
		assertEquals(2,a.size());
		assertEquals(null,a.search('t'));
		assertEquals('r',a.getRoot().getKey());
		
		a.add('t', 305);
		
		a.remove('h');
		
		assertEquals(2,a.size());
		assertEquals(null,a.getRoot().getLeft());
		
	}
	
	@Test
	public void testIsEmpty()
	{
		setup1();
		assertEquals(true,a.isEmpty());
		a.add('f', 404);
		assertEquals(false,a.isEmpty());
	}
	
	@Test
	public void testKeyExists()
	{
		setup1();
		a.add('f', 404);
		a.add('a', 202);
		assertEquals(false,a.keyExists('b'));
		assertEquals(true,a.keyExists('f'));
	}

}
