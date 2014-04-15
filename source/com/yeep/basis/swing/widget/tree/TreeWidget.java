package com.yeep.basis.swing.widget.tree;

import java.awt.Point;
import java.util.Enumeration;

import javax.swing.JTree;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

@SuppressWarnings("serial")
public class TreeWidget<Entry> extends JTree
{
	public TreeWidget(TreeWidgetModel<Entry> treeModel)
	{
		super(treeModel);
		setRootVisible(false);
	}

	@SuppressWarnings("unchecked")
	@Override
	public TreeWidgetModel<Entry> getModel()
	{
		return (TreeWidgetModel<Entry>) super.getModel();
	}

	/**
	 * Return the TreeNode for the specific location.
	 * Return null if no node is in the position for the given location.
	 * @param point
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TreeWidgetNode<Entry> getTreeNodeForLocation(Point point)
	{
		TreePath path = getPathForRow(getRowForLocation(point.x, point.y));
		return path == null ? null : (TreeWidgetNode<Entry>) (path.getLastPathComponent());
	}

	/**
	 * Get the currently selected node, if no node is selected then return the root node
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public TreeWidgetNode<Entry> getCurrrentSelectedNode()
	{
		return getSelectionPath() == null ? (TreeWidgetNode<Entry>) getModel().getRoot()
				: (TreeWidgetNode<Entry>) (getSelectionPath().getLastPathComponent());
	}

	/**
	 * Add a new node to the current selected path
	 * 
	 * @param node
	 */
	public TreeWidgetNode<Entry> addNode(Entry entry, String[] displayNames, boolean canHaveLeaf)
	{
		return addNode(entry, displayNames, canHaveLeaf, getCurrrentSelectedNode());
	}

	/**
	 * Add a new node to the specified node, if parentNode is set NULL, then the Entry will be
	 * added to the ROOT
	 * 
	 * @param node
	 */
	@SuppressWarnings("unchecked")
	public TreeWidgetNode<Entry> addNode(Entry entry, String[] displayNames, boolean canHaveLeaf,
			TreeWidgetNode<Entry> parentNode)
	{
		if (parentNode == null)
			parentNode = (TreeWidgetNode<Entry>) getModel().getRoot();
		TreeWidgetNode<Entry> childNode = new TreeWidgetNode<Entry>(entry, displayNames, canHaveLeaf);
		((TreeWidgetModel<Entry>) getModel()).insertNodeInto(childNode, parentNode, parentNode.getChildCount());
		scrollPathToVisible(new TreePath(childNode.getPath()));
		return childNode;
	}

	/**
	 * Remove the node which is selected currently.
	 */
	public TreeWidgetNode<Entry> removeNode(TreeWidgetNode<Entry> node)
	{
		if (node != null)
		{
			MutableTreeNode parent = (MutableTreeNode) node.getParent();
			if (parent != null)
				((DefaultTreeModel) getModel()).removeNodeFromParent(node);
		}

		return node;
	}

	/**
	 * Remove the node which is selected currently.
	 */
	public TreeWidgetNode<Entry> removeCurrentSeletedNode()
	{
		return removeNode(getCurrrentSelectedNode());
	}

	public void visitAllNodes()
	{
		TreeNode root = (TreeNode) getModel().getRoot();
		visitAllNodes(root);
	}

	@SuppressWarnings("unchecked")
	public void visitAllNodes(TreeNode node)
	{
		// node is visited exactly once
		// you can do your things about this node,such as:
		this.makeVisible(new TreePath(((DefaultTreeModel) getModel()).getPathToRoot(node)));

		if (node.getChildCount() >= 0)
		{
			for (Enumeration e = node.children(); e.hasMoreElements();)
			{
				TreeNode n = (TreeNode) e.nextElement();
				System.out.println(n.toString());
				visitAllNodes(n);
			}
		}
	}

}
