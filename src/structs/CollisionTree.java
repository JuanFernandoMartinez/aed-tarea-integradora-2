package structs;

public abstract class CollisionTree<K extends Comparable<K>,V> implements BSTInterface<K, V> {
	
	private LinkedList<K, V> root;

	@Override
	public boolean add(K k, V v) {
		if (root == null)
		{
			root = new LinkedList<>(k, v);
			return true;
		}else
		{
			return root.add(k, v);
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
	public boolean remove(K k) {
		LinkedList<K, V> aux = searchList(k);
		
		if (aux == null)
		{
			return false;
		}else
		{
			aux.remove();
			return true;
		}
		
	}
	
	
	
}
