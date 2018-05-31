package account;
/**
 * An implementation of a customizable account's profile for adults.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
public class AdultProfileClass extends AbstractProfileClass implements AdultProfile {
	/**
	 * Creates a profile for an adult.
	 * @param name name of the profile.
	 */
	public AdultProfileClass(String name) {
		super(name);
	}
}
