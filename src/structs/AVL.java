package structs;

import java.util.ArrayList;

public class AVL<K extends Comparable<K>,V> implements BSTInterface<K, V> {
	
	private Node<K,V> root;
	private int size;
	
	public AVL() {
		size = 0;
		
	}
	

	@Override
	public boolean add(K k, V v) {
		if (root == null)
		{
			root = new Node<>(k,v,true);
			size++;
			return true;
		}else
		{
			boolean status = root.add(k, v);
			if (status)
			{
				size++;
				return true;
			}else
			{
				return false;
			}
		 
		}
	}

	

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean keyExists(K k) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(K k) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Node<K, V> searchNode(K k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<V> search(K k) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
