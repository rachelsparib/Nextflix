package netflix.exceptions;
/**
 * An exception raised by an equal account in the system.
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081 MIEI
 *
 */
@SuppressWarnings("serial")
public class ExistentAccountException extends RuntimeException {
	public ExistentAccountException() {
		super();
	}
}
