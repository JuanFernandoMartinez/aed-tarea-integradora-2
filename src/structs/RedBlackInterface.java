package structs;

public interface RedBlackInterface<K extends Comparable<K>,V> {
	public void leftRotate(RedBlackNode<K,V> a);
	public void leftRotateFixup(RedBlackNode<K,V> a);
	public void rightRotate(RedBlackNode<K,V> a);
	public void rightRotateFixup(RedBlackNode<K,V> a);
	public void insert(K k, V v);
	public void insertFixup(RedBlackNode<K,V> a);
	public boolean remove(K k);
	public void removeFixup(RedBlackNode<K,V> a);
	public void fixNodeData(RedBlackNode<K,V> a, RedBlackNode<K,V> b);
	public RedBlackNode<K,V> search(K key);
}
