package ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultCaret;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import commands.Command;
import commands.Commands;
import connection.Connection;
import connection.Data;
import connection.Receiver;

public class Window
{
	private static String username;
	
	private static Window instance;
	
	private static JScrollPane scrollPane;
	
	private static JTextPane textPane;
	
	private static StyledDocument doc;
	
	private static JFrame baseFrame;
	
	private static SimpleAttributeSet attributes;
	
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
		baseFrame.addWindowListener(new WindowListener(){

			@Override
			public void windowActivated(WindowEvent arg0)
			{
			}

			@Override
			public void windowClosed(WindowEvent arg0)
			{
			}

			@Override
			public void windowClosing(WindowEvent arg0)
			{
				Receiver.stop();
			}

			@Override
			public void windowDeactivated(WindowEvent arg0)
			{
			}

			@Override
			public void windowDeiconified(WindowEvent arg0)
			{
			}

			@Override
			public void windowIconified(WindowEvent arg0)
			{
			}

			@Override
			public void windowOpened(WindowEvent arg0)
			{
			}
		});
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.gridheight = 2;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		
		Container content = baseFrame.getContentPane();
		
		textPane = new JTextPane();
		textPane.setForeground(Color.white);
		attributes = new SimpleAttributeSet();
		
		StyleConstants.setForeground(attributes, Color.WHITE);
		textPane.setBackground(Color.black);
		
		doc = textPane.getStyledDocument();
		
		
		scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane.add(textPane);
		scrollPane.setViewportView(textPane);
		textPane.setEditable(false);
		textPane.setText("\n\n\n\n\n");
		
		Dimension textPaneMax = new Dimension();
		textPaneMax.height = 100;
		textPaneMax.width = 275;
		
		scrollPane.setMaximumSize(textPaneMax);
		textPane.setMaximumSize(textPaneMax);
		
		Dimension textPaneMin = new Dimension();
		textPaneMin.height = 100;
		textPaneMin.width = 300;
		textPane.setMinimumSize(textPaneMin);
		scrollPane.setPreferredSize(textPaneMin);
		scrollPane.setMinimumSize(textPaneMin);
		scrollPane.setSize(textPaneMin);
		scrollPane.setAutoscrolls(true);
		
		content.add(scrollPane, c);
		
		c.gridy = 3;
		c.gridheight = 1;
		c.gridwidth = 3;
		c.anchor = GridBagConstraints.LAST_LINE_START;
		
		JLabel chatBoxLabel = new JLabel("Chat:");
		
		content.add(chatBoxLabel, c);
		
		c.gridy = 4;
		c.anchor = GridBagConstraints.PAGE_END;
		
		final JTextArea textBox = new JTextArea(1, 3);
		
		Dimension textBoxDimen = new Dimension();
		textBoxDimen.width = 250;
		textBox.setPreferredSize(textBoxDimen);
		
		 @SuppressWarnings("serial")
		Action moveToSelectionStart = new AbstractAction("moveCaret") {

		        @Override
		        public void actionPerformed(ActionEvent e) {
		            int selectionStart = textBox.getSelectionStart();
		            int selectionEnd = textBox.getSelectionEnd();
		            if (selectionStart != selectionEnd) {
		                textBox.setCaretPosition(selectionEnd);
		                textBox.moveCaretPosition(selectionStart);
		            }
		        }

		        public boolean isEnabled() {
		            return textBox.getSelectedText() != null;
		        }
		    };
		    Object actionMapKey = "caret-to-start";
		    textBox.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), actionMapKey);
		    textBox.getActionMap().put(actionMapKey, moveToSelectionStart);
		
		textBox.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent e)
			{
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
				{
					Data data = new Data(textBox.getText(), username, StyleConstants.getForeground(attributes));

					if (data.toString().equals("\n"))
					{
						return;
					}
					
					if (data.toString().startsWith("\n"))
						data.setData(data.toString().split("\n")[1]);
					
					if (data.toString().endsWith("\n"))
						data.setData(data.toString().split("\n")[0]);
					
					for (Commands c : Commands.values())
					{
						if (data.getText().startsWith(Command.getCommandChar() + "" + c.getValue().getCommandText()))
						{							
							data = c.getValue().execute(data);
						}
					}
					
					if (data != null)
					{
						Window.addToTextPane(data.toString());
						Connection.sendData(data);
					}

					textBox.setText("");
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
		baseFrame.pack();
	}
	
	public static Window getInstance()
	{		
		return instance;
	}
	
	public static void addToTextPane(String textToAdd)
	{		
//		String temp;
//		if (textPane.getText().startsWith("\n"))
//		{
//			temp = textPane.getText().replaceFirst("\n", "");
//		}
//		else
//		{
//			temp = textPane.getText();
//		}
	
		try
		{
			doc.insertString(doc.getLength(), "\n" + textToAdd, attributes);
		}
		catch (BadLocationException e)
		{
			e.printStackTrace();
		}

		textPane.setCaretPosition(textPane.getDocument().getLength());
		DefaultCaret caret = (DefaultCaret) textPane.getCaret();
		caret.setUpdatePolicy(DefaultCaret.NEVER_UPDATE);

		
		System.out.println("Current Text: " + textPane.getText());
	}
	
	public static void show(String username)
	{
		Window.username = username;
		baseFrame.setVisible(true);
	}
	
	public static void addStyleConstant(SimpleAttributeSet sas)
	{
		attributes = sas;
	}
	
	public static SimpleAttributeSet getAttributes()
	{
		return attributes;
	}
	
	public static void closing()
	{
		baseFrame.setVisible(false);
		baseFrame.dispose();
	}
}
