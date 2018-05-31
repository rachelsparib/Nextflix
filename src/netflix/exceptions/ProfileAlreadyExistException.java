package netflix.exceptions;
/**
 * An exception caused by an attempt to add a profile with the same name.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
@SuppressWarnings("serial")
public class ProfileAlreadyExistException extends RuntimeException {
	public ProfileAlreadyExistException() {
		super();
	}
}
