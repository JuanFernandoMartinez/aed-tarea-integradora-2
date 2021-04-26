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
		a.add(10, 20);
		a.add(11, 20);
		a.add(12, 20);
		a.add(13, 20);
		
		a.remove(11);
		
		assertEquals(3,a.size());
		assertEquals(12,a.getRoot().getKey());
		
		setup1();
		a.add(10, 20);
		a.add(11, 20);
		a.add(12, 20);
		a.add(13, 20);
		
		a.remove(12);
		
		assertEquals(13, a.getRoot().getRight().getKey());
		
	}
	
	

}
