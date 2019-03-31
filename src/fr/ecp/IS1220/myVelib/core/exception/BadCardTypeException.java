package fr.ecp.IS1220.myVelib.core.exception;

/**
 * This Exception occurs when a wrong type of card is called
 * @author Samuel
 *
 */
public class BadCardTypeException extends IllegalArgumentException {

	/**
	 * Constructs a <code>BadCardTypeException</code> with no
     * detail message.
	 */
	public BadCardTypeException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs a <code>BadCardTypeException</code>
	 * @param message
	 * @param cause
	 */
	public BadCardTypeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>BadCardTypeException</code>
	 * @param s
	 */
	public BadCardTypeException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs a <code>BadCardTypeException</code>
	 * @param cause		the cause
	 */
	public BadCardTypeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Serial Version UID auto-generated
	 */
	private static final long serialVersionUID = -335725732495736034L;
}
