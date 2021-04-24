package structs;

import java.util.ArrayList;

public class Node<K extends Comparable<K>,V> {

	private Node<K,V> parent,left,right;
	private K key;
	private ArrayList<V> values;
	private int BalanceFactor;
	private boolean isAVL;
	
	public Node(K k, V v) {
		values = new ArrayList<>();
		key = k;
		values.add(v);
		BalanceFactor = 0;
		isAVL = false;
	}
	
	public Node(K k, V v, boolean a) {
		values = new ArrayList<>();
		key = k;
		values.add(v);
		BalanceFactor = 0;
		isAVL = a;
	}
	
	public int calculateBalance()
	{
		int l,r;
		
		if (left == null) l = 0;
		else l = left.calculateWeight();
		if (right == null) r = 0;
		else r = right.calculateWeight();
		
		
		
		return r-l;
		
	}
	
	public void leftRotate()
	{
		if (parent != null)
		{
			if (parent.left == this)
			{
				parent.setLeft(right);
				right.setParent(parent);
				
				Node<K,V> aux = right.left;
				
				right.setLeft(this);
				parent = right;
				
				right = aux;
				if (aux != null)aux.parent = this;
			}else
			{
				parent.right = right;
				right.setParent(parent);
				
				Node<K,V> aux = right.left;
				
				right.setLeft(this);
				parent = right;
				
				right = aux;
				if (aux != null)aux.parent = this;
			}
		}else
		{
			right.setParent(parent);
			
			Node<K,V> aux = right.left;
			
			right.setLeft(this);
			parent = right;
			
			right = aux;
			if (aux != null)aux.parent = this;

		}
		
	}
	
	
	public void rightRotate()
	{
		if (parent != null)
		{
			if (parent.left == this)
			{
				parent.left = left;
				left.setParent(parent);
				
				Node<K,V> aux = left.right;
				
				left.setRight(this);
				parent = right;
				
				right = aux;
				if (aux != null)aux.parent = this;
			}else
			{
				parent.right = left;
				left.setParent(parent);
				
				Node<K,V> aux = left.right;
				
				left.setRight(this);
				parent = right;
				
				right = aux;
				if (aux != null)aux.parent = this;
				
			}
		}else
		{
			left.setParent(parent);
			
			Node<K,V> aux = left.right;
			
			left.setRight(this);
			parent = right;
			
			right = aux;
			
			if (aux != null)aux.parent = this;
			
		}
		
	}
	
	public void balance()
	{
		if (calculateBalance() > 1)
		{
			if (right.right == null)
			{
				right.leftRotate();
				leftRotate();
			}else
			{
				leftRotate();
			}
		}else if (calculateBalance() < -1)
		{
			if (left.left == null)
			{
				left.rightRotate();
				rightRotate();
			}else 
			{
				rightRotate();
			}
		}
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
					if (isAVL)
					{
						right = new Node<K,V>(k,v,true);
					}else right = new Node<K,V>(k,v);
					
					right.setParent(this);
					if (isAVL) balance();
					return true;
				}else
				{
					boolean status =  right.add(k, v);
					if (status)if (isAVL) balance();
					return status;
				}
			}else
			{
				if (left == null)
				{
					if (isAVL) left = new Node<K,V>(k,v,true);
					else left = new Node<K,V>(k,v);
					left.setParent(this);
					if (isAVL) balance();
					return true;
				}else
				{
					boolean status = left.add(k, v);
					if(status) if (isAVL) balance();
					return status;
				}
			}
		}
	}
	
	
	public boolean remove(K k)
	{
		if (k.compareTo(key) == 0)
		{
			boolean status =  remove();
			if (status) if (isAVL) balance();
			return status;
		}else
		{
			if (key.compareTo(k) < 0)
			{
				if (right == null) return false;
				else {
					boolean status = right.remove(k);
					if (status)if (isAVL) balance();
					return status;
				}
			}else
			{
				if (left == null) return false;
				else {
					boolean status =  left.remove(k);
					if (status) if (isAVL) balance();
					return status;
				}
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


	public int getBalanceFactor() {
		return BalanceFactor;
	}


	public void setBalanceFactor(int balanceFactor) {
		BalanceFactor = balanceFactor;
	}
	
	
	
	
}
