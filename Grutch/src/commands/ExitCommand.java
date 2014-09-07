package commands;

import connection.Connection;
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
		
		data.setData("[EXITCOMMAND]" + data.getData().toString());
		
		Connection.disconnect();
		
		return data;
	}

}
