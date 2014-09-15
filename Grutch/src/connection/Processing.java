package connection;

import java.awt.Color;
import java.io.UnsupportedEncodingException;
import java.net.DatagramPacket;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

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
		String temp = "";
		try
		{
			temp = new String(packet.getData(), "UTF-8");
		}
		catch (UnsupportedEncodingException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try
		{
			String colorRGB = temp.split("\\|")[2];
			
			Data data = new Data(temp.split("\\|")[1], temp.split("\\|")[0], new Color(Integer.parseInt(colorRGB.split(",")[0]), Integer.parseInt(colorRGB.split(",")[1]), Integer.parseInt(colorRGB.split(",")[2])));
			
			SimpleAttributeSet previousSAS = Window.getAttributes();
			
			SimpleAttributeSet lineSAS = new SimpleAttributeSet();
			StyleConstants.setForeground(lineSAS, data.getColor());
			Window.addStyleConstant(lineSAS);
			Window.addToTextPane(data);			
			Window.addStyleConstant(previousSAS);
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
	}

}
