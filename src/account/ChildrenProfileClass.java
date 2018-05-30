package account;
/**
 * An implementation of a customizable account's profile for children.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
public class ChildrenProfileClass extends AbstractProfileClass implements ChildrenProfile {

	private int ageRate;
	
	public ChildrenProfileClass(String name, int ageRate) {
		super(name);

		this.ageRate = ageRate;
	}

	@Override
	public int getAgeRate() {
		return ageRate;
	}

}
