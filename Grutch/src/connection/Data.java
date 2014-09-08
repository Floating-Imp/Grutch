package connection;

public class Data
{
	private byte[] data;
	private String text;
	
	public Data(String info)
	{
		this.text = info;
		this.data = info.getBytes();
	}
	
	public byte[] getData()
	{
		return data;
	}
	
	public void setData(String s)
	{
		this.text = s;
		data = s.getBytes();
	}
	
	public String toString()
	{
		return this.text;
	}
}
