package com.yeep.basis.swing.widget.tree;

import javax.swing.tree.DefaultMutableTreeNode;

import com.yeep.basis.util.Utility;

@SuppressWarnings("serial")
public class TreeWidgetNode<Entry> extends DefaultMutableTreeNode
{
	private Entry entry;
	private String[] displayNames;
	private boolean canHaveLeaf; // can have leaves or not

	// Constructor
	/**
	 * Create a root node
	 */
	protected TreeWidgetNode()
	{
		super("root");
		this.canHaveLeaf = true;
	}

	public TreeWidgetNode(Entry entry, String[] displayNames, boolean canHaveLeaf)
	{
		super(entry);
		this.entry = entry;
		this.displayNames = displayNames;
		this.canHaveLeaf = canHaveLeaf;
	}

	/**
	 * Return the relate entry object
	 * 
	 * @return
	 */
	public Entry getEntry()
	{
		return entry;
	}

	/**
	 * Returns the result of sending <code>toString()</code> to this node's user object, or null if this node has no
	 * user object.
	 * 
	 * @see #getUserObject
	 */
	public String toString()
	{
		if (this.entry instanceof String)
			return this.entry.toString();
		else
			return Utility.getValue(this.entry, this.displayNames);
	}

	public boolean isCanHaveLeaf()
	{
		return canHaveLeaf;
	}

	/**
	 * Return true if the node has children
	 * @return
	 */
	public boolean hasChildren()
	{
		return getChildCount() > 0;
	}
}
