package netflix.exceptions;
/**
 * An exception raised by a failed login attempt caused by a wrong password.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
@SuppressWarnings("serial")
public class WrongPasswordException extends RuntimeException {
	public WrongPasswordException(){
		super();
	}
}
