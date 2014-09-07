package connection;

public class Data
{
	private byte[] data;
	
	public Data(String info)
	{
		this.data = info.getBytes();
	}
	
	public byte[] getData()
	{
		return data;
	}
	
	public void setData(String s)
	{
		data = s.getBytes();
	}
}
