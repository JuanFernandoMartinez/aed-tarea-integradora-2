package structs;

public interface BSTInterface<K extends Comparable<K>, V> {
	public boolean add(K key, V value);

	public boolean update(K key, V value);

	public boolean remove(K key);

	public V search(K key);

	public boolean keyExists(K key);

	public boolean isEmpty();
}
