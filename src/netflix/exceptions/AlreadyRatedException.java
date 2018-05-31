package netflix.exceptions;
/**
 * An exception created by an attempt to rate a content already rated.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
@SuppressWarnings("serial")
public class AlreadyRatedException extends RuntimeException {
	public AlreadyRatedException() {
		super();
	}
}
