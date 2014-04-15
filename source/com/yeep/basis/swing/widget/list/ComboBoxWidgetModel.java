package com.yeep.basis.swing.widget.list;

import java.util.List;

import javax.swing.ComboBoxModel;

@SuppressWarnings("serial")
public class ComboBoxWidgetModel<Entry> extends ListWidgetModel<Entry>
		implements ComboBoxModel
{
	private Object selectedObject;

	// Constructor
	public ComboBoxWidgetModel(List<Entry> entries, String[] displayNames)
	{
		super(entries, displayNames);
	}

	// implements javax.swing.ComboBoxModel
	/**
	 * Set the value of the selected item. The selected item may be null.
	 * <p>
	 * @param anObject The combo box value or null for no selection.
	 */
	public void setSelectedItem(Object anObject)
	{
		if ((selectedObject != null && !selectedObject.equals(anObject))
				|| selectedObject == null && anObject != null)
		{
			selectedObject = anObject;
			fireContentsChanged(this, -1, -1);
		}
	}

	// implements javax.swing.ComboBoxModel
	public Object getSelectedItem()
	{
		return selectedObject;
	}
}
