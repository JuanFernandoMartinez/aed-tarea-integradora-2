package structs;

import exceptions.NotFoundNodeException;
import exceptions.RepeatedElementException;

public interface BSTInterface<K extends Comparable<K>,V> {
	public boolean add(K k, V v) throws RepeatedElementException;
	public V search(K k);
	public boolean remove(K k) throws NotFoundNodeException;
}
