package com.yeep.basis.swing.widget.list;

import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractListModel;

import com.yeep.basis.util.Utility;

@SuppressWarnings("serial")
public class ListWidgetModel<Entry> extends AbstractListModel
{
	private List<Entry> entries = new ArrayList<Entry>();
	private String[] displayNames;

	// Constructor
	public ListWidgetModel(List<Entry> entries, String[] displayNames)
	{
		super();
		if (entries != null)
			this.entries = entries;
		this.displayNames = displayNames;
	}

	/**
	 * @see javax.swing.ListModel#getSize()
	 */
	public int getSize()
	{
		return this.entries == null ? 0 : this.entries.size();
	}

	/**
	 * @see javax.swing.ListModel#getElementAt(int)
	 */
	public Object getElementAt(int i)
	{
		if (this.displayNames == null)
			return this.entries.get(i).toString();
		return Utility.getValue(this.entries.get(i), this.displayNames);
	}

	public Entry getEntry(int i)
	{
		return this.entries.get(i);
	}

	public void addEntry(Entry entry)
	{
		this.entries.add(entry);
		fireIntervalAdded(this, this.entries.size() - 1, this.entries.size() - 1);
	}

	public boolean removeEntry(Entry entry)
	{
		int index = this.entries.indexOf(entry);
    boolean rv = this.entries.remove(entry);
    if (index >= 0) {
      fireIntervalRemoved(this, index, index);
    }
    return rv;
	}

	public int getEntryIndex(Entry anEntry)
	{
		int i = -1;
		try
		{
			for (int index = 0; index < this.entries.size(); index++)
			{
				if (Utility.getValue(anEntry, this.displayNames).equals(
						Utility.getValue(this.entries.get(index), this.displayNames)))
				{
					i = index;
					break;
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		return i;
	}

	public List<Entry> getEntries()
	{
		return entries;
	}
}
