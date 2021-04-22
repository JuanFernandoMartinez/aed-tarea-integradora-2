package structs;

public class LinkedList<K extends Comparable<K>,V> implements ListInterface<K, V>,Comparable<LinkedList<K,V>> {

	
	private int size;
	private Node<K,V> first;
	private K key;
	private LinkedList<K, V> left;
	private LinkedList<K, V> right;
	private LinkedList<K, V> parent;
	
	public LinkedList(K k,V v) {
		size = 0;
		key = k;
		first = new Node<>(k, v);
	}
	
	
	@Override
	public int size() {
		
		return size;
	}

	@Override
	public boolean isEmpty() {
		if (size == 0) return true;
		return false;
	}

	@Override
	public boolean add(K k, V v) {
		if (k.compareTo(key) == 0)
		{
			if (first == null)
			{
				first = new Node<>(k, v);
				size++;
				return true;
			}else
			{
				Boolean status = first.add(k, v);
				if (status) size++;
				return status;
			}
		}else if (k.compareTo(key)>0)
		{
			if (right == null)
			{
				right = new LinkedList<K,V>(k, v);
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
				left = new LinkedList<>(k,v);
				left.setParent(this);
				return true;
			}else
			{
				return left.add(k, v);
			}
		}
	}
	
	public LinkedList<K,V> search(K k)
	{
		if (k.compareTo(key) == 0)
		{
			return this;
		}else if (k.compareTo(key)>1)
		{
			if (right == null) return null;
			return right.search(k);
		}else
		{
			if (left == null) return null;
			return left.search(k);
		}
	}
	
	public boolean remove()
	{
		LinkedList<K, V> aux = right.successor();
		aux.setLeft(left);
		left.setParent(aux);
		aux.setRight(right);
		right.setParent(aux);
		
		if (parent.left == this)
		{
			parent.setLeft(aux);
		}else
		{
			parent.setRight(aux);
		}
		return true;
	}
	
	public LinkedList<K,V> successor()
	{
		if (left == null)
		{
			return this;
		}else
		{
			return left.successor();
		}
	}


	@Override
	public V get(int index) {
		
		if (first == null) return null;
		return first.get(index-1); 
	}


	public int getSize() {
		return size;
	}


	public void setSize(int size) {
		this.size = size;
	}


	public Node<K,V> getFirst() {
		return first;
	}


	public void setFirst(Node<K,V> first) {
		this.first = first;
	}


	public K getKey() {
		return key;
	}


	public void setKey(K key) {
		this.key = key;
	}


	@Override
	public int compareTo(LinkedList<K, V> o) {
		if(key.compareTo(o.key)>0) return 1;
		else if (key.compareTo(o.key)<0)return -1;
		return 0;
	}


	public LinkedList<K, V> getLeft() {
		return left;
	}


	public void setLeft(LinkedList<K, V> left) {
		this.left = left;
	}


	public LinkedList<K, V> getRight() {
		return right;
	}


	public void setRight(LinkedList<K, V> right) {
		this.right = right;
	}


	public LinkedList<K, V> getParent() {
		return parent;
	}


	public void setParent(LinkedList<K, V> parent) {
		this.parent = parent;
	}
	
	

}
