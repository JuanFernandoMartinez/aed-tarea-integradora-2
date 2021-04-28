package structs;

public class RedBlackNode<K extends Comparable<K>,V> {
	private static final int BLACK = 0;
	private static final int RED = 1;
	
	private K key;
	private V value;
	private int color;
	
	private RedBlackNode<K, V> parent;
	private RedBlackNode<K, V> left;
	private RedBlackNode<K, V> right;
	
	private int numLeft;
	private int numRight;
	
	RedBlackNode(){
        color = BLACK;
        numLeft = 0;
        numRight = 0;
        parent = null;
        left = null;
        right = null;
    }
	
	RedBlackNode(K key,V value){
        this();
        this.key = key;
        this.value = value;
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

	public int getColor() {
		return color;
	}

	public void setColor(int color) {
		this.color = color;
	}

	public RedBlackNode<K, V> getParent() {
		return parent;
	}

	public void setParent(RedBlackNode<K, V> parent) {
		this.parent = parent;
	}

	public RedBlackNode<K, V> getLeft() {
		return left;
	}

	public void setLeft(RedBlackNode<K, V> left) {
		this.left = left;
	}

	public RedBlackNode<K, V> getRight() {
		return right;
	}

	public void setRight(RedBlackNode<K, V> right) {
		this.right = right;
	}

	public int getNumLeft() {
		return numLeft;
	}

	public void setNumLeft(int numLeft) {
		this.numLeft = numLeft;
	}

	public int getNumRight() {
		return numRight;
	}

	public void setNumRight(int numRight) {
		this.numRight = numRight;
	}
	
	
	
	

}
