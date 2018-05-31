package netflix.exceptions;
/**
 * An exception caused by an attempt to access a non-existent profile.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
@SuppressWarnings("serial")
public class ProfileDoesNotExistException extends RuntimeException {
	public ProfileDoesNotExistException() {
		super();
	}
}
