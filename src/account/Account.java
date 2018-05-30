package account;

import java.util.Iterator;

import enums.MembershipPlanEnum;
import netflix.exceptions.DeviceAlreadyExistsException;
import netflix.exceptions.DeviceNotRegistedException;
import netflix.exceptions.DowngradeMembershipException;
import netflix.exceptions.ExceededMaxNumberDevicesException;
import netflix.exceptions.MembershipUnchangedException;
import netflix.exceptions.NoDevicesException;
import netflix.exceptions.ProfileAlreadyExistException;
import netflix.exceptions.ProfileDoesNotExistException;
import netflix.exceptions.ProfileNumberExccededException;

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
	 * @throws DeviceAlreadyExistsException if the device to be added already belongs to the connected devices,
	 *  ExceededMaxNumberDevicesException adding the device would exceed the number limit imposed by the membership plan.
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
	 * Returns account's current membership plan.
	 * @return account's current membership plan.
	 */
	MembershipPlanEnum getPlan();
	
	/**
	 * Sets a different membership plan.
	 * @param plan new plan to be subscribed.
	 * @throws MembershipUnchangedException if the new plan is equal to the current one,
	 * 	 DowngradeMembershipException if the maximum number of devices allowed in the new plan
	 * 		 is inferior than the number of already connected ones.
	 */
	void setPlan(MembershipPlanEnum plan) throws MembershipUnchangedException, DowngradeMembershipException;
	
	/**
	 * Returns an iterator for the collection of the account's devices.
	 * @return an iterator for the collection of the account's devices.
	 */
	Iterator<String> listDevices();
	
	/**
	 * @throws ProfileNumberExccededException 
	 * @throws ProfileAlreadyExistException 
	 * Adds an adult profile to the account.
	 * @param name
	 * @throws
	 */
	void addProfile(String name) throws ProfileAlreadyExistException, ProfileNumberExccededException;
	
	/**
	 * 
	 * @param name
	 * @param ageRate
	 * @throws ProfileAlreadyExistException 
	 * @throws ProfileNumberExccededException 
	 */
	void addChildrenProfile(String name, int ageRate) throws ProfileAlreadyExistException, ProfileNumberExccededException;
	
	public void setActiveProfile(String profileName) throws ProfileDoesNotExistException;
	
	/**
	 * Returns information of an account.
	 * @return information of an account.
	 */
	String toString();

	public void setActiveDevice(String deviceID) throws DeviceNotRegistedException;

	public String getActiveDevice();
}
