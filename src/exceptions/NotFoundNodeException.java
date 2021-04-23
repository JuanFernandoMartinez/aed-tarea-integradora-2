package exceptions;

public class NotFoundNodeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotFoundNodeException() {
		super("Not found node on the Tree");
	}

}
