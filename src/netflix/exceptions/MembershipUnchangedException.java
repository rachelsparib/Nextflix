package netflix.exceptions;
/**
 * An exception raised by attempting to change to the same membership plan.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
@SuppressWarnings("serial")
public class MembershipUnchangedException extends RuntimeException {
	public MembershipUnchangedException() {
		super();
	}
}
