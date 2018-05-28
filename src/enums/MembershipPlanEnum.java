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
	BASIC		(1, 2),
	STANDARD	(2, 5),
	PREMIUM		(4, 5);
	
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
	 * @param devices maximum number of devices.
	 * @param profiles maximum number of profiles.
	 */
	private MembershipPlanEnum(int devices, int profiles) {
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
}
