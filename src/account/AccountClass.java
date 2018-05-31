package account;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

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
 * An implementation of a user account in the streaming service.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
public class AccountClass implements Account {
	/**
	 * Client's name.
	 */
	private String name;
	
	/**
	 * Client's email used as username.
	 */
	private String email;
	
	/**
	 * Account's password.
	 */
	private String password;
	
	/**
	 * Collection of account's access devices.
	 */
	List<String> devices;
	
	/**
	 * Collection of personalized profiles.
	 */
	List<Profile> profiles;
	
	/**
	 * Account's membership plan.
	 */
	MembershipPlanEnum plan;
	
	/**
	 * The device being used (or last used) in the streaming service.
	 */
	String activeDevice;
	
	/**
	 * The profile being used (or last used) in the streaming service.
	 */
	Profile activeProfile;
	
	/**
	 * Indicates if any profile is selected.
	 */
	boolean isProfileActive;
	
	/**
	 * Creates an account in the streaming service.
	 * @param name client's name.
	 * @param email account's username.
	 * @param password account's password.
	 * @param deviceID ID of the device used to access the service.
	 */
	public AccountClass(String name, String email, String password, String deviceID) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.plan = MembershipPlanEnum.BASIC;
		this.devices = new LinkedList<String>();		// more efficient in the removing
		this.addDevice(deviceID);
		this.activeDevice = deviceID;
		this.profiles = new ArrayList<Profile>(MembershipPlanEnum.BASIC.getMaxProfiles());
		this.isProfileActive = false;
	}
	
	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void addDevice(String deviceID) throws DeviceAlreadyExistsException, ExceededMaxNumberDevicesException {
		if(devices.contains(deviceID))
			throw new DeviceAlreadyExistsException();
		if(devices.size() == getPlan().getMaxDevices())
			throw new ExceededMaxNumberDevicesException();		
		devices.add(deviceID);
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void removeDevice(String deviceID) throws NoDevicesException {
		if(devices.isEmpty())
			throw new NoDevicesException();
		devices.remove(deviceID);
	}
	
	@Override
	public MembershipPlanEnum getPlan() {
		return plan;
	}
	
	@Override
	public void setPlan(MembershipPlanEnum plan) throws MembershipUnchangedException, DowngradeMembershipException{
		if(this.getPlan().getName().equals(plan.getName()))
			throw new MembershipUnchangedException();
		if(this.devices.size() > plan.getMaxDevices())
			throw new DowngradeMembershipException();
		this.plan = plan;
	}

	@Override
	public Iterator<String> listDevices() {
		return devices.iterator();
	}

	@Override
	public void setActiveDevice(String deviceID) {
		this.activeDevice = deviceID;
	}

	@Override
	public String getActiveDevice() {
		return activeDevice;
	}

	@Override
	public void addProfile(String name, int ageRate, boolean isKids)
			throws ProfileAlreadyExistException, ProfileNumberExceededException {
		for (Profile p : profiles) {
			if (p.getName().equalsIgnoreCase(name))
				throw new ProfileAlreadyExistException();
		}
		if (profiles.size() >= this.plan.getMaxProfiles())
			throw new ProfileNumberExceededException();
		Profile profile;
		if(isKids)
			profile = new ChildrenProfileClass(name, ageRate);
		else 
			profile = new AdultProfileClass(name);
		this.profiles.add(profile);		
	}

	@Override
	public void setActiveProfile(String profileName) throws ProfileDoesNotExistException {
		for (Profile p : profiles) {
			if (p.getName().equalsIgnoreCase(profileName)) {
				activeProfile = p;
				this.isProfileActive = true;
				return;
			}
		}
		throw new ProfileDoesNotExistException();
	}

	@Override
	public void logoutProfile() {
		this.isProfileActive = false;
	}

	@Override
	public boolean isProfileActive() {
		return this.isProfileActive;
	}

	@Override
	public void watchContent(Content cont) throws InapropriateContentException {
		if(activeProfile instanceof ChildrenProfile) {
			ChildrenProfile children = (ChildrenProfile)activeProfile;
			
			if (!children.isContentAppropriate(cont))
				throw new InapropriateContentException();
		}
		
		activeProfile.addToWatched(cont); 		// may throw an InapropriateContentException
	}

	@Override
	public void rateContent(String title, int rate) throws NotInRecentlyWatchedException, AlreadyRatedException {
		activeProfile.rate(title, rate);	// may throw NotInRecentlyWatchedException, AlreadyRatedException
	}

	@Override
	public Iterator<Profile> listProfiles() {
		return this.profiles.iterator();
	}
	
	@Override
	public Profile getActiveProfile() {
		return activeProfile;
	}
}
