package com.yeep.basis.swing.widget.frame;

import java.awt.AWTException;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * The frame extends TrayFrame can be trayed and revert from tray
 * 
 */
@SuppressWarnings("serial")
public class TrayFrame extends JFrame
{
	private TrayIcon trayIcon;
	private SystemTray tray;
	private TrayFrameHandler trayFrameHandler;

	// Constructor
	public TrayFrame()
	{
		super();
		initialize();
	}

	/*
	 * Set tray icon
	 */
	public void setTrayIcon(TrayIcon trayIcon)
	{
		this.trayIcon = trayIcon;
	}

	/*
	 * Set tray icon
	 */
	public void setTrayIcon(ImageIcon icon, String tooltip)
	{
		this.trayIcon = new TrayIcon(icon.getImage(), tooltip);
	}

	/*
	 * Set tray icon
	 */
	public void setTrayIcon(ImageIcon icon, String tooltip, PopupMenu menu)
	{
		this.trayIcon = new TrayIcon(icon.getImage(), tooltip, menu);
	}
	
	public void setTrayFrameHandler(TrayFrameHandler trayFrameHandler)
	{
		this.trayFrameHandler = trayFrameHandler;
	}

	/**
	 * Initialize tray
	 */
	private void initialize()
	{
		if (!SystemTray.isSupported())
			return;
		this.tray = SystemTray.getSystemTray();
	}

	protected void switchTray()
	{
		try
		{
			if (tray != null)
			{
				TrayIcon[] icons = tray.getTrayIcons();
				boolean exist = false;
				for (TrayIcon icon : icons)
				{
					if (icon == trayIcon)
						exist = true;
				}

				if (exist)
				{
					tray.remove(trayIcon);
					setVisible(exist);
					if (this.trayFrameHandler != null)
						this.trayFrameHandler.fromTray();
				}
				else
				{
					tray.add(trayIcon);
					setVisible(exist);
					if (this.trayFrameHandler != null)
						this.trayFrameHandler.toTray();
				}
			}
		}
		catch (AWTException ex)
		{
			ex.printStackTrace();
		}
	}

	/**
	 * Make frame to tray
	 */
	protected void toTray()
	{
		try
		{
			if (tray != null)
			{
				tray.add(trayIcon);
				setVisible(false);
			}
		}
		catch (AWTException ex)
		{
			ex.printStackTrace();
		}
	}

	/**
	 * Resume frame from tray
	 */
	protected void fromTray()
	{
		if (tray != null)
		{
			tray.remove(trayIcon);
			setVisible(true);
		}
	}

	// Setter and Getter
	public TrayIcon getTrayIcon()
	{
		return trayIcon;
	}
}
