package netflix.exceptions;
/**
 * Exception raised by not existing an account logged in.
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081 MIEI
 *
 */
@SuppressWarnings("serial")
public class NoClientLoggedInException extends RuntimeException {
	public NoClientLoggedInException() {
		super();
	}
}
