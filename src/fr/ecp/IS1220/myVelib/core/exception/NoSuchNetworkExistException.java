package fr.ecp.IS1220.myVelib.core.exception;

/**
 * This Exception occurs when the network does not exist.
 * @author Samuel
 *
 */
public class NoSuchNetworkExistException extends RuntimeException {
	
	/**
	 * Constructs a <code>NoSuchNetworkExistException</code> with no
	 * detail message
	 */
	public NoSuchNetworkExistException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs a <code>NoSuchNetworkExistException</code>
	 * @param message	the detail message
	 * @param cause		the detail cause
	 * @param enableSuppression		the enable suppression
	 * @param writableStackTrace	the writable stack tree
	 */
	public NoSuchNetworkExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoSuchNetworkExistException</code>
	 * @param message	the detail message
	 * @param cause		the detail cause
	 */
	public NoSuchNetworkExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs a <code>NoSuchNetworkExistException</code>
	 * @param message	the detail message
	 */
	public NoSuchNetworkExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoSuchNetworkExistException</code>
	 * @param cause 	the detail cause
	 */
	public NoSuchNetworkExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Serial Version UID auto-generated
	 */
	private static final long serialVersionUID = 4911208782409362666L;

}
