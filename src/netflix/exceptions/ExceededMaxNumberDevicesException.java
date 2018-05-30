package netflix.exceptions;
/**
 * An exception raised by an exceeding number of connected devices.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
@SuppressWarnings("serial")
public class ExceededMaxNumberDevicesException extends RuntimeException {
	public ExceededMaxNumberDevicesException() {
		super();
	}
}
