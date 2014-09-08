package commands;

import connection.Data;

public abstract class Command
{
	private static final char COMMAND_CHAR = '/';
	
	private final String keyText;
	
	public Command(String commandText)
	{
		this.keyText = commandText;
	}
	
	public String getCommandText()
	{
		return keyText;
	}
	
	
	/**
	 * Executes the command
	 * @param packet The packet that was sent.
	 * @return The modified data
	 */
	public abstract Data execute(Data data);
	
	public static char getCommandChar()
	{
		return COMMAND_CHAR;
	}
}
