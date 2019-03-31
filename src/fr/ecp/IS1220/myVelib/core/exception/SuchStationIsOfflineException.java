package fr.ecp.IS1220.myVelib.core.exception;

/**
 * This Exception occurs when a station is offline
 * @author Samuel
 *
 */
public class SuchStationIsOfflineException extends RuntimeException {

	/**
	 * Constructs a <code>SuchStationIsOfflineException</code> with no
	 * detail message
	 */
	public SuchStationIsOfflineException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs a <code>SuchStationIsOfflineException</code>
	 * @param message	the detail message
	 * @param cause		the cause
	 * @param enableSuppression		the enable suppression 
	 * @param writableStackTrace	the writable stack treee
	 */
	public SuchStationIsOfflineException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>SuchStationIsOfflineException</code>
	 * @param message	the detail message
	 * @param cause		the cause
	 */
	public SuchStationIsOfflineException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>SuchStationIsOfflineException</code>
	 * @param message	the detail message
	 */
	public SuchStationIsOfflineException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>SuchStationIsOfflineException</code>
	 * @param cause		the cause
	 */
	public SuchStationIsOfflineException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Serial Version UID auto-generated
	 */
	private static final long serialVersionUID = 5159304724896590116L;
	
}
