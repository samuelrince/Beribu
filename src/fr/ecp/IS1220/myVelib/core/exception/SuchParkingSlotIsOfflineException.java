package fr.ecp.IS1220.myVelib.core.exception;

/**
 * This Exception occurs when a parking slot is offline.
 * @author Samuel
 *
 */
public class SuchParkingSlotIsOfflineException extends RuntimeException {
	
	/**
	 * Constructs a <code>SuchParkingSlotIsOfflineException</code> with no
	 * detail message
	 */
	public SuchParkingSlotIsOfflineException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs a <code>SuchParkingSlotIsOfflineException</code>
	 * @param message	the detail message
	 * @param cause		the cause
	 * @param enableSuppression		the enable suppression boolean
	 * @param writableStackTrace	the writable stack tree boolean
	 */
	public SuchParkingSlotIsOfflineException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>SuchParkingSlotIsOfflineException</code>
	 * @param message 	the detail message
	 * @param cause		the cause
	 */
	public SuchParkingSlotIsOfflineException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>SuchParkingSlotIsOfflineException</code>
	 * @param message	the detail message
	 */
	public SuchParkingSlotIsOfflineException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>SuchParkingSlotIsOfflineException</code>
	 * @param cause		the cause
	 */
	public SuchParkingSlotIsOfflineException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Serial Version UID auto-generated
	 */
	private static final long serialVersionUID = -3513123993512185074L;

}
