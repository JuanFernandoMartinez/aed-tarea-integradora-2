package structs;

public class RedBlackTree<K extends Comparable<K>,V> implements RedBlackInterface<K, V> {
	
	private RedBlackNode<K,V> nil  = new RedBlackNode<>();
	private RedBlackNode<K,V> root = nil;
	
	public RedBlackTree() {
		root.setLeft(nil);
		root.setParent(nil);
		root.setRight(nil);
	}
	
	

	@Override
	public void leftRotate(RedBlackNode<K, V> a) {
		
		leftRotateFixup(a);
		
		RedBlackNode<K,V> b;
		b = a.getRight();
		a.setRight(b.getLeft());
		
		
		if (isNil(a.getParent()))
			root = b;

		// x is the left child of it's parent
		else if (a.getParent().getLeft() == a)
			a.getParent().setLeft(b);

		// x is the right child of it's parent.
		else
			a.getParent().setRight(b);
			

		// Finish of the leftRotate
		b.setLeft(a);
		a.setParent(b);
		
		
	}

	
	
	@Override
	public void leftRotateFixup(RedBlackNode<K, V> a) {
		
		if (isNil(a.getLeft()) && isNil(a.getRight().getLeft())){
			
			a.setNumLeft(0);
			a.setNumRight(0);
			a.getRight().setNumLeft(1); 
		}
		
		else if (isNil(a.getLeft()) && !isNil(a.getRight().getLeft())){
			
			a.setNumLeft(0);
			a.setNumRight(1+a.getRight().getLeft().getNumLeft()+ a.getRight().getLeft().getNumRight());
			a.getRight().setNumLeft(2+a.getRight().getLeft().getNumLeft()+a.getRight().getLeft().getNumRight());
			
		}
		
		else if (!isNil(a.getLeft()) && isNil(a.getRight().getLeft())){
			
			a.setNumRight(0);
			a.getRight().setNumLeft(2 + a.getLeft().getNumLeft() + a.getLeft().getNumRight());
			
		}
		
		else{
			a.setNumRight(1 + a.getRight().getLeft().getNumLeft() + a.getRight().getLeft().getNumRight());
			a.getRight().setNumLeft(3 + a.getLeft().getNumLeft() + a.getLeft().getNumRight() + a.getRight().getLeft().getNumLeft() + a.getRight().getLeft().getNumRight());
			
		}
	}

	@Override
	public void rightRotate(RedBlackNode<K, V> a) {
		
		rightRotateFixup(a);
		
		RedBlackNode<K,V> x = a.getLeft();
		a.setLeft(x.getRight());
		
		if (!isNil(x.getRight()))
			a.getRight().setParent(a);
		
        x.setParent(a.getParent());    
        
        if (isNil(a.getParent()))
            root = x;
        
        else if (a.getParent().getRight() == a)
        	a.getParent().setRight(x);
            
        else
            a.getParent().setLeft(x);
        x.setRight(a);
        
        a.setParent(x);
       	
		
	}

	@Override
	public void rightRotateFixup(RedBlackNode<K, V> a) {
		
		if (isNil(a.getRight()) && isNil(a.getLeft().getRight())){
			a.setNumRight(0);
			a.setNumLeft(0);
			a.getLeft().setNumRight(1);
		}
		
		else if (isNil(a.getRight()) && !isNil(a.getLeft().getRight())){
			a.setNumRight(0);
			
			a.setNumLeft(1+a.getLeft().getRight().getNumRight() + a.getLeft().getRight().getNumLeft());
			
			a.getLeft().setNumRight(2+a.getLeft().getRight().getNumRight() + a.getLeft().getRight().getNumLeft());
		}
		
		else if (!isNil(a.getRight()) && isNil(a.getLeft().getRight())){
			
			a.setNumLeft(0);
			
			a.getLeft().setNumRight(2 + a.getRight().getNumRight() + a.getRight().getNumLeft());

		}
		
		else{
			
			
			a.setNumLeft(1+a.getLeft().getRight().getNumRight() + a.getLeft().getRight().getNumLeft());
			
			a.getLeft().setNumRight(3 + a.getRight().getNumRight() + a.getRight().getNumLeft() + a.getLeft().getRight().getNumRight() + a.getLeft().getRight().getNumLeft());
		}

		
	}

	@Override
	public void insert(K k, V v) {
		 insert(new RedBlackNode<K,V>(k,v));
		
	}

	private void insert(RedBlackNode<K, V> z) {
		
		RedBlackNode<K,V> y = nil;
		RedBlackNode<K,V> x = root;
		
		while (!isNil(x)){
			y = x;

			
			
			if (z.getKey().compareTo(x.getKey()) < 0){

				x.setNumLeft(x.getNumLeft()+1);
				
				x = x.getLeft();
			}

			
			else{

				x.setNumRight(x.getNumRight()+1);
			
				x = x.getRight();
			}
		}
		
		z.setParent(y);
		
		if (isNil(y))
			root = z;
		else if (z.getKey().compareTo(y.getKey()) < 0)
			y.setLeft(z);
		else
			y.setRight(z);
		
		z.setLeft(nil);
		z.setRight(nil);
		z.setColor(1);
		
		insertFixup(z);
	}



	@Override
	public void insertFixup(RedBlackNode<K, V> a) {
		
		RedBlackNode<K,V> y = nil;
		
		while (a.getParent().getColor() == 1){

			
			if (a.getParent() == a.getParent().getParent().getLeft()){

				
				y = a.getParent().getParent().getRight();

				
				
				if (y.getColor() == 1){
					a.getParent().setColor(0);
					y.setColor(0);
					a.getParent().getParent().setColor(1);
					a = a.getParent().getParent();
				}
				
				
				else if (a == a.getParent().getParent().getRight()){
					a = a.getParent();
					leftRotate(a);
				}

				
				else{
					a.getParent().setColor(0);
					a.getParent().getParent().setColor(1);
					rightRotate(a.getParent().getParent());
				}
			}

			
			else{

				y = a.getParent().getParent().getLeft();

				
				if (y.getColor() == 1){
					
					a.getParent().setColor(0);
					y.setColor(0);
					a.getParent().getParent().setColor(1);
					a = a.getParent().getParent();
				}

				
				else if (a == a.getParent().getLeft()){
					
					a = a.getParent();
					rightRotate(a);
				}
				
				else{
					
					
					
					a.getParent().setColor(0);
					
					a.getParent().getParent().setColor(1);
					leftRotate(a.getParent().getParent());
				}
			}
		}
		
		root.setColor(0);
		
	}
	

	@Override
	public boolean remove(K k) {
		RedBlackNode<K, V> a = search(k);
		if (a == null)
		{
			return false;
		}else
		{
			remove(a);
			return true;
		}
	}
	
	
	public void remove(RedBlackNode<K,V> v){

		RedBlackNode<K,V> z = search(v.getKey());

		
		RedBlackNode<K,V> x = nil;
		RedBlackNode<K,V> y = nil;

		
		if (isNil(z.getLeft()) || isNil(z.getRight()))
			y = z;

		
		else y = treeSuccessor(z);

		
		if (!isNil(y.getLeft()))
			x = y.getLeft();
		else
			x = y.getRight();

		
		x.setParent(y.getParent());

		
		if (isNil(y.getParent()))
			root = x;

		
		else if (!isNil(y.getParent().getLeft()) && y.getParent().getLeft() == y)
			y.getParent().setLeft(x);

		
		else if (!isNil(y.getParent().getRight()) && y.getParent().getRight() == y)
			y.getParent().setRight(x);

		
		if (y != z){
			z.setKey(y.getKey());
		}

		
		fixNodeData(x,y);

		if (y.getColor() == 0)
			removeFixup(x);
	}

	@Override
	public void removeFixup(RedBlackNode<K, V> a) {
		
		RedBlackNode<K,V> w;

		
		while (a != root && a.getColor() == 0){

			
			if (a == a.getParent().getLeft()){

				
				w = a.getParent().getRight();

				
				if (w.getColor() == 1){
					w.setColor(0);
					a.getParent().setColor(1);
					leftRotate(a.getParent());
					w = a.getParent().getRight();
				}

				
				if (w.getLeft().getColor() == 0 && w.getRight().getColor() == 0){
					w.setColor(1);
					a = a.getParent();
				}
				
				else{
					
					if (w.getRight().getColor() == 0){
						w.getLeft().setColor(0);
						w.setColor(1);
						rightRotate(w);
						w = a.getParent().getRight();
					}
					
					w.setColor(a.getParent().getColor());
					a.getParent().setColor(0);
					w.getRight().setColor(0);
					leftRotate(a.getParent());
					a = root;
				}
			}
			
			else{

				
				w = a.getParent().getLeft();

				
				if (w.getColor() == 1){
					w.setColor(0);
					a.getParent().setColor(1);
					rightRotate(a.getParent());
					w = a.getParent().getLeft();
				}

				
				if (w.getRight().getColor() == 0 && w.getLeft().getColor() == 0){
					w.setColor(1);
					a = a.getParent();
				}

				
				else{
					
					 if (w.getLeft().getColor() == 0){
						w.getRight().setColor(0);
						w.setColor(1);
						leftRotate(w);
						w = a.getParent().getLeft();
					}

					
					w.setColor(a.getParent().getColor());
					a.getParent().setColor(0);
					w.getLeft().setColor(0);
					rightRotate(a.getParent());
					a = root;
				}
			}
		}
		a.setColor(0);
		
	}

	@Override
	public void fixNodeData(RedBlackNode<K, V> a, RedBlackNode<K, V> b) {
		RedBlackNode<K,V> current = nil;
		RedBlackNode<K,V> track = nil;


		
		if (isNil(a)){
			current = b.getParent();
			track = b;
		}

		
		else{
			current = a.getParent();
			track = a;
		}

		
		while (!isNil(current)){
			
			if (b.getKey() != current.getKey()) {

				
				if (b.getKey().compareTo(current.getKey()) > 0)
					current.setNumRight(current.getNumRight()-1);

				
				if (b.getKey().compareTo(current.getKey()) < 0)
					current.setNumLeft(current.getNumLeft()-1);
			}

			
			else{
				
				if (isNil(current.getLeft()))
					current.setNumLeft(current.getNumLeft()-1);
				else if (isNil(current.getRight()))
					current.setNumRight(current.getNumRight()-1);

				
				else if (track == current.getRight())
					current.setNumRight(current.getNumRight()-1);
				else if (track == current.getLeft())
					current.setNumLeft(current.getNumLeft()-1);
			}

			
			track = current;
			current = current.getParent();

		}
		
	}

	@Override
	public RedBlackNode<K, V> search(K key) {
		RedBlackNode<K,V> current = root;

		// While we haven't reached the end of the tree
		while (!isNil(current)){

			// If we have found a node with a key equal to key
			if (current.getKey().equals(key))

				// return that node and exit search(int)
				return current;

			// go left or right based on value of current and key
			else if (current.getKey().compareTo(key) < 0)
				current = current.getRight();

			// go left or right based on value of current and key
			else
				current = current.getLeft();
		}

		// we have not found a node whose key is "key"
		return null;
		
	}
	
	public V searchValue(K key)
	{
		RedBlackNode<K, V> a = search(key);
		if (a == null)
		{
			return null;
		}else
		{
			return a.getValue();
		}
	}
	
	public boolean keyExists(K k)
	{
		if (search(k) == null)
		{
			return false;
		}else
		{
			return true;
		}
	}
	
	/*public boolean removeElement(K key)
	{
		RedBlackNode<K, V> a = search(key);
		if (a == null)
		{
			return false;
		}else
		{
			remove(key);
			return true;
		}
	}*/
	
	
	private boolean isNil(RedBlackNode<K,V> node){
		return node == nil;
	}
	
	
	public RedBlackNode<K,V> treeMinimum(RedBlackNode<K,V> node){
		
		while (!isNil(node.getLeft()))
			node = node.getLeft();
		return node;
	}
	
	public RedBlackNode<K,V> treeSuccessor(RedBlackNode<K,V> x){

		if (!isNil(x.getLeft()))
			return treeMinimum(x.getRight());

		RedBlackNode<K,V> y = x.getParent();

		
		while (!isNil(y) && x == y.getRight()){
			
			x = y;
			y = y.getParent();
		}
		
		return y;
	}
	
	public int numGreater(K key){

		
		return findNumGreater(root,key);

	}
	
	public int numSmaller(K key){

		
		return findNumSmaller(root,key);

	}
	
	
	
	public int findNumGreater(RedBlackNode<K,V> node, K key){

		
		if (isNil(node))
			return 0;
		
		else if (key.compareTo(node.getKey()) < 0)
            return 1+ node.getNumRight() + findNumGreater(node.getLeft(),key);

		
		else
			return findNumGreater(node.getRight(),key);

	}
	
	
	
	public int findNumSmaller(RedBlackNode<K,V> node, K key){

		
		if (isNil(node)) return 0;

		
		else if (key.compareTo(node.getKey()) <= 0)
			return findNumSmaller(node.getLeft(),key);

		
		else
			return 1+ node.getNumLeft() + findNumSmaller(node.getRight(),key);

	}
	
	
	public int size(){

		
		if (!isNil(root))
		{
			return root.getNumLeft()+root.getNumRight()+1;
		}else
		{
			return 0;
		}
	}



	public RedBlackNode<K, V> getRoot() {
		return root;
	}



	public void setRoot(RedBlackNode<K, V> root) {
		this.root = root;
	}
	
	

}
