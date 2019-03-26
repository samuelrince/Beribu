package fr.ecp.IS1220.myVelib.core.exception;

public class NoParkingSlotAvailableException extends RuntimeException {

	/**
	 * Constructs a <code>NoParkingSlotAvailableException</code> with no
	 * detail message
	 */
	public NoParkingSlotAvailableException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoParkingSlotAvailableException</code>
	 * @param message		the detail message
	 * @param cause			the cause
	 * @param enableSuppression		the enable suppression boolean
	 * @param writableStackTrace	the writable stack trace boolean
	 */
	public NoParkingSlotAvailableException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoParkingSlotAvailableException</code>
	 * @param message	the detail message
	 * @param cause		the cause
	 */
	public NoParkingSlotAvailableException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoParkingSlotAvailableException</code>
	 * @param message	the detail message
	 */
	public NoParkingSlotAvailableException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoParkingSlotAvailableException</code>
	 * @param cause		the cause
	 */
	public NoParkingSlotAvailableException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Serial Version UID auto-generated
	 */
	private static final long serialVersionUID = -4616702796005767531L;

}
