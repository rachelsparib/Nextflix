package enums;
/**
 * An enumerate type for output messages.
 * @author Antonio Santos 49055 MIEI e Raquel Pena 45081 MIEI
 *
 */
public enum MessageEnum {
	/**
	 * Enumerate constants and its values.
	 */
	UPLOAD_SUCCESS						("Database was updated:"),
	SERVICE_BUSY						("Another client is logged in."),
	INVALID_REGISTRATION				("There is another account with email "),
	LOGIN_SUCCESS						("Welcome "),
	CLIENT_ALREADY_LOGGEDIN				("Client already logged in."),
	ACCOUNT_DOES_NOT_EXIST				("Account does not exist."),
	WRONG_PASS							("Wrong password."),
	NOT_POSSIBLE_CONNECT_MORE_DEVICES	("Not possible to connect more devices."),
	NO_CLIENT_IS_LOGGED					("No client is logged in."),
	LOGOUT_SUCCESS						("Goodbye "),
	DEV_DISCONNECTED					(" was disconnected)."),
	DEV_STILL_CONNECTED					(" still connected)."),
	MEMBERSHIP_CHANGED_SUCESS			("Membership plan was changed from "),
	NO_MEMBERSHIP_CHANGE				("No membership plan change."),
	CANNOT_DOWNGRADE_MEMBERSHIP			("Cannot downgrade membership plan at the moment."),
	PROFILE_ADDED						("New profile added."),
	PROFILE_ALREADY_EXIST				("There is already a profile "),
	PROFILE_MAX_EXCEEDED				("Not possible to add more profiles."),
	PROFILE_DOES_NOT_EXIST				("Profile does not exist."),
	WATCH_SUCESS						("Loading "),
	PROFILE_NOT_SELECTED				("No profile is selected."),
	CONTENT_NOT_EXISTS					("Show does not exist."),
	CONTENT_INNAPROPRIATE				("Show not available."),
	RATE_SUCCESS						("Thank you for rating "),
	CONTENT_NOT_RECENT					("Can only rate recently seen shows."),
	CONTENT_ALREADY_RATED				("Show already rated."),
	NO_PROFILES_DEFINED					("No profiles defined."),
	EMPTY_RECENTLY_WATCHED				("Empty list of recently seen shows."),
	INVALID_COMMAND						("Unknown command."),
	EXIT_SUCCESS						("Exiting...");
	
	/**
	 * Message content.
	 */
	private String description;
	
	/**
	 * Creates an output message with respective content.
	 * @param description content of the message.
	 */
	private MessageEnum(String description) {
		this.description = description;
	}
	
	/**
	 * Returns content of the message.
	 */
	public String toString() {
		return this.description;
	}
}
