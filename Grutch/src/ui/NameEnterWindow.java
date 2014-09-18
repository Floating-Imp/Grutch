package ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import connection.Connection;

public class NameEnterWindow
{	
	private String username;
	
	private NameEnterWindow()
	{
		final JFrame nameFrame = new JFrame();
		
		nameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		nameFrame.setLocationRelativeTo(null);
		
		nameFrame.setTitle("Enter username and IP to connect to");
		
		nameFrame.setLayout(new GridBagLayout());

		GridBagConstraints c = new GridBagConstraints();
		
		Container content = nameFrame.getContentPane();
		
		JLabel userNameLabel = new JLabel("Username: ");
		
		c.gridx = 1;
		c.gridy = 1;
		c.gridheight = 1;
		c.gridwidth = 1;
		
		content.add(userNameLabel, c);
		
		final JTextField textField = new JTextField();
		
		final JTextField ipField = new JTextField();
		
		Dimension textFieldDimens = new Dimension();
		
		textFieldDimens.height = 25;
		textFieldDimens.width = 300;
		
		textField.setPreferredSize(textFieldDimens);
		
		textField.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0)
			{
				if (arg0.getKeyCode() == (KeyEvent.VK_ENTER))
				{
					if (textField.getText().equals("") || ipField.getText().equals("") || !isValidIP(ipField.getText()))
					{
						JOptionPane.showMessageDialog(nameFrame, "Invalid username or IP", "ERROR", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					try
					{
						Connection.instantiate(ipField.getText());
					}
					catch (UnknownHostException e)
					{
						JOptionPane.showMessageDialog(nameFrame, "Invalid username or IP", "ERROR", JOptionPane.ERROR_MESSAGE);
						return;
					}
					username = textField.getText().replace("\n", "");
					
					nameFrame.setVisible(false);
					nameFrame.dispose();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0)
			{
			}

			@Override
			public void keyTyped(KeyEvent arg0)
			{				
			}
			
		});
		
		c.gridheight = 1;
		c.gridwidth = 3;
		c.gridx = 1;
		c.gridy = 2;
		
		content.add(textField, c);
		
		JLabel IPLabel = new JLabel("Server IP:");
		
		c.gridx = 1;
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 1;
		
		content.add(IPLabel, c);
		
		ipField.setPreferredSize(textFieldDimens);
		
		ipField.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0)
			{
				if (arg0.getKeyCode() == (KeyEvent.VK_ENTER))
				{
					if (textField.getText().equals("") || ipField.getText().equals("") || !isValidIP(ipField.getText()))
					{
						JOptionPane.showMessageDialog(nameFrame, "Invalid username or IP", "ERROR", JOptionPane.ERROR_MESSAGE);
						return;
					}
					
					try
					{
						Connection.instantiate(ipField.getText());
					}
					catch (UnknownHostException e)
					{
						JOptionPane.showMessageDialog(nameFrame, "Invalid username or IP", "ERROR", JOptionPane.ERROR_MESSAGE);
						return;
					}
					username = textField.getText().replace("\n", "");
					
					nameFrame.setVisible(false);
					nameFrame.dispose();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0)
			{
			}

			@Override
			public void keyTyped(KeyEvent arg0)
			{				
			}
			
		});
		
		c.gridx = 1;
		c.gridy = 4;
		c.gridheight = 1;
		c.gridwidth = 3;
		
		content.add(ipField, c);
		
		nameFrame.pack();
		nameFrame.setVisible(true);
	}
	
	public static String getUserName()
	{
		NameEnterWindow nEw = new NameEnterWindow();
		
		while (nEw.username == null)
		{
			
		}
		
		return nEw.username;
	}
	
	private static boolean isValidIP(String ip)
	{
		String[] parts = ip.split("\\.");
		if (parts.length != 4)
		{
			return false;
		}
		
		for (String s : parts)
		{
			try
			{
				Integer.parseInt(s);
			}
			catch (NumberFormatException e)
			{
				return false;
			}
		}
		
		return true;
	}
}
