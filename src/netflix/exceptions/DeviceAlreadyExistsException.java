package netflix.exceptions;
/**
 * An exception for a device already existing in the collection of connected devices of an account.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
@SuppressWarnings("serial")
public class DeviceAlreadyExistsException extends RuntimeException {
	public DeviceAlreadyExistsException() {
		super();
	}
}
