package fr.ecp.IS1220.myVelib.core.exception;

public class BadSortPolicyException extends IllegalArgumentException {

	/**
	 * Constructs a <code>BadSortPolicyException</code> with no
     * detail message.
	 */
	public BadSortPolicyException() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs a <code>BadSortPolicyException</code>
	 * @param message	the detail message
	 * @param cause		the cause
	 */
	public BadSortPolicyException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>BadSortPolicyException</code>
	 * @param s		the detail message
	 */
	public BadSortPolicyException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructs a <code>BadSortPolicyException</code>
	 * @param cause		the cause
	 */
	public BadSortPolicyException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Serial Version UID auto-generated
	 */
	private static final long serialVersionUID = 8038711527057393098L;

}
