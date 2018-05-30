package account;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import enums.MembershipPlanEnum;
import netflix.exceptions.DeviceAlreadyExistsException;
import netflix.exceptions.DowngradeMembershipException;
import netflix.exceptions.ExceededMaxNumberDevicesException;
import netflix.exceptions.MembershipUnchangedException;
import netflix.exceptions.NoDevicesException;
import netflix.exceptions.ProfileAlreadyExistException;
import netflix.exceptions.ProfileDoesNotExistException;
import netflix.exceptions.ProfileNumberExccededException;

/**
 * An implementation of a user account in the streaming service.
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081 MIEI
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
	
	Profile activeProfile;
	
	/**
	 * Account's membership plan.
	 */
	MembershipPlanEnum plan;
	
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
		this.devices = new LinkedList<String>();
		this.addDevice(deviceID);
		this.profiles = new ArrayList<Profile>(MembershipPlanEnum.BASIC.getMaxProfiles());
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
		((LinkedList<String>) devices).removeLast();		
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
	public void addProfile(String name) throws ProfileAlreadyExistException, ProfileNumberExccededException {
		for (Profile p : profiles) {
			if (p.getName().equalsIgnoreCase(name))
				throw new ProfileAlreadyExistException();
		}
		
		if (profiles.size() >= this.plan.getMaxProfiles())
			throw new ProfileNumberExccededException();
		
		Profile profile = new AdultProfileClass(name);
		this.profiles.add(profile);
	}

	@Override
	public void addChildrenProfile(String name, int ageRate) throws ProfileAlreadyExistException, ProfileNumberExccededException {
		for (Profile p : profiles) {
			if (p.getName().equalsIgnoreCase(name))
				throw new ProfileAlreadyExistException();
		}
		
		if (profiles.size() >= this.plan.getMaxProfiles())
			throw new ProfileNumberExccededException();
		
		Profile profile = new ChildrenProfileClass(name, ageRate);
		this.profiles.add(profile);
	}
	
	@Override
	public void setActiveProfile(String profileName) throws ProfileDoesNotExistException {
		for (Profile p : profiles) {
			if (p.getName().equalsIgnoreCase(profileName)) {
				activeProfile = p;
				return;
			}
		}
		
		throw new ProfileDoesNotExistException();
	}
	
}
