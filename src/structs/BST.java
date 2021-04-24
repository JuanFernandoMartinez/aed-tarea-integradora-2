package structs;

import java.util.ArrayList;

public class BST<K extends Comparable<K>,V> implements BSTInterface<K, V> {
	
	private Node<K,V> root;
	private int size;
	
	public BST() {
		size = 0;
		
	}

	@Override
	public boolean add(K k, V v) {
		if (root == null)
		{
			root = new Node<>(k,v);
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
	public boolean remove(K k) {
		if (root.getKey().equals(k))
		{
			Node<K,V> aux = root.getSuccesor();
			aux.setLeft(root.getLeft());
			root.getLeft().setParent(aux);
			aux.setRight(aux.getRight());
			root.getRight().setParent(aux);
			
			if (aux.getParent().getLeft() == aux) aux.getParent().setLeft(null);
			else aux.getParent().setRight(null);
			
			root = aux;
			size--;
			return true;
		}else
		{
			return root.remove(k);
		}
		
	}

	@Override
	public boolean isEmpty() {
		if (size == 0) return true;
		return false;
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public boolean keyExists(K k) {
		if (search(k) == null) return false;
		return true;
	}

	@Override
	public Node<K, V> searchNode(K k) {
		if (root == null) return null;
		else return root.search(k);	
	}

	@Override
	public ArrayList<V> search(K k) {
		Node<K,V> aux = searchNode(k);
		
		if (aux != null)
		{
			return aux.getValues();
		}
		return null;
	}
	
}
