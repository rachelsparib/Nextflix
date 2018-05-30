package netflix.exceptions;
/**
 * An exception raised by attempting to downgrade to a plan with an inferior number of devices than the ones owned.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
@SuppressWarnings("serial")
public class DowngradeMembershipException extends RuntimeException {
	public DowngradeMembershipException() {
		super();
	}
}
