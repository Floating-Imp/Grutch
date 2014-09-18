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
		String rawData = null;
		
		try
		{
			rawData = new String(packet.getData(), "UTF-8");
		}
		catch (UnsupportedEncodingException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try
		{
			rawData = rawData.trim();
			
			String colorRGB = rawData.split("\\|")[2];
			
			int red = Integer.parseInt(colorRGB.split(",")[0]);
			int green = Integer.parseInt(colorRGB.split(",")[1]);
			int blue = Integer.parseInt(colorRGB.split(",")[2]);
			
			Data data = new Data(rawData.split("\\|")[1], rawData.split("\\|")[0], new Color(red, green, blue));
			
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
