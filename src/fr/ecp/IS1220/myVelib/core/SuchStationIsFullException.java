package fr.ecp.IS1220.myVelib.core;

/**
 * This Exception occurs when a station is full
 * @author samuel
 *
 */
public class SuchStationIsFullException extends RuntimeException {

	public SuchStationIsFullException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SuchStationIsFullException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public SuchStationIsFullException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SuchStationIsFullException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public SuchStationIsFullException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Serial Version UID auto-generated
	 */
	private static final long serialVersionUID = -2619602615502997521L;

}
