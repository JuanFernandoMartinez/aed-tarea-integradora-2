package structs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class AVLNodeTest {
	
	private AVLNode<Integer,Integer> a;
	
	private void setup1()
	{
		a = new AVLNode<Integer, Integer>(10, 1, null);
	}

	@Test
	public void testAdd()
	{
		setup1();
		a.add(7, 2);
		assertEquals(7,a.getLeft().getKey());
		a.add(12, 3);
		assertEquals(12,a.getRight().getKey());
		assertEquals(2,a.getWeight());
		
		a.add(17, 4);
		
		assertEquals(3,a.getWeight());
	}
	
	@Test
	public void testRemove()
	{
		setup1();
		
		a.add(7, 2);
		a.add(12, 3);
		
		assertEquals(12,a.getRight().getKey());
		a.remove(12);
		
		assertEquals(null,a.getRight());
		assertEquals(0,a.getrWeight());
		a.remove(7);
		assertEquals(1,a.getWeight());
		assertEquals(null,a.getLeft());
	}
	
	@Test
	public void testSearch()
	{
		setup1();
		a.add(7, 1);
		a.add(12, 2);
		a.add(16, 4);
		
		assertNull(a.search(24));
		assertEquals(2,a.search(12).getValue());
	}
	
	
	@Test
	public void testLeftRotate()
	{
		setup1();
		a.add(11, 2);
		a.add(12, 3);
		a.add(13, 4);
		
		a.getRight().leftRotate();
		
		assertEquals(12,a.getRight().getKey());
		assertEquals(2,a.getRight().getWeight());
		assertEquals(1,a.search(11).getWeight());
		
		
	}
	
	@Test
	public void testRightRotate()
	{
		setup1();
		a.add(9, null);
		a.add(8, null);
		a.add(7, null);
		
		a.getLeft().rightRotate();
		assertEquals(8,a.getLeft().getKey());
		assertEquals(2,a.getLeft().getWeight());
		assertEquals(1,a.search(9).getWeight());
		
	}
	
	@Test
	public void testBalance()
	{
		setup1();
		a.add(9, null);
		a.add(8, null);
		a.add(6, null);
		a.add(7, null);
		
		assertEquals(false,a.getLeft().isBalanced());
		
		a.getLeft().balance();
		assertEquals(true,a.getLeft().isBalanced());
		
	}
	
	

}
