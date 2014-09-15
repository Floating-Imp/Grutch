package connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Connection
{
	private static DatagramSocket socket;
	private static InetAddress SEND_TO_IP;
	public static int port;
	
	
	static
	{		
		try
		{
			socket = new DatagramSocket(0);
		}
		catch (java.net.BindException e)
		{
//			try
//			{
//				socket = new DatagramSocket(1003);
//			}
//			catch (java.net.BindException ex)
//			{
//				ex.printStackTrace();
//			}
//			catch (SocketException e1)
//			{
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
			e.printStackTrace();
		}
		catch (SocketException e)
		{
			e.printStackTrace();
		}
		
		port = 1001;
		
		try	
		{
			SEND_TO_IP = InetAddress.getByName("10.135.213.136");
		}
		catch (UnknownHostException uhe)
		{
			
		}
		

		socket.connect(SEND_TO_IP, port);
		
		try
		{
			new Thread(new Receiver(new DatagramSocket(socket.getPort() + 1))).start();
		}
		catch (SocketException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try
		{
			Thread.sleep(100);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sendData(Data data)
	{
		DatagramPacket datagram = new DatagramPacket(data.getData(), data.getData().length, SEND_TO_IP, port);
		
		try
		{
			socket.send(datagram);
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
	}
	
	public static void disconnect()
	{
		if (socket.isConnected())
			socket.disconnect();
	}
}
