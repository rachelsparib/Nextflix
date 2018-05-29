package enums;

/**
 * An enumerate type for output messages.
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081 MIEI
 *
 */
public enum MessageEnum {
	/**
	 * Enumerate constants and its values.
	 */
	UPLOAD_SUCCESS						("Database was updated:"),
	INVALID_COMMAND						("Unknown command."),
	CLIENT_ALREADY_LOGGEDIN				("Client already logged in."),
	ANOTHER_CLIENT_LOGGEDIN				("Another client is logged in."),
	ACCOUNT_DOES_NOT_EXIST				("Account does not exist."),
	WRONG_PASS							("Wrong password."),
	NOT_POSSIBLE_CONNECT_MORE_DEVICES	("Not possible to connect more devices"),
	NO_CLIENT_IS_LOGIN					("No client is logged in."),
	
	EXIT_SUCCESS		("Exiting...");
	
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
