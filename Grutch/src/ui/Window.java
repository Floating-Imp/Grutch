package ui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.ScrollPane;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class Window
{
	private static Window instance;
	
	private static ScrollPane scrollPane;
	
	private static JTextPane textPane;
	
	private static JFrame baseFrame;
	
	static
	{
		instance = new Window();
	}
	
	private Window()
	{
		baseFrame = new JFrame("TEST");
		baseFrame.setLayout(new GridBagLayout());
		
		baseFrame.setSize(200, 300);
		baseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.gridheight = 2;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		
		Container content = baseFrame.getContentPane();
		
		textPane = new JTextPane();
		scrollPane = new ScrollPane(ScrollPane.SCROLLBARS_AS_NEEDED);
		scrollPane.add(textPane);
		textPane.setEditable(false);
		textPane.setText("\n\n\n\n\n");
		
		Dimension textPaneMax = new Dimension();
		textPaneMax.height = 100;
		textPaneMax.width = 275;
		
		scrollPane.setMaximumSize(textPaneMax);
		
		Dimension textPaneMin = new Dimension();
		textPaneMin.height = 100;
		textPaneMin.width = 100;
		scrollPane.setMinimumSize(textPaneMin);
		
		scrollPane.
		
		
		content.add(scrollPane, c);
		
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.anchor = GridBagConstraints.LAST_LINE_START;
		
		JLabel chatBoxLabel = new JLabel("Chat:");
		
		content.add(chatBoxLabel, c);
		
		c.gridy = 4;
		c.anchor = GridBagConstraints.PAGE_END;
		
		final JTextArea textBox = new JTextArea("0,0");
		
		Dimension textBoxDimen = new Dimension();
		textBoxDimen.width = 250;
		textBox.setMinimumSize(textBoxDimen);
		textBox.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					Window.addToTextPane(textBox.getText());
					textBox.setText("");
					textBox.setCaretPosition(0);
				}
			}

			@Override
			public void keyReleased(KeyEvent ignored)
			{
			}

			@Override
			public void keyTyped(KeyEvent ignored)
			{
				
			}
			
		});
		content.add(textBox, c);
		
		
		baseFrame.setLocationRelativeTo(null);		
	}
	
	public static Window getInstance()
	{		
		return instance;
	}
	
	public static void addToTextPane(String textToAdd)
	{
		if (textToAdd.endsWith("\n"))
		{
			textToAdd.replace("\n", "");
		}
		
		String temp;
		if (textPane.getText().startsWith("\n"))
		{
			temp = textPane.getText().replaceFirst("\n", "");
		}
		else
		{
			temp = textPane.getText();
		}
		textPane.setText(temp + "\n" + textToAdd);
		
		System.out.println("Current Text: " + textPane.getText());
	}
	
	public static void show()
	{
		baseFrame.setVisible(true);
	}
}
