package structs;

public class Node<K,V> {

	private V value;
	private K key;
	private Node<K,V> next;
	
	
	public Node(K key, V value) {
		this.value = value;
		this.key = key;
		
	}

	
	public boolean add(K k,V v)
	{
		if (next == null)
		{
			next = new Node<K,V>(k, v);
			return true;
			
		}else
		{
			return next.add(k, v);
		}
	}
	
	public V get(int index)
	{
		if (index == 0)
		{
			return value;
		}else
		{
			if (next == null) return null;
			return next.get(index-1);
		}
	}
	
	
	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public Node<K, V> getNext() {
		return next;
	}

	public void setNext(Node<K, V> next) {
		this.next = next;
	}
	
	
}
