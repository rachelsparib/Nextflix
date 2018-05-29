package account;

import java.util.ArrayList;
import java.util.List;

import enums.MembershipPlanEnum;

/**
 * An implementation of a user account in the streaming service.
 * 
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081 MIEI
 *
 */
public class AccountClass implements Account {

	private String name;

	private String email;

	private String password;

	List<String> devices;

	List<Profile> profiles;

	MembershipPlanEnum plan;

	public AccountClass(String name, String email, String password, String deviceID) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.plan = MembershipPlanEnum.BASIC;
		this.devices = new ArrayList<String>(MembershipPlanEnum.BASIC.getMaxDevices());
		this.profiles = new ArrayList<Profile>(MembershipPlanEnum.BASIC.getMaxProfiles());
	}

	@Override
	public String getEmail() {

		return email;
	}

	@Override
	public String getPass() {

		return password;
	}

	@Override
	public boolean hasDevice(String deviceId) {
		return devices.contains(deviceId);
	}

	@Override
	public int getDeviceCount() {

		return devices.size();
	}

	@Override
	public int getMaxDevices() {
		switch (plan) {
		case BASIC:
			return 1;

		case STANDARD:
			return 2;

		case PREMIUM:
			return 4;

		default:
			return -1;
		}
	}

	@Override
	public void addDevice(String deviceID) {
		devices.add(deviceID);
		
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void removeDevice(String deviceID) {
		devices.remove(deviceID);
		
	}

	@Override
	public void setMembershipPlan(MembershipPlanEnum plan) {
		this.plan = plan;
		
	}

}
