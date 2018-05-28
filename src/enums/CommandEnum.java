package enums;

/**
 * An enumerate type for program commands.
 * @author Antonio Santos 49055 MIEI & Raquel Pena 45081 MIEI
 *
 */
public enum CommandEnum {
	/**
	 * Enumerate constants and its values.
	 */
	UPLOAD			("upload"),
	EXIT			(""),
	UNKNOWN			("");
	
	/**
	 * Command name.
	 */
	private String name;
	
	/**
	 * Creates a command constant with name <code>name</code>.
	 * @param name name of the command.
	 */
	private CommandEnum(String name) {
		this.name = name;
	}
	
	/**
	 * Return command name.
	 * @return command name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Return command value that matches the one received, or null if it doesn't match.
	 * @param command command received.
	 * @return command value that matches the one received, or null if it doesn't match.
	 */
	public static CommandEnum getValue(String command) {
		for(CommandEnum c: values())
			if(c.getName().equalsIgnoreCase(command))
				return c;
		return null;
	}
	
	
}
