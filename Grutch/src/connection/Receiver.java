package connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver implements Runnable
{
	private DatagramSocket socket;
	
	public Receiver(DatagramSocket socket)
	{
		this.socket = socket;
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			DatagramPacket temp = new DatagramPacket("".getBytes(), 0);
			try
			{
				socket.receive(temp);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			
			new Thread(new Processing(temp)).start();
		}
	}
}
