package com.yeep.basis.swing.widget.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.yeep.basis.util.Utility;

@SuppressWarnings("serial")
public class TableWidgetModel<Entry> extends AbstractTableModel
{
	private List<Entry> entries = new ArrayList<Entry>();
	private String[] columnNames;
	private String[] displayNames;

	// Constructor
	public TableWidgetModel(List<Entry> entries, String[] columnNames, String[] displayNames)
	{
		super();
		this.columnNames = columnNames;
		this.displayNames = displayNames;
		if (entries != null)
			this.entries = entries;
	}

	public int getColumnCount()
	{
		return columnNames.length;
	}

	public int getRowCount()
	{
		return entries.size();
	}

	public String getColumnName(int col)
	{
		return columnNames[col];
	}

	public Object getValueAt(int row, int col)
	{
		return Utility.getAttribute(this.entries.get(row), displayNames[col]);
	}

	public Entry getEntry(int i)
	{
		return this.entries.get(i);
	}

	/**
	 * Add entry to model
	 * 
	 * @param entry
	 */
	public void addEntry(Entry entry)
	{
		if (!this.entries.contains(entry))
		{
			this.entries.add(entry);
			fireTableRowsInserted(this.entries.size() - 1, this.entries.size() - 1);
		}
		else
		{
			this.fireTableDataChanged();
		}
	}

	/**
	 * Remove entry from model
	 * 
	 * @param entry
	 */
	public void removeEntry(Entry entry)
	{
		this.entries.remove(entry);
		fireTableRowsDeleted(this.entries.size() - 1, this.entries.size() - 1);
	}
}
