package structs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NodeTest {

	private Node<Character,Integer> a;

	
	private void setup1()
	{
		a = new Node<>('a', 10);
	}
	
	
	@Test
	public void testAdd()
	{
		setup1();
		a.add('b', 5);
		a.add('c', 12);
		a.add('A', 30);
		
		
		assertEquals(5, a.getRight().getValue());
		assertEquals(30, a.getLeft().getValue());
		assertEquals(a,a.getRight().getParent());
	}
	
	@Test
	public void testSearch()
	{
		setup1();
		a.add('b', 5);
		a.add('c', 12);
		a.add('A', 30);
		
		Integer n = a.search('c').getValue();
		
		assertEquals(12,n);
		
	}
	
	
	
	
	
	@Test
	public void testRemove()
	{
		setup1();
		a.add('b', 5);
		a.add('c', 12);
		a.add('A', 30);
		
		a.remove('b');
		
		assertEquals(12,a.getRight().getValue());
		
		
	}
	
	
	
	
	
	
	
	
	
	
	

}
