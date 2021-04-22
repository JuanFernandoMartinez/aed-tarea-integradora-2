package structs;

public interface BSTInterface<K extends Comparable<K>,V> {
	public boolean add(K k, V v);
	public V search(K k);
	public boolean remove(K k);
}
