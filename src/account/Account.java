package account;
/**
 * A user account in the streaming service.
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081 MIEI
 *
 */
public interface Account {
	
	public String getEmail();
	
	public String getPass();
	
	public boolean hasDevice(String deviceID);
	
	public int getDeviceCount();
	
	public int getMaxDevices();
	
	public void addDevice(String deviceID);
	
	public String getName();
	
	/**
	 * Returns information of an account.
	 * @return information of an account.
	 */
	String toString();
}
