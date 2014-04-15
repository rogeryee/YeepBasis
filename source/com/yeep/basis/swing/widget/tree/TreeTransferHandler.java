package com.yeep.basis.swing.widget.tree;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.TransferHandler;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 * Transfer class for supporting the DnD actions of TreeWidget 
 */
@SuppressWarnings("serial")
public class TreeTransferHandler extends TransferHandler
{
	@SuppressWarnings("unchecked")
	private TreeWidget treeWidget;

	// Constructor
	@SuppressWarnings("unchecked")
	public TreeTransferHandler(TreeWidget treeWidget)
	{
		this.treeWidget = treeWidget;
	}

	@Override
	public int getSourceActions(JComponent c)
	{
		return TransferHandler.MOVE;
	}

	public boolean canImport(TransferHandler.TransferSupport info)
	{
		info.setShowDropLocation(true);
		return true;
	}

	@SuppressWarnings("unchecked")
	protected Transferable createTransferable(JComponent c)
	{
		return new TransferableTreePath(((TreeWidget) c).getSelectionPath());
	}

	@SuppressWarnings("unchecked")
	public boolean importData(TransferHandler.TransferSupport info)
	{
		try
		{
			Transferable tr = info.getTransferable();
			DataFlavor[] flavors = tr.getTransferDataFlavors();

			for (int i = 0; i < flavors.length; i++)
			{
				if (tr.isDataFlavorSupported(flavors[i]))
				{
					TreePath oldPath = (TreePath) tr.getTransferData(flavors[i]);
					DefaultMutableTreeNode oldNode = (DefaultMutableTreeNode) oldPath.getLastPathComponent();

					((DefaultTreeModel) this.treeWidget.getModel()).removeNodeFromParent(oldNode);

					JTree.DropLocation dropLocation = (JTree.DropLocation) info.getDropLocation();
					TreePath newPath = dropLocation.getPath();
					TreeWidgetNode newNode = (TreeWidgetNode) newPath.getLastPathComponent();
					((DefaultTreeModel) this.treeWidget.getModel()).insertNodeInto(oldNode, newNode, newNode.getChildCount());
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}

		return true;
	}

	protected void exportDone(JComponent c, Transferable data, int action)
	{
		try
		{
			DataFlavor[] flavors = data.getTransferDataFlavors();

			for (int i = 0; i < flavors.length; i++)
			{
				if (data.isDataFlavorSupported(flavors[i]))
				{
					TreePath p = (TreePath) data.getTransferData(flavors[i]);
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) p.getLastPathComponent();

					((DefaultTreeModel) this.treeWidget.getModel()).removeNodeFromParent(node);
				}
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}

	// Inner class
	/**
	 * Transferable tree path
	 */
	class TransferableTreePath implements Transferable
	{
		private DataFlavor TREE_PATH_FLAVOR = new DataFlavor(TreePath.class, "Tree Path");
		private DataFlavor flavors[] = { TREE_PATH_FLAVOR };
		private TreePath treePath;

		// Constructor
		public TransferableTreePath(TreePath treePath)
		{
			this.treePath = treePath;
		}

		public synchronized DataFlavor[] getTransferDataFlavors()
		{
			return this.flavors;
		}

		public boolean isDataFlavorSupported(DataFlavor flavor)
		{
			return (flavor.getRepresentationClass() == TreePath.class);
		}

		public synchronized Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException
		{
			if (isDataFlavorSupported(flavor))
				return treePath;
			else
				throw new UnsupportedFlavorException(flavor);
		}
	}
}
