package commands;

import java.awt.Color;
import java.lang.reflect.Field;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;

import ui.Window;
import connection.Data;

public class ColorCommand extends Command
{

	public ColorCommand()
	{
		super("color");
		// TODO Auto-generated constructor stub
	}

	@Override
	public Data execute(Data data)
	{
		String[] strings = data.toString().split(" ");
		Color color = Color.black;
		
		
		
		if (strings.length >= 1)
		{
			try
			{
				color = stringToColor(strings[1]);
				SimpleAttributeSet sas = new SimpleAttributeSet();
				StyleConstants.setForeground(sas, color);
				
				Window.addStyleConstant(sas);
			}
			catch (NoSuchFieldException e)
			{
				return null;
			}
			catch (SecurityException e)
			{
				e.printStackTrace();
			}
			catch (IllegalArgumentException e)
			{
				e.printStackTrace();
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	public static Color stringToColor(String string) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		Field f = Color.class.getField(string);
		return (Color)f.get(null);
	}

}
