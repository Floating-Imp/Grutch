package connection;

import java.awt.Color;

public class Data
{
	private String text;
	private String username;
	private Color color;
	
	public Data(String info, String username, Color c)
	{
		this.text = info;
	}
	
	public byte[] getData()
	{
		return (username + "|" + text + "|" + color.toString()).getBytes();
	}
	
	public void setData(String info, String username, Color c)
	{
		this.text = info;
		this.username = username;
		this.color = c;
	}
	
	public void setData(String info, String username)
	{
		this.text = info;
		this.username = username;
	}
	
	public void setData(String info)
	{
		this.text = info;
	}
	
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	public void setColor(Color c)
	{
		this.color = c;
	}
	
	public Color getColor()
	{
		return color;
	}
	
	public String toString()
	{
		return "[" + this.username + "]:" + this.text;
	}
}
