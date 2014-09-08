package commands;

import connection.Data;

public class MeCommand extends Command
{

	public MeCommand()
	{
		super("me");
	}

	@Override
	public Data execute(Data data)
	{
		data.setData(data.toString().replace(super.getCommandChar() + this.getCommandText(), ""));
		
		data.setData("[MECOMMAND]" + data.toString());
		
		return data;
	}

}
