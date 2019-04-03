package fr.ecp.IS1220.myVelib.core.exception;

/**
 * This exception occurs when the name of a shape called is not valid.
 * @author Valentin
 *
 */
public class BadShapeException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6260110092420903653L;

	/**
	 * Constructs a <code>BadShapeException</code> with no
     * detail message.
	 */
	public BadShapeException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs a <code>BadShapeException</code>
	 * @param message
	 * @param cause
	 */
	public BadShapeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>BadShapeException</code>
	 * @param s
	 */
	public BadShapeException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs a <code>BadShapeException</code>
	 * @param cause		the cause
	 */
	public BadShapeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
