package netflix.exceptions;
/**
 * An exception caused by an attempt to login with a non-existent account.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
@SuppressWarnings("serial")
public class AccountDoesNotExistException extends RuntimeException {
	public AccountDoesNotExistException() {
		super();
	}
}
