package structs;

import exceptions.NotFoundNodeException;

public abstract class CollisionTree<K extends Comparable<K>,V> implements BSTInterface<K, V> {
	
	private LinkedList<K, V> root;

	@Override
	public boolean add(K k, V v) {
		if (root == null)
		{
			root = new LinkedList<>(k, v);
			balanceTree();
			return true;
		}else
		{
			boolean status = root.add(k, v);
			balanceTree();
			return status;
		}
		
	}

	@Override
	public V search(K k) {
		if (root == null)
		{
			return null;
		}else
		{
			return root.search(k).getFirst().getValue();
		}
		
	}
	
	public LinkedList<K, V> searchList(K k) {
		if (root == null)
		{
			return null;
		}else
		{
			return root.search(k);
		}
		
	}

	@Override
	public boolean remove(K k) throws NotFoundNodeException{
		if (root == null) return false;
		else
		{
			if (root.getKey().compareTo(k)==0)
			{
				LinkedList<K, V> aux = root.successor();
				if (aux.getParent().getLeft() == aux) aux.getParent().setLeft(null);
				else aux.getParent().setRight(null);
				
				aux.setLeft(root.getLeft());
				aux.getLeft().setParent(aux);
				
				aux.setRight(root.getRight());
				aux.getRight().setParent(aux);
				
				root = aux;
				balanceTree();
				return true;
			}else
			{
				LinkedList<K, V> aux = root.search(k);
				if(aux == null) throw new NotFoundNodeException();
				else
				{
					boolean status =  aux.remove();
					balanceTree();
					return status;
				}
			}
		}
		
	}

	public LinkedList<K, V> getRoot() {
		return root;
	}

	public void setRoot(LinkedList<K, V> root) {
		this.root = root;
	}
	
	public abstract void balanceTree();
	
	
	
}
