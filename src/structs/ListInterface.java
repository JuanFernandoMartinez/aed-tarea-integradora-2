package structs;

public interface ListInterface<K extends Comparable<K>,V> {
	
	public int size();
	public boolean isEmpty();
	public boolean add(K k, V v);
	public V get(int index);
	
	
}
