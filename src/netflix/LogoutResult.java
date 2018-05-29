package netflix;

import account.Account;

public class LogoutResult {
	private Account account;
	private String deviceId;
	
	public LogoutResult(Account account, String deviceId) {
		this.account = account;
		this.deviceId = deviceId;
	}
	
	public Account getAccount() {
		return account;
	}

	public String getDeviceId() {
		return deviceId;
	}	
}
