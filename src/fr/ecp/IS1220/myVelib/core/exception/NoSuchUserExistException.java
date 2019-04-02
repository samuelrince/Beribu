package fr.ecp.IS1220.myVelib.core.exception;

/**
 * This Exception occurs when the user does not exist.
 * @author Samuel
 *
 */
public class NoSuchUserExistException extends RuntimeException {

	/**
	 * Constructs a <code>NoSuchUserExistException</code> with no
	 * detail message
	 */
	public NoSuchUserExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoSuchUserExistException</code>
	 * @param message	the detail message
	 * @param cause		the cause
	 * @param enableSuppression	the enable suppression boolean
	 * @param writableStackTrace	the writable stack tree boolean
	 */
	public NoSuchUserExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoSuchUserExistException</code>
	 * @param message	the detail message
	 * @param cause		the cause
	 */
	public NoSuchUserExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoSuchUserExistException</code>
	 * @param message	the detail message
	 */
	public NoSuchUserExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoSuchUserExistException</code>
	 * @param cause the cause
	 */
	public NoSuchUserExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Serial Version UID auto-generated
	 */
	private static final long serialVersionUID = -5769446111382187745L;

}
