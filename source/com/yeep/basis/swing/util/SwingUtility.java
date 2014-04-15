package com.yeep.basis.swing.util;

import java.util.List;

import javax.swing.JComponent;

/**
 * The <code>Utility</code> is a number of utility operations
 * that can be used for UI.
 */
public class SwingUtility
{
	public static final String SEPARATOR_SPACE = " ";

	/**
	 * Set the visible for each component of the given list
	 * @param components
	 * @param visible
	 */
	public static void setUIComponentVisible(List<JComponent> components, boolean visible)
	{
		if (components == null)
			return;

		for (JComponent component : components)
		{
			setUIComponentVisible(component, visible);
		}
	}

	/**
	 * Set the visible for the component
	 * @param component
	 * @param visible
	 */
	public static void setUIComponentVisible(JComponent component, boolean visible)
	{
		if (component == null)
			return;

		component.setVisible(visible);
	}
}
