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
package weiss.manager;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import weiss.core.agent.MetaAgent;

/**
 *
 * @author Adam Young, Teesside University Sch. of Computing
 */
public class TreePane
{

    private TreeNode rootNode;
    private DefaultTreeModel treeModel;
    private static JTree tree;
    private DefaultTreeCellRenderer render;

    public TreePane()
    {
        render = new DefaultTreeCellRenderer();
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
        tree.setCellRenderer(new WeissTreeCellRenderer());
    }

    public JTree getTree()
    {
        return tree;
    }

    public TreeNode addNode(MetaAgent child, ImageIcon image)
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

        return addNode(parentNode, child, true, image);
    }

    public TreeNode addNode(DefaultMutableTreeNode parent,
            MetaAgent child,
            boolean shouldBeVisible,
            ImageIcon image)
    {

        TreeNode childNode
                = new TreeNode(child.getName(), child, image);

        treeModel.insertNodeInto(childNode, parent,
                parent.getChildCount());

        if (shouldBeVisible)
        {
            tree.scrollPathToVisible(new TreePath(childNode.getPath()));
        }
        child.start();

        return childNode;
    }

    private class WeissTreeCellRenderer implements TreeCellRenderer
    {

        private JLabel label;

        WeissTreeCellRenderer()
        {
            label = new JLabel();
        }

        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean selected, boolean expanded, boolean leaf, int row,
                boolean hasFocus)
        {
            TreeNode o = ((TreeNode) value);
            ImageIcon image;

            if (o.getImage() != null)
                image = o.getImage();
            else
                image = new ImageIcon("Images/weiss20px.png");
            label.setIcon(image);
            
            if(selected)
                label.setForeground(Color.red);
            else
                label.setForeground(Color.black);
            
            if(o.getName() != null)
                label.setText(o.getName());
            else
                label.setText("Weiss");
            return label;
        }
    }

}

