package account;

import java.util.Iterator;

import content.Content;
import enums.MembershipPlanEnum;
import netflix.exceptions.AlreadyRatedException;
import netflix.exceptions.DeviceAlreadyExistsException;
import netflix.exceptions.DowngradeMembershipException;
import netflix.exceptions.ExceededMaxNumberDevicesException;
import netflix.exceptions.InapropriateContentException;
import netflix.exceptions.MembershipUnchangedException;
import netflix.exceptions.NoDevicesException;
import netflix.exceptions.NotInRecentlyWatchedException;
import netflix.exceptions.ProfileAlreadyExistException;
import netflix.exceptions.ProfileDoesNotExistException;
import netflix.exceptions.ProfileNumberExceededException;

/**
 * A user account in the streaming service.
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081 MIEI
 *
 */
public interface Account {
	/**
	 * Returns client's email.
	 * @return email of the client.
	 */
	public String getEmail();
	
	/**
	 * Returns account's password.
	 * @return password of the account.
	 */
	public String getPassword();
		
	/**
	 * Adds a device with ID <code>deviceID</code> to the account.
	 * @param deviceID device's ID.
	 * @throws DeviceAlreadyExistsException if the device to be added already belongs to the connected devices.
	 * @throws ExceededMaxNumberDevicesException adding the device would exceed the number limit imposed by the membership plan.
	 */
	void addDevice(String deviceID) throws DeviceAlreadyExistsException, ExceededMaxNumberDevicesException;
	
	/**
	 * Returns the name of the account's owner.
	 * @return name of the account's owner.
	 */
	String getName();
	
	/**
	 * Removes the last device used in the streaming service.
	 * @throws if there are no devices associated with this account.
	 */
	void removeDevice(String deviceID) throws NoDevicesException;
	
	/**
	 * Stores the device being used in the service.
	 * @param deviceID name of the device.
	 */
	void setActiveDevice(String deviceID);
	
	/**
	 * Returns the device being used (or last used) in the service.
	 * @return device being used or null if there's none. 
	 */
	public String getActiveDevice();
	
	/**
	 * Returns account's current membership plan.
	 * @return account's current membership plan.
	 */
	MembershipPlanEnum getPlan();
	
	/**
	 * Sets a different membership plan.
	 * @param plan new plan to be subscribed.
	 * @throws MembershipUnchangedException if the new plan is equal to the current one.
	 * @throws DowngradeMembershipException if the maximum number of devices allowed in the new plan
	 * 		 is inferior than the number of already connected ones.
	 */
	void setPlan(MembershipPlanEnum plan) throws MembershipUnchangedException, DowngradeMembershipException;
	
	/**
	 * Returns an iterator for the collection of the account's devices.
	 * @return an iterator for the collection of the account's devices.
	 */
	Iterator<String> listDevices();

	/**
	 * Adds a profile to the account.
	 * @param name name of the profile.
	 * @param ageRate the age rate of the profile.
	 * @param isKids <code>true</code> if its a profile for kids, false otherwise.
	 * @throws ProfileAlreadyExistException if there's already a profile with the same name
	 * @throws ProfileNumberExceededException if the number of allowed profiles would exceed the limit imposed 
	 * 		by the subscription plan.
	 */
	void addProfile(String name, int ageRate, boolean isKids) throws ProfileAlreadyExistException, ProfileNumberExceededException;
	
	/**
	 * Sets the profile to the profile with name <code>profileName</code>.
	 * @param profileName profile's name.
	 * @throws ProfileDoesNotExistException if its attempted to change to a non-existent profile.
	 */
	void setActiveProfile(String profileName) throws ProfileDoesNotExistException;
	
	/**
	 * Exits from a profile.
	 */
	void logoutProfile();
	
	/**
	 * Verifies if any profile is active.
	 * @return <code>true</code> if a profile is active, <code>false</code> otherwise.
	 */
	boolean isProfileActive();
	
	Profile getActiveProfile();
	
	/**
	 * Watches a content of the streaming service.
	 * @param cont a content of the streaming service.
	 * @throws InapropriateContentException if the content to watch is inappropriate to the profile in use.
	 */
	void watchContent(Content cont) throws InapropriateContentException;
	
	/**
	 * Rates a recently watched content.
	 * @param title content's title.
	 * @param rating rating given to a content with title <code>title</code>.
	 * @throws NotInRecentlyWatchedException if the content isn't in the collection of recently watched content.
	 * @throws AlreadyRatedException if the content was already rated.
	 */
	void rateContent(String title, int rating) throws NotInRecentlyWatchedException, AlreadyRatedException;
	
	/**
	 * Returns an iterator for the collection of profiles.
	 * @return an iterator for the collection of profiles.
	 */
	Iterator<Profile> listProfiles();
	
	/**
	 * Returns information of an account.
	 * @return information of an account.
	 */
	String toString();
}
