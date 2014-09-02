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
}
