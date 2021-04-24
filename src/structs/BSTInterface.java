package structs;

import java.util.ArrayList;

public interface BSTInterface<K extends Comparable<K>,V> {
	public boolean add(K k, V v);
	public boolean remove(K k);
	public Node<K,V> searchNode(K k);
	public ArrayList<V> search(K k);
	public boolean isEmpty();
	public int size();
	public boolean keyExists(K k);
}
