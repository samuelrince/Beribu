package fr.ecp.IS1220.myVelib.core.exception;

public class BadDurationException extends IllegalArgumentException {
	
	/**
	 * Constructs a <code>BadDurationException</code> with no
	 * detail message
	 */
	public BadDurationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>BadDurationException</code>
	 * @param message	the detail message
	 * @param cause		the detail cause
	 */
	public BadDurationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs a <code>BadDurationException</code>
	 * @param s	the detail message
	 */
	public BadDurationException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs a <code>BadDurationException</code>
	 * @param cause the detail cause
	 */
	public BadDurationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Serial Version UID auto-generated
	 */
	private static final long serialVersionUID = -5470598598472794843L;

}
