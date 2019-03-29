package fr.ecp.IS1220.myVelib.core.exception;

/**
 * This Exception occurs when a date is not valid.
 * Can also occurs when SystemDate is set to an anterior date
 * than the current instance
 * @author Samuel
 *
 */
public class BadDateException extends IllegalArgumentException {
	
	/**
	 * Constructs a <code>BadDateException</code> with no
     * detail message.
	 */
	public BadDateException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>BadDateException</code>
	 * @param message	the detail message
	 * @param cause		the detail cause
	 */
	public BadDateException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs a <code>BadDateException</code>
	 * @param s		the detail message
	 */
	public BadDateException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>BadDateException</code>
	 * @param cause		the detail cause
	 */
	public BadDateException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Serial Version UID auto-generated
	 */
	private static final long serialVersionUID = -4525430219191488927L;

}
