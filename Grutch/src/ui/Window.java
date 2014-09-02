package ui;

import java.awt.Container;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SpringLayout;

public class Window
{
	public Window()
	{
		JFrame baseFrame = new JFrame("TEST");
		baseFrame.setLayout(new GridBagLayout());
		
		baseFrame.setSize(200, 300);
		baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Container content = baseFrame.getContentPane();
		
		JTextPane textPane = new JTextPane();
		
		textPane.setText("ASDFRASDFAS");
		
		content.add(textPane);
		
		JLabel chatBoxLabel = new JLabel("Chat:");
		
		content.add(chatBoxLabel);
		
		final JLabel coords = new JLabel("0,0");
		
		baseFrame.addMouseMotionListener(new MouseMotionListener(){

			@Override
			public void mouseDragged(MouseEvent arg0)
			{
			}

			@Override
			public void mouseMoved(MouseEvent arg0)
			{
				coords.setText(arg0.getX() + "," + arg0.getY());
			}
			
		});
		
		content.add(coords);
		
		
		baseFrame.setLocationRelativeTo(null);
		baseFrame.setVisible(true);
	}
}
