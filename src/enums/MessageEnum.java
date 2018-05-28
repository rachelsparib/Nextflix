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
	UPLOAD_SUCCESS		("Database was updated:"),
	INVALID_COMMAND		("Unknown command."),
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
