package fr.ecp.IS1220.myVelib.core.exception;

public class NoSuchStationExistException extends RuntimeException {
	
	/**
	 * Constructs a <code>NoSuchStationExistException</code> with no
	 * detail message
	 */
	public NoSuchStationExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoSuchStationExistException</code>
	 * @param message	the detail message
	 * @param cause		the detail cause
	 * @param enableSuppression		the enable suppression boolean
	 * @param writableStackTrace	the writable stack tree boolean
	 */
	public NoSuchStationExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoSuchStationExistException</code>
	 * @param message	the detail message
	 * @param cause		the cause detail
	 */
	public NoSuchStationExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoSuchStationExistException</code>
	 * @param message	the detail message
	 */
	public NoSuchStationExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoSuchStationExistException</code>
	 * @param cause		the detail cause
	 */
	public NoSuchStationExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Serial Version UID auto-generated
	 */
	private static final long serialVersionUID = 6340439839875343841L;

}
