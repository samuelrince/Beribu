package fr.ecp.IS1220.myVelib.core.exception;

/**
 * This exception occurs when user already exist in the network database
 * @author Samuel
 *
 */
public class SuchUserAlreadyExistException extends RuntimeException {

	/**
	 * Constructs a <code>SuchUserAlreadyExistException</code> with no
	 * detail message
	 */
	public SuchUserAlreadyExistException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs a <code>SuchUserAlreadyExistException</code>
	 * @param arg0	the detail message
	 * @param arg1	the cause
	 * @param arg2	the boolean 1
	 * @param arg3	the boolean 2
	 */
	public SuchUserAlreadyExistException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs a <code>SuchUserAlreadyExistException</code>
	 * @param message	the detail message
	 * @param cause		the cause
	 */
	public SuchUserAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>SuchUserAlreadyExistException</code>
	 * @param message	the detail message
	 */
	public SuchUserAlreadyExistException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>SuchUserAlreadyExistException</code>
	 * @param cause		the cause
	 */
	public SuchUserAlreadyExistException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Serial Version UID auto-generated
	 */
	private static final long serialVersionUID = -3810169490418535751L;

}
