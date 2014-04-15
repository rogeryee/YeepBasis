package com.yeep.basis.swing.widget.table;

import javax.swing.JTable;


@SuppressWarnings("serial")
public class TableWidget<X> extends JTable
{
	private TableWidgetModel<X> listModel;

	public TableWidget()
	{
		super();
	}

	public TableWidget(TableWidgetModel<X> listModel)
	{
		super(listModel);
		this.listModel = listModel;
	}

	/**
	 * Set listModel
	 * @param listModel
	 */
	public void setListModel(TableWidgetModel<X> listModel)
	{
		super.setModel(listModel);
		this.listModel = listModel;
	}

	public X getSelected()
	{
		int selectedIndex = getSelectedRow();
		return selectedIndex == -1 ? null : this.listModel.getEntry(selectedIndex);
	}

	/**
	 * Add row to the table
	 * @param x
	 */
	public void addRow(X x)
	{
		this.listModel.addEntry(x);
	}

	/**
	 * Remove a specific row
	 * @param x
	 */
	public void removeRow(X x)
	{
		this.listModel.removeEntry(x);
	}
}
