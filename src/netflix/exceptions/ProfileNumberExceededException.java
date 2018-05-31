package netflix.exceptions;
/**
 * An exception caused by an attempt to create a profile that exceeds the number allowed by the subscription plan.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
@SuppressWarnings("serial")
public class ProfileNumberExceededException extends RuntimeException {
	public ProfileNumberExceededException() {
		super();
	}
}
