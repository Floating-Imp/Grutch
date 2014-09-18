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
	
	private static Connection instance;
	
	private Connection(String ip) throws UnknownHostException
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
		
		SEND_TO_IP = InetAddress.getByName(ip);

		socket.connect(SEND_TO_IP, port);
		
		try
		{
			DatagramSocket receiver = new DatagramSocket(socket.getLocalPort() + 1);
			
			System.out.println("Receiver set to port: " + receiver.getLocalPort());
			
			new Thread(new Receiver(receiver)).start();
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
	
	public static void instantiate(String SEND_TO_IP) throws UnknownHostException
	{
		if (instance == null)
		{
			instance = new Connection(SEND_TO_IP);
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
