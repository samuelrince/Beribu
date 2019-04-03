package fr.ecp.IS1220.myVelib.core.exception;

/**
 * This exception occurs when the name of the path strategy called is not valid.
 * @author Valentin
 *
 */
public class BadPathStrategyException extends IllegalArgumentException {

	/**
	 * Constructs a <code>BadPathStrategyException</code> with no
     * detail message.
	 */
	public BadPathStrategyException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs a <code>BadPathStrategyException</code>
	 * @param message
	 * @param cause
	 */
	public BadPathStrategyException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>BadPathStrategyException</code>
	 * @param s
	 */
	public BadPathStrategyException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs a <code>BadPathStrategyException</code>
	 * @param cause		the cause
	 */
	public BadPathStrategyException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4366961143252548286L;
}
