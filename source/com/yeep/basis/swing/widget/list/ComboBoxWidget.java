package com.yeep.basis.swing.widget.list;

import javax.swing.JComboBox;

@SuppressWarnings("serial")
public class ComboBoxWidget<X> extends JComboBox
{
	private ComboBoxWidgetModel<X> listModel;

	// Constructor
	public ComboBoxWidget()
	{
	}

	public ComboBoxWidget(ComboBoxWidgetModel<X> listModel)
	{
		this();
		super.setModel(listModel);
		this.listModel = listModel;
	}

	/**
	 * Set listModel
	 * @param listModel
	 */
	public void setListModel(ComboBoxWidgetModel<X> listModel)
	{
		super.setModel(listModel);
		this.listModel = listModel;
	}

	/**
	 * Get listModel
	 * @return
	 */
	public ComboBoxWidgetModel<X> getListModel()
	{
		return this.listModel;
	}

	/**
	 * Get selected item
	 * @return
	 */
	public X getSelected()
	{
		int selectedIndex = this.getSelectedIndex();
		return selectedIndex == -1 ? null : this.listModel.getEntry(selectedIndex);
	}

	/**
	 * Set selected item
	 */
	public void setSelected(X x)
	{
		if (x != null)
			setSelectedIndex(this.listModel.getEntryIndex(x));
	}
}
