package exceptions;

public class RepeatedElementException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RepeatedElementException() {
		super("Key is currently used in the tree");
	}

}
