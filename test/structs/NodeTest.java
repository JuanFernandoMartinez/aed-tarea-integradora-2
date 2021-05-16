package structs;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class NodeTest {

	private Node<Character,Integer> a;

	
	private void setup1()
	{
		a = new Node<>('a', 10);
	}
	
	private void setup2() {
		a.add('b', 5);
		a.add('c', 12);
		a.add('A', 30);
	}
	
	@Test
	public void testAdd()
	{
		setup1();
		setup2();
		
		
		assertEquals(5, a.getRight().getValue());
		assertEquals(30, a.getLeft().getValue());
		assertSame(a,a.getRight().getParent());
	}
	
	@Test
	public void testSearch()
	{
		setup1();
		setup2();
		
		assertEquals(12, a.search('c').getValue());
		
	}
	

	@Test
	public void testRemove()
	{
		setup1();
		setup2();
		
		a.remove('b');
		
		assertEquals(12,a.getRight().getValue());
		
		
	}
	

}
