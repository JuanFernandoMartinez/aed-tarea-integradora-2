package structs;

import java.util.ArrayList;

public class Node<K extends Comparable<K>,V> {

	private Node<K,V> parent,left,right;
	private K key;
	private ArrayList<V> values;
	
	public Node(K k, V v) {
		values = new ArrayList<>();
		key = k;
		values.add(v);
	}

	
	public boolean add(K k ,V v)
	{
		if (key.compareTo(k) == 0)
		{
			values.add(v);
			return true;
		}else
		{
			if (key.compareTo(key) < 0)
			{
				if (right == null) 
				{
					right = new Node<K,V>(k,v);
					right.setParent(this);
					return true;
				}else
				{
					return right.add(k, v);
				}
			}else
			{
				if (left == null)
				{
					left = new Node<K,V>(k,v);
					left.setParent(this);
					return true;
				}else
				{
					return left.add(k, v);
				}
			}
		}
	}
	
	
	public boolean remove(K k)
	{
		if (k.compareTo(key) == 0)
		{
			return remove();
		}else
		{
			if (key.compareTo(k) < 0)
			{
				if (right == null) return false;
				else return right.remove(k);
			}else
			{
				if (left == null) return false;
				else return left.remove(k);
			}
		}
	}
	
	
	private boolean remove()
	{
		Node<K,V> aux = getSuccesor();
		if (aux.getParent().left == aux) aux.parent.left = null;
		else aux.parent.right = null;
		
		aux.parent = parent;
		aux.left = left;
		left.parent = aux;
		aux.right = right;
		right.parent = aux;
		if (parent.left == this) parent.left = aux;
		else parent.right = aux;
		return true;		
	}
	
	
	public int calculateWeight()
	{
		if (left == null && right == null)
		{
			return 1;
		}else
		{
			int l,r;
			if (left == null) l = 0;
			else l = left.calculateWeight();
			
			
			if (right == null) r = 0;
			else r = right.calculateWeight();
			
			
			if (l> r) return l;
			else return r;
		}
	}
	
	
	
	
	public Node<K,V> getSuccesor()
	{
		return right.getMinChild();
	}
	
	public Node<K,V> getMinChild()
	{
		if (left == null) return this;
		else return left.getMinChild();
	}
	
	public Node<K,V> search(K k)
	{
		if (key.equals(k))
		{
			return this;
			
		}else
		{
			if (key.compareTo(k)<0)
			{
				if (right == null)
				{
					return null;
				}else
				{
					return right.search(k);
				}
			}else
			{
				if (left == null)
				{
					return null;
				}else
				{
					return left.search(k);
				}
			}
		}
	}
	
	
	
	public Node<K, V> getParent() {
		return parent;
	}

	public void setParent(Node<K, V> parent) {
		this.parent = parent;
	}

	public Node<K, V> getLeft() {
		return left;
	}

	public void setLeft(Node<K, V> left) {
		this.left = left;
	}

	public Node<K, V> getRight() {
		return right;
	}

	public void setRight(Node<K, V> right) {
		this.right = right;
	}

	public ArrayList<V> getValues() {
		return values;
	}

	public void setValues(ArrayList<V> values) {
		this.values = values;
	}


	public K getKey() {
		return key;
	}


	public void setKey(K key) {
		this.key = key;
	}
	
	
	
	
}
