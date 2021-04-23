package structs;

import exceptions.NotFoundNodeException;
import exceptions.RepeatedElementException;

public abstract class NonCollisionTree<K extends Comparable<K>,V> implements BSTInterface<K, V> {

	private NodeTree<K, V> root;
	
	
	@Override
	public boolean add(K k, V v) throws RepeatedElementException {
		if (root == null)
		{
			root = new NodeTree<K, V>();
			return true;
		}else
		{
			return root.add(k, v);
		}
		
	}

	@Override
	public V search(K k) {
		if (root == null) return null;
		else return root.search(k);
		
	}

	@Override
	public boolean remove(K k) throws NotFoundNodeException{
		if (root == null) return false;
		else 
		{
			if (root.getKey().compareTo(k) == 0)
			{
				NodeTree<K, V> aux = root.successor();
				if (aux.getParent().getLeft() == aux) aux.getParent().setLeft(null);
				else aux.getParent().setRight(null);
				aux.setLeft(root.getLeft());
				aux.getLeft().setParent(aux);
				
				aux.setRight(root.getRight());
				aux.getRight().setParent(aux);
				
				root = aux;
				balanceTree();
				return true;
			}
			NodeTree<K,V> aux = root.searchNode(k);
			if (aux == null) throw new NotFoundNodeException();
			else
			{
				aux.remove();
				balanceTree();
				return true;
			}
		}
		
	}
	
	public abstract void balanceTree();

}
