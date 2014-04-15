package test;

import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

@SuppressWarnings("serial")
public class TrayDemo extends JFrame
{
	private JPanel pane = null;
	private JButton button = null;
	private JLabel label = null;
	private TrayIcon trayIcon = null;
	private SystemTray tray = null;

	public TrayDemo()
	{
		super("DEMO");
		try
		{

			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		pane = new JPanel();
		button = new JButton("to tray");
		button.setEnabled(false);
		label = new JLabel("not Support tray");
		pane.add(label);
		pane.add(button);
		if (SystemTray.isSupported())
		{
			this.tray();
		}
		this.getContentPane().add(pane);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(300, 200);
		this.setVisible(true);
	}

	private void tray()
	{
		label.setText("Support tray");
		button.setEnabled(true);
		tray = SystemTray.getSystemTray();
		ImageIcon icon = new ImageIcon("images/icon.gif");
		PopupMenu pop = new PopupMenu();
		MenuItem show = new MenuItem("show");
		MenuItem exit = new MenuItem("Exit");
		MenuItem author = new MenuItem("Author");

		trayIcon = new TrayIcon(icon.getImage(), "DEMO", pop);

		button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					tray.add(trayIcon);
					setVisible(false);
				}
				catch (AWTException ex)
				{
					ex.printStackTrace();
				}
			}
		});

		trayIcon.addMouseListener(new MouseAdapter()
		{
			public void mouseClicked(MouseEvent e)
			{
				if (e.getClickCount() == 2)
				{
					tray.remove(trayIcon);
					setVisible(true);
				}
			}
		});
		show.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				tray.remove(trayIcon);
				setVisible(true);
			}
		});
		exit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		author.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				showMessage();
			}
		});
		pop.add(show);
		pop.add(exit);
		pop.add(author);
	}

	private void showMessage()
	{

	}

	public static void main(String[] args)
	{
		new TrayDemo();
	}

}
