package fr.ecp.IS1220.myVelib.core.exception;


/**
 * This exception occurs when a localization is not valid.
 * @author Valentin
 *
 */
public class BadLocalizationException extends IllegalArgumentException {

	/**
	 * Constructs a <code>BadLocalizationException</code> with no
	 * detail message
	 */
	public BadLocalizationException() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>BadLocalizationException</code>
	 * @param message	the detail message
	 * @param cause		the detail cause
	 */
	public BadLocalizationException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>BadLocalizationException</code>
	 * @param s	the detail message
	 */
	public BadLocalizationException(String s) {
		super(s);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Constructs a <code>BadLocalizationException</code>
	 * @param cause the detail cause
	 */
	public BadLocalizationException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3432639608022878929L;
}
