package fr.ecp.IS1220.myVelib.core.exception;

/**
 * This Exception occurs when the type of bicycle called does not exist.
 * @author Samuel
 *
 */
public class BadBicycleTypeException extends IllegalArgumentException {
	
	/**
	 * Constructs a <code>BadBicycleTypeException</code> with no
     * detail message.
	 */
	public BadBicycleTypeException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs a <code>BadBicycleTypeException</code>
	 * @param message	the detail message
	 * @param cause		the cause
	 */
	public BadBicycleTypeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>BadBicycleTypeException</code>
	 * @param message	the detail message
	 */
	public BadBicycleTypeException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>BadBicycleTypeException</code>
	 * @param cause		the cause
	 */
	public BadBicycleTypeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Serial Version UID auto-generated
	 */
	private static final long serialVersionUID = -1756481697158196395L;

}
