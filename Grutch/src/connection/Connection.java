package connection;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Connection
{
	private static DatagramSocket socket;
	private static InetAddress SEND_TO_IP;
	public static int port;
	
	
	static
	{
		port = 1001;
		
		try	
		{
			SEND_TO_IP = InetAddress.getByName("10.135.213.136");
		}
		catch (UnknownHostException uhe)
		{
			
		}
	}
	
	public Connection()
	{
		socket.connect(SEND_TO_IP, port);
		
	}
	
	public static void sendData(Data data) throws IOException
	{
		DatagramPacket datagram = new DatagramPacket(data.getData(), data.getData().length, SEND_TO_IP, port);
		
		socket.send(datagram);
	}
}
