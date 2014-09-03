import ui.Window;


public class Main
{
	public static void main(String[] args)
	{
		Window.show();
		
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException ie)
		{
			ie.printStackTrace();
		}
		
		Window.addToTextPane("Some Text.");
	}
}
