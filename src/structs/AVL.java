package structs;

import java.util.LinkedList;

public class AVL<K extends Comparable<K>,V> implements AVLInterface<K, V> {
	
	
	private AVLNode<K,V> root;
	private int size;

	@Override
	public boolean add(K k, V v) {
		if (root == null)
		{
			root = new AVLNode<>(k, v, this);
			size++;
			return true;
		}else
		{
			root.add(k, v);
			root.balance();
			size++;
			return true;
		}
		
	}

	@Override
	public boolean remove(K k) {
		if (root == null) return false;
		else
		{
			if (root.getKey().equals(k))
			{
				if (size == 1)
				{
					root = null;
					size--;
					return true;
				}else
				{
					AVLNode<K, V> aux = root.getSuccesor();
					aux.setLeft(root.getLeft());
					aux.setRight(root.getRight());
					
					if (aux.getLeft() != null) 
					{
						aux.getLeft().setParent(aux);
						aux.setlWeight(aux.getLeft().getWeight());
					}
					if (aux.getRight() != null) 
					{
						aux.getRight().setParent(aux);
						aux.setrWeight(aux.getRight().getWeight());
					}
					
					aux.setParent(null);
					aux.setOwner(this);
					root = aux;
					if (aux.getlWeight() > aux.getrWeight()) aux.setWeight(aux.getlWeight()+1);
					else aux.setWeight(aux.getrWeight()+1);
					
					root.balance();
					size--;
					
					
				}
			}else
			{
				boolean status = root.remove(k);
				size--;
				if (status ) root.balance();
			}
		}
		return false;
	}

	@Override
	public AVLNode<K, V> searchNode(K k) {
		if (root == null) return null;
		else return root.search(k);
	}
	
	public LinkedList<V> searchByRange(K kStart, K kEnd)
	{
		if (root == null) return null;
		else return root.searchByRange(kStart,kEnd);
	}

	@Override
	public V search(K k) {
		if (root == null)
		{
			return null;
		}else
		{
			AVLNode<K, V> aux = root.search(k);
			
			if (aux != null) return aux.getValue();
			else return null;
		}
		
	}

	@Override
	public boolean isEmpty() {
		return size==0;
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public boolean keyExists(K k) {
		if (root == null) return false;
		else
		{
			if (root.search(k) != null) return true;
		}
		return false;
	}

	public AVLNode<K, V> getRoot() {
		return root;
	}

	public void setRoot(AVLNode<K, V> root) {
		this.root = root;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}
	
	

	
	
	
	
}
