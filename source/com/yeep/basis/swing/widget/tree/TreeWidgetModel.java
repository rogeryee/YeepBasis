package com.yeep.basis.swing.widget.tree;

import javax.swing.tree.DefaultTreeModel;

@SuppressWarnings("serial")
public class TreeWidgetModel<Entry> extends DefaultTreeModel
{
	/**
	 * Default Constructor, create a default root node for the tree widget
	 */
	public TreeWidgetModel()
	{
		super(new TreeWidgetNode<Entry>());
	}
}
