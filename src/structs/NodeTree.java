package structs;

import exceptions.RepeatedElementException;

public class NodeTree<K extends Comparable<K>,V> {
	private K key;
	private V value;
	private NodeTree<K,V> left;
	private NodeTree<K,V> right;
	private NodeTree<K,V> parent;
	
	
	public NodeTree(K k, V v) {
		key = k;
		value = v;
	}
	
	public boolean add(K k, V v) throws RepeatedElementException
	{
		if (k.compareTo(key) == 0)
		{
			throw new RepeatedElementException();
		}else if (k.compareTo(key) < 0)
		{
			if (left == null)
			{
				left = new NodeTree<K, V>(k, v);
				left.setParent(this);
				return true;
			}else
			{
				return left.add(k, v);
			}
		}else
		{
			if (right == null)
			{
				right = new NodeTree<K, V>(k, v);
				right.setParent(this);
				return true;
			}else
			{
				return right.add(k, v);
			}
		}
			
	}
	
	
	public V search(K k)
	{
		if (k.compareTo(key) == 0)
		{
			return value;
		}else if (k.compareTo(key)>0)
		{
			if (right == null) return null;
			return right.search(k);
		}else
		{
			if (left == null) return null;
			return left.search(k);
		}
	}
	
	
	
	public NodeTree<K, V> successor()
	{
		if (left == null) return this;
		return left.successor();
	}
	
	public boolean remove()
	{
		NodeTree<K, V> aux = successor();
		
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public NodeTree<K, V> getLeft() {
		return left;
	}

	public void setLeft(NodeTree<K, V> left) {
		this.left = left;
	}

	public NodeTree<K, V> getRight() {
		return right;
	}

	public void setRight(NodeTree<K, V> right) {
		this.right = right;
	}

	public NodeTree<K, V> getParent() {
		return parent;
	}

	public void setParent(NodeTree<K, V> parent) {
		this.parent = parent;
	}
	
	
	
	
}