package connection;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class Connection
{
	private static DatagramSocket socket;
	private static ServerSocket server;
	
	public static InetAddress address;
	public static int port;
	
	
	static
	{
		port = 1001;
		
		try	
		{
			address = InetAddress.getByName("");
		}
		catch (UnknownHostException uhe)
		{
			
		}
		
		socket.connect(address, port);
	}
	
	public static void sendData(Data data)
	{
		DatagramPacket datagram = new DatagramPacket(data.getData(), port, null);
		
		socket.send(datagram);
	}
}
