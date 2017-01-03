/* 
 * Copyright (C) 2017 Adam Young
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Weiss.Manager;

import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import weiss.MetaAgent.MetaAgent;

/**
 *
 * @author Adam Young, Teesside University Sch. of Computing
 */
public class TreePane
{

    private TreeNode rootNode;
    private DefaultTreeModel treeModel;
    private static JTree tree;

    public TreePane()
    {
        rootNode = new TreeNode("Weiss");
        treeModel = new DefaultTreeModel(rootNode);
        treeModel.addTreeModelListener(new TreeModelListener()
        {
            @Override
            public void treeNodesChanged(TreeModelEvent e)
            {
            }

            @Override
            public void treeNodesInserted(TreeModelEvent e)
            {
            }

            @Override
            public void treeNodesRemoved(TreeModelEvent e)
            {
            }

            @Override
            public void treeStructureChanged(TreeModelEvent e)
            {
            }

        });

        tree = new JTree(treeModel);
        tree.addTreeSelectionListener(new TreeSelectionListener()
        {
            @Override
            public void valueChanged(TreeSelectionEvent e)
            {
                TreeNode treeNode = (TreeNode) tree.getSelectionPath().getLastPathComponent();

                switch (treeNode.getLevel())
                {
                    case 0:
                        WeissManager.getAgentSelectBtn().setText("New Router");
                        break;
                    case 1:
                        WeissManager.getAgentSelectBtn().setText("New Portal");
                        break;
                    case 2:
                        WeissManager.getAgentSelectBtn().setText("New Agent");
                        break;
                    case 3:
                        WeissManager.getAgentSelectBtn().setText("View Agent");
                        break;
                    default:
                        WeissManager.getAgentSelectBtn().setText("Select an Item");
                        break;
                }
            }
        });

        tree.setEditable(false);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(true);
    }

    public JTree getTree()
    {
        return tree;
    }

    public TreeNode addNode(MetaAgent child)
    {
        TreeNode parentNode = null;
        TreePath parentPath = tree.getSelectionPath();

        if (parentPath == null)
        {
            parentNode = rootNode;
        } else
        {
            parentNode = (TreeNode) parentPath.getLastPathComponent();
        }
        child.setSuperAgent(parentNode.getAgentRef());

        return addNode(parentNode, child, true);
    }

    public TreeNode addNode(DefaultMutableTreeNode parent,
            MetaAgent child,
            boolean shouldBeVisible)
    {

        TreeNode childNode
                = new TreeNode(child.getName(), child);

        treeModel.insertNodeInto(childNode, parent,
                parent.getChildCount());

        if (shouldBeVisible)
        {
            tree.scrollPathToVisible(new TreePath(childNode.getPath()));
        }

        child.start();

        return childNode;
    }
}
