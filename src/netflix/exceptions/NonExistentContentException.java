package netflix.exceptions;
/**
 * An exception caused by an attempt to watch a non-existent content in the streaming service.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
@SuppressWarnings("serial")
public class NonExistentContentException extends RuntimeException {
	public NonExistentContentException() {
		super();
	}
}
