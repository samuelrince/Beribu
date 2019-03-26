package fr.ecp.IS1220.myVelib.core.exception;

public class NoNewRideException extends RuntimeException {

	/**
	 * Constructs a <code>NoNewRideException</code> with no
	 * detail message
	 */
	public NoNewRideException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoNewRideException</code>
	 * @param message	the detail message
	 * @param cause		the cause
	 * @param enableSuppression		the enable suppression boolean
	 * @param writableStackTrace	the writable stack trace boolean
	 */
	public NoNewRideException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoNewRideException</code>
	 * @param message	the detail message
	 * @param cause		the cause
	 */
	public NoNewRideException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoNewRideException</code>
	 * @param message	the detail message
	 */
	public NoNewRideException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoNewRideException</code>
	 * @param cause		the cause
	 */
	public NoNewRideException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Serial Version UID auto-generated
	 */
	private static final long serialVersionUID = -1639357728649642221L;

}
