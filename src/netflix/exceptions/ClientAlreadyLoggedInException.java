package netflix.exceptions;
/**
 * An exception raised by the client being already logged in the service.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
@SuppressWarnings("serial")
public class ClientAlreadyLoggedInException extends RuntimeException {
	public ClientAlreadyLoggedInException() {
		super();
	}
}
