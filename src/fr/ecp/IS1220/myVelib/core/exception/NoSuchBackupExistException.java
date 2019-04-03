package fr.ecp.IS1220.myVelib.core.exception;

/**
 * This Exception occurs when the backup file does not exist.
 * @author Samuel
 *
 */
public class NoSuchBackupExistException extends RuntimeException {

	/**
	 * Constructs a <code>NoSuchBackupExistException</code> with no
	 * detail message
	 */
	public NoSuchBackupExistException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoSuchBackupExistException</code>
	 * @param message	the detail message
	 * @param cause		the cause
	 * @param enableSuppression		the enable suppression boolean
	 * @param writableStackTrace	the writable stack tree boolean
	 */
	public NoSuchBackupExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoSuchBackupExistException</code>
	 * @param message	the detail message
	 * @param cause		the cause
	 */
	public NoSuchBackupExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoSuchBackupExistException</code>
	 * @param message	the detail message
	 */
	public NoSuchBackupExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs a <code>NoSuchBackupExistException</code>
	 * @param cause		the cause
	 */
	public NoSuchBackupExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Serial Version UID auto-generated
	 */
	private static final long serialVersionUID = 3655832203397798625L;

}
