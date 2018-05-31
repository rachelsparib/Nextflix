package enums;
/**
 * An enumerate type for the existing membership plans.
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081
 *
 */
public enum MembershipPlanEnum {
	/**
	 * Enumerate constants and its values (number of devices and profiles allowed).
	 */
	BASIC		("Basic", 1, 2),
	STANDARD	("Standard", 2, 5),
	PREMIUM		("Premium", 4, 5);
	
	/**
	 * The name of the plan.
	 */
	private String name;
	/**
	 * The maximum number of devices allowed to be associated with an account.
	 */
	private int deviceMax;
	
	/**
	 * The maximum number of personalized profiles allowed to be associated with an account.
	 */
	private int profileMax;
	
	/**
	 * Creates a membership plan with a maximum number of devices and profiles.
	 * @param name of the plan.
	 * @param devices maximum number of devices.
	 * @param profiles maximum number of profiles.
	 */
	private MembershipPlanEnum(String name, int devices, int profiles) {
		this.name = name;
		this.deviceMax = devices;
		this.profileMax = profiles;
	}
	
	/**
	 * Returns the maximum number of devices allowed to be associated with an account.
	 * @return maximum number of devices.
	 */
	public int getMaxDevices() {
		return this.deviceMax;
	}
	
	/**
	 * Returns the maximum number of personalized profiles allowed to be associated with an account.
	 * @return maximum number of personalized profiles.
	 */
	public int getMaxProfiles() {
		return this.profileMax;
	}
	
	/**
	 * Returns membership plan name.
	 * @return membership plan name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * Returns membership plan type if it matches <code>name</code>  or null otherwise.
	 * @param name name of the plan.
	 * @return membership plan type if it matches <code>name</code>  or null otherwise.
	 */
	public static MembershipPlanEnum getValue(String name) {
		for(MembershipPlanEnum p : MembershipPlanEnum.values())
			if(p.getName().equalsIgnoreCase(name))
				return p;
		return null;
	}
	
	/**
	 * Returns information of the membership plan.
	 */
	public String toString() {
		return name;
	}
}
