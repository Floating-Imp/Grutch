package commands;

import ui.Window;
import connection.Data;

public class ExitCommand extends Command
{

	public ExitCommand()
	{
		super("exit");
	}

	@Override
	public Data execute(Data data)
	{
		
		data.setOutput(data.getUsername() + " has left.");
		
		Window.closing();
		
		return data;
	}

}
