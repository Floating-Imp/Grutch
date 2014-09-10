package connection;

import java.net.DatagramPacket;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import commands.ColorCommand;
import ui.Window;

public class Processing implements Runnable
{
	private DatagramPacket packet;
	
	Processing(DatagramPacket packet)
	{
		this.packet = packet;
	}
	
	@Override
	public void run()
	{
		String temp = packet.getData().toString();
		
		try
		{
			Data data = new Data(temp.split("|")[0], temp.split("|")[1], ColorCommand.stringToColor(temp.split("|")[2]));
			
			SimpleAttributeSet previousSAS = Window.getAttributes();
			
			SimpleAttributeSet lineSAS = new SimpleAttributeSet();
			StyleConstants.setForeground(lineSAS, data.getColor());
			Window.addStyleConstant(lineSAS);
			Window.addToTextPane(data.toString());			
			Window.addStyleConstant(previousSAS);
		}
		catch (NoSuchFieldException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (SecurityException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalArgumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
