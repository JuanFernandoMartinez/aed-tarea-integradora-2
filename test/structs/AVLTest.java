package structs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AVLTest {

	private AVL<Integer,Integer> a;
	
	private void setup1()
	{
		a = new  AVL<>();
	}
	
	
	@Test
	public void testAdd()
	{
		setup1();
		a.add(10, 20);
		assertEquals(1,a.size());
		a.add(11, 20);
		a.add(12, 20);
		a.add(13, 20);
		
		assertEquals(true,a.getRoot().isBalanced());
		assertEquals(4,a.size());
		assertEquals(3,a.getRoot().getWeight());
		assertEquals(10,a.getRoot().getLeft().getKey());
	}
	
	
	@Test
	public void testRemove()
	{
		setup1();
		a.add(10, null);
		a.add(5, null);
		a.add(6, null);
		a.add(3, null);
		a.add(4, null);
		a.add(7, null);
		a.add(15, 7);
		a.add(12, null);
		a.add(17, null);
		a.add(16, null);
		
		
		assertEquals(10,a.size());
		assertEquals(7,a.search(15));
		
		a.remove(15);
		assertEquals(null,a.search(15));
		
	}
	
	

}
