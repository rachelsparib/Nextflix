package netflix.exceptions;
/**
 * An exception caused by an attempt to watch inappropriate content for an age rate.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
@SuppressWarnings("serial")
public class InapropriateContentException extends RuntimeException {
	public InapropriateContentException() {
		super();
	}
}
