package connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver implements Runnable
{
	private DatagramSocket socket;
	
	private static boolean running;
	
	public Receiver(DatagramSocket socket)
	{
		this.socket = socket;
		running = true;
	}
	
	@Override
	public void run()
	{
		while(running)
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
	
	public static void stop()
	{
		running = false;
	}
}
