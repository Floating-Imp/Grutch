package commands;

public enum Commands
{
	me (new MeCommand()),
	exit (new ExitCommand());
	
	private Command command;
	
	private Commands(Command c)
	{
		command = c;
	}
	
	public Command getValue()
	{
		return command;
	}
}
