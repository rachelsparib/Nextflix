package account;

import content.Content;

/**
 * A customizable account's profile for children.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
public interface ChildrenProfile extends Profile {
	/**
	 * Returns the age rate of the profile.
	 * @return profile's age rate.
	 */
	int getAgeRate();
	
	/**
	 * Verifies if the content to be watched has an age rate inferior to the one of the profile.
	 * @param cont content to be watched.
	 * @return <code>true</code> if the content to be watched is appropriate to the profile, or <code>false</code> otherwise.
	 */
	boolean isContentAppropriate(Content cont);
}
