package com.yeep.basis.swing.widget.menu;

import javax.swing.JComponent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

@SuppressWarnings("serial")
public class PopupMenuWidget extends JPopupMenu
{
	/**
	 * Appends the every menu item of the specified menu group to the end of this menu. 
	 */
	public MenuItemGroup<JMenuItem, JSeparator> add(
			MenuItemGroup<JMenuItem, JSeparator> menuGroup)
	{
		if (menuGroup != null)
		{
			for (JComponent jcomponent : menuGroup.getItems())
			{
				super.add(jcomponent);
			}
		}
		return menuGroup;
	}
}
