package structs;

public class CollisionAVL<K extends Comparable<K>,V> extends CollisionTree<K, V>{
	
	private int balanceFactor;

	public CollisionAVL() {
		super();
		this.balanceFactor = 0;
	}

	public void calculateBalanceFactor()
	{
		if (super.getRoot() == null) balanceFactor = 0;
		else
		{
			if (super.getRoot().getLeft() == null && super.getRoot().getRight() == null)
			{
				balanceFactor = 0;
			}else
			{
				int l,r;
				if (super.getRoot().getLeft() == null) l = 0;
				else l = super.getRoot().getLeft().calculateWeight();
				
				
				if (super.getRoot().getRight() == null) r = 0;
				else r = super.getRoot().getRight().calculateWeight();
				
				
				balanceFactor = r-l;
			}
		}
	}
	
	public int getBalanceFactor() {
		return balanceFactor;
	}

	public void setBalanceFactor(int balanceFactor) {
		this.balanceFactor = balanceFactor;
	}

	@Override
	public void balanceTree() {
		if (balanceFactor > 1 || balanceFactor < -1)
		{
			
		}
		
	}
	
	
	
	

}
