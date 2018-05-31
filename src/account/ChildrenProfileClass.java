package account;

import content.Content;

/**
 * An implementation of a customizable account's profile for children.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
public class ChildrenProfileClass extends AbstractProfileClass implements ChildrenProfile {
	
	/**
	 * Age rate of the profile.
	 */
	private int ageRate;
	
	/**
	 * Creates a profile for children.
	 * @param name name of the profile.
	 * @param ageRate age rate of the profile.
	 */
	public ChildrenProfileClass(String name, int ageRate) {
		super(name);
		this.ageRate = ageRate;
	}

	@Override
	public int getAgeRate() {
		return ageRate;
}

	@Override
	public boolean isContentAppropriate(Content cont) {
		return this.ageRate >= cont.getAgeRate();
	}
}
