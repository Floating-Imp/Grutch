package ui;

import java.awt.Container;
import java.awt.Dimension;
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
		final JFrame nameFrame = new JFrame();
		
		nameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		nameFrame.setLocationRelativeTo(null);
		
		nameFrame.setTitle("Enter username");
		
		nameFrame.setLayout(new GridBagLayout());
		
		Container content = nameFrame.getContentPane();
		
		final JTextField textField = new JTextField();
		
		Dimension textFieldDimens = new Dimension();
		
		textFieldDimens.height = 100;
		textFieldDimens.width = 300;
		
		textField.setPreferredSize(textFieldDimens);
		
		textField.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent arg0)
			{
				if (arg0.getKeyCode() == (KeyEvent.VK_ENTER))
				{
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
		
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridheight = 1;
		c.gridwidth = 3;
		
		content.add(textField, c);
		
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
}
