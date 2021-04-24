package structs;

import java.util.ArrayList;

public class AVL<K extends Comparable<K>,V> implements BSTInterface<K, V> {

	private Node<K,V> root;
	private int size;

	public AVL() {
		size = 0;

	}

	@Override
	public boolean add(K key, V value) {
		return false;
	}

	@Override
	public boolean update(K key, V value) {
		return false;
	}


	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
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


	public Node<K, V> searchNode(K k) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V search(K k) {
		// TODO Auto-generated method stub
		return null;
	}



}