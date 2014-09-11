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
		data.setData(data.getText().replace(super.getCommandChar() + this.getCommandText(), ""));
		
		data.setOutput(data.getUsername() + " " + data.getText());
		
		return data;
	}

}
