package structs;

public class AVLNode<K extends Comparable<K>,V> {
	private K key;
	private V value;
	private int weight;
	private int lWeight;
	private int rWeight;
	private int balanceFactor;
	private AVL<K,V> owner;
	
	private AVLNode<K,V> left;
	private AVLNode<K,V> right;
	private AVLNode<K, V> parent;
	
	public AVLNode(K k,V v,AVL<K,V> owner) {
		key = k;
		value = v;
		
		weight = 1;
		lWeight = 0;
		rWeight = 0;
		balanceFactor = 0;
		this.owner = owner;
	}
	
	public boolean add(K k,V v)
	{
		if (key.compareTo(k) < 0)
		{
			if (right == null) 
			{
				 right = new AVLNode<K,V>(k, v, null);
				
				right.setParent(this);
				if (right.weight+1 > weight ) weight = right.weight+1;
				rWeight = right.weight;
				return true;
			}else
			{
				boolean status =  right.add(k, v);
				if (right.weight+1 > weight ) weight = right.weight+1;
				rWeight = right.weight;
				return status;
			}
		}else
		{
			if (left == null)
			{
				
				left = new AVLNode<K,V>(k,v,null);
				left.setParent(this);
				if (left.weight+1 > weight) weight = left.weight+1;
				lWeight = left.weight;
				return true;
			}else
			{
				boolean status = left.add(k, v);
				if (left.weight+1 > weight) weight = left.weight+1;
				lWeight = left.weight;
				return status;
			}
		}
	}
	
	
	public boolean remove(K k)
	{
		if (key.compareTo(k) > 0)
		{
			if (left != null)
			{
				if (left.key.equals(k))
				{
					boolean status = removeLeft();
					if (left != null)lWeight = left.weight;
					else lWeight = 0;
					
					if (lWeight+1 > rWeight+1) weight = lWeight+1;
					else weight = rWeight+1;
					return status;
				}
				else
				{
					boolean status = left.remove(k);
					if (left != null)lWeight = left.weight;
					else lWeight = 0;
					if (lWeight+1 > rWeight+1) weight = lWeight+1;
					else weight = rWeight+1;
					return status;
						
				}
			}else
			{
				return false;
			}
		}else if (key.compareTo(k)< 0)
		{
			
			if (right != null)
			{
				if (right.key.equals(k))
				{
					boolean status = removeRight();
					if (right != null) rWeight = right.weight;
					else rWeight = 0;
					
					if (lWeight+1 > rWeight+1) weight = lWeight+1;
					else weight = rWeight+1;
					return status;
				}
				else
				{
					boolean status = right.remove(k);
					if (right != null) rWeight = right.weight;
					
					if (lWeight+1 > rWeight+1) weight = lWeight+1;
					else weight = rWeight+1;
					return status;
				}
			}else
			{
				return false;
			}
		}
		return false;
	}
	
	
	public AVLNode<K,V> getSuccesor()
	{
		if (right != null)
		{
			AVLNode<K, V>  a = right.catchMinChild();
			if (left == null) lWeight = 0;
			else lWeight = left.weight;
			if (right == null) rWeight = 0;
			else rWeight = right.weight;
			
			if (lWeight+1 > rWeight+1) weight = lWeight+1;
			else weight = rWeight+1;
			
			return a;
			
		}else
		{
			return left;
		}
	}
	
	private boolean removeLeft()
	{
		if (left.right == null && left.left == null)
		{
			left = null;
			lWeight = 0;
			
			if (lWeight+1 > rWeight+1) weight = lWeight+1;
			else weight = rWeight+1;
		}else
		{
			AVLNode<K,V> aux = left.getSuccesor();
			if (aux.parent.left == aux) parent.left = null;
			else aux.parent.right = null;
			
			aux.left = left.left;
			if (left.left != null)left.left.parent = aux;
			
			aux.right = left.right;
			if (left.right != null) left.right.parent = aux;
			
			aux.parent = this;
			left = aux;
			
			lWeight = left.weight;
			
			if (lWeight+1 > rWeight+1) weight = lWeight+1;
			else weight = rWeight+1;
		}
		
		return true;
		
	}
	
	public boolean removeRight()
	{
		if (right.right == null && right.left == null)
		{
			right = null;
			rWeight = 0;
			
			if (lWeight+1 > rWeight+1) weight = lWeight+1;
			else weight = rWeight+1;
			
		}else
		{
			AVLNode<K,V> aux = right.getSuccesor();
			if (aux.parent.left == aux) parent.left = null;
			else aux.parent.right = null;
			
			aux.left = right.left;
			if (right.left != null) right.left.parent = aux;
			
			aux.right = right.right;
			if (right.right != null) right.right.parent = aux;
			
			aux.parent = this;
			right = aux;
			
			rWeight = right.weight;
			
			if (lWeight+1 > rWeight+1) weight = lWeight+1;
			else weight = rWeight+1;
		}
		return true;
	}
	
	public AVLNode<K,V> getMinChild()
	{
		if (left == null) return this;
		else return left.getMinChild();
	}
	
	public AVLNode<K,V> catchMinChild()
	{
		if (left == null)
		{
			AVLNode<K,V> a = this;
			if (parent.left == this) 
			{
				if (right != null) 
				{
					parent.left = right;
					right = null;
				}
				else parent.left = null;
			}
			else
			{
				if (right != null)
				{
					parent.right = right;
					right = null;
				}else
				{
					parent.right = right;
				}
			}
			
			return a;
		}else
		{
			AVLNode<K,V> a =  catchMinChild();
			if (left == null) lWeight = 0;
			else lWeight = left.weight;
			if (right == null) rWeight = 0;
			else rWeight = right.weight;
			
			if (lWeight+1 > rWeight+1) weight = lWeight+1;
			else weight = rWeight+1;
			
			return a;
		}
	}
	
	public AVLNode<K,V> search(K k)
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
	
	public void leftRotate()
	{
		AVLNode<K, V> aux = right;
		right = null;
		rWeight = 0;
		
		if (aux.left != null)
		{
			right = aux.left;
			aux.left.setParent(this);
			aux.left = null;
			rWeight = right.weight;
		}
		
		aux.left = this;
		
		if (owner != null)
		{
			parent = aux;
			owner.setRoot(aux);
			aux.owner = owner;
			owner = null;
		}else
		{
			if (parent.left == this)
			{
				parent.left = aux;
				aux.parent = parent;
			}else
			{
				parent.right = aux;
				aux.parent = parent;
			}
		}
		
		parent = aux;
		
		if (lWeight+1 > rWeight+1) weight = lWeight+1;
		else weight = rWeight+1;
		
		aux.lWeight = weight;
		
		if (aux.lWeight> aux.rWeight) aux.weight = aux.lWeight+1;
		else aux.weight = aux.rWeight+1;
	}
	
	
	public void rightRotate()
	{
		AVLNode<K, V> aux = left;
		left = null;
		lWeight = 0;
		if (aux.right != null)
		{
			left = aux.right;
			aux.right.parent = this;
			aux.right = null;
			lWeight = left.weight;
		}
		
		aux.right = this;
		if (owner != null)
		{
			owner.setRoot(aux);
			aux.parent = null;
			aux.owner = owner;
			owner = null;
			
		}else
		{
			if (parent.left == this)
			{
				parent.left = aux;
				aux.parent = parent;
			}
			else
			{
				parent.right = aux;
				aux.parent = parent;
			}
		}
		
		parent = aux;
		
		if (lWeight+1 > rWeight+1) weight = lWeight+1;
		else weight = rWeight+1;
		
		aux.rWeight = weight;
		
		if (aux.lWeight> aux.rWeight) aux.weight = aux.lWeight+1;
		else aux.weight = aux.rWeight+1;
	}
	
	
	
	public void calculateBalanceFactor()
	{
		balanceFactor = rWeight-lWeight;
	}
	
	

	public boolean verifySpecialCase(int n)
	{
		switch (n)
		{
		case 1: 
			if (left.left == null && left.right != null)
			{
				return true;
			}else
			{
				return false;
			}
			
		case 2: 
			if (right.right == null && right.left != null)
			{
				return true;
			}else
			{
				return false;
			}
			default: return true;
		}
		
		
	}
	
	
	public boolean balance()
	{
		
		if (isBalanced() )
		{
			return true;
		}else
		{
			if(left != null && !left.isBalanced())
			{
				left.balance();
			}
			if (right != null && !right.isBalanced())
			{
				right.balance();
			}
			
			if (left != null) lWeight = left.weight;
			else lWeight = 0;
			
			if (right != null) rWeight = right.weight;
			else rWeight = 0;
			
			if (lWeight+1 > rWeight+1) weight = lWeight+1;
			else weight = rWeight+1;
			
			if (isBalanced())
			{
				return true;
			}else
			{
				if (balanceFactor > 1)
				{
					if (verifySpecialCase(2))
					{
						right.rightRotate();
						leftRotate();
					}else
					{
						leftRotate();
					}
				}else
				{
					if (verifySpecialCase(1))
					{
						left.leftRotate();
						rightRotate();
					}else
					{
						rightRotate();
					}
				}
				
				return true;
			}
			
			
			
			
			
		}
		
	}
	
	
	public boolean isBalanced()
	{
		calculateBalanceFactor();
		if (balanceFactor < -1 || balanceFactor > 1)
		{
			return false;
		}else
		{
			return true;
		}
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getlWeight() {
		return lWeight;
	}

	public void setlWeight(int lWeight) {
		this.lWeight = lWeight;
	}

	public int getrWeight() {
		return rWeight;
	}

	public void setrWeight(int rWeight) {
		this.rWeight = rWeight;
	}

	public int getBalanceFactor() {
		return balanceFactor;
	}

	public void setValanceFactor(int balanceFactor) {
		this.balanceFactor = balanceFactor;
	}

	public AVL<K, V> getOwner() {
		return owner;
	}

	public void setOwner(AVL<K, V> owner) {
		this.owner = owner;
	}

	public AVLNode<K, V> getLeft() {
		return left;
	}

	public void setLeft(AVLNode<K, V> left) {
		this.left = left;
	}

	public AVLNode<K, V> getRight() {
		return right;
	}

	public void setRight(AVLNode<K, V> right) {
		this.right = right;
	}

	public AVLNode<K, V> getParent() {
		return parent;
	}

	public void setParent(AVLNode<K, V> parent) {
		this.parent = parent;
	}
	
	
	

}
