package netflix.exceptions;

/**
 * An exception raised by an account already logged.
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081 MIEI
 *
 */
@SuppressWarnings("serial")
public class OccupiedServiceException extends RuntimeException {
	public OccupiedServiceException() {
		super();
	}
}
