package ui;

import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class NameEnterWindow
{	
	private String username;
	
	private NameEnterWindow()
	{
		JFrame nameFrame = new JFrame();
		
		nameFrame.setTitle("Enter username");
		
		nameFrame.setLayout(new GridBagLayout());
		
		Container content = nameFrame.getContentPane();
		
		final JTextField textField = new JTextField();
		
		textField.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0)
			{
				if (arg0.equals(KeyEvent.VK_ENTER))
				{
					username = textField.getText().replace("\n", "");
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
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridheight = 1;
		c.gridwidth = 3;
		
		content.add(textField, c);
		
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
}
