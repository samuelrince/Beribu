package fr.ecp.IS1220.myVelib.core.exception;

/**
 * This Exception occurs when a parking slot has a bicycle and cannot
 * receive a new one
 * @author Samuel
 *
 */
public class SuchParkingSlotHasBicycleException extends RuntimeException {

	/**
	 * Constructs a <code>SuchParkingSlotHasBicycleException</code> with no
	 * detail message
	 */
	public SuchParkingSlotHasBicycleException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>SuchParkingSlotHasBicycleException</code>
	 * @param message	the detail message
	 * @param cause		the cause
	 * @param enableSuppression		the enable suppression	
	 * @param writableStackTrace	the writable stack tree 
	 */
	public SuchParkingSlotHasBicycleException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>SuchParkingSlotHasBicycleException</code>
	 * @param message	the detail message
	 * @param cause		the cause
	 */
	public SuchParkingSlotHasBicycleException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>SuchParkingSlotHasBicycleException</code>
	 * @param message	the detail message
	 */
	public SuchParkingSlotHasBicycleException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>SuchParkingSlotHasBicycleException</code>
	 * @param cause		the cause
	 */
	public SuchParkingSlotHasBicycleException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Serial Version UID auto-generated
	 */
	private static final long serialVersionUID = 6871051394482150205L;

}
