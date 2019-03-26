package fr.ecp.IS1220.myVelib.core.exception;

public class NoBicycleAvailableException extends RuntimeException {

	/**
	 * Constructs a <code>NoBicycleAvailableException</code> with no
	 * detail message
	 */
	public NoBicycleAvailableException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoParkingSlotAvailableException</code>
	 * @param message		the detail message
	 * @param cause			the cause
	 * @param enableSuppression		the enable suppression boolean
	 * @param writableStackTrace	the writable stack tree boolean
	 */
	public NoBicycleAvailableException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoParkingSlotAvailableException</code>
	 * @param message	the detail message
	 * @param cause		the cause
	 */
	public NoBicycleAvailableException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoParkingSlotAvailableException</code>
	 * @param message	the detail message
	 */
	public NoBicycleAvailableException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>NoParkingSlotAvailableException</code>
	 * @param cause		the cause
	 */
	public NoBicycleAvailableException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Serial Version UID auto-generated
	 */
	private static final long serialVersionUID = 2200517216246360128L;

}
