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
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.event.*;
import javax.swing.tree.*;
import weiss.core.agent.MetaAgent;
import weiss.core.agent.Router;

/**
 *
 * @author Adam Young, Teesside University Sch. of Computing
 */
public class TreePane
{

    private DefaultTreeModel treeModel;
    private TreeNode rootNode;
    private static JTree tree;

    public TreePane(WeissManager manager)
    {
        this.buildTree(manager);
    }

    private JTree buildTree(WeissManager weissManager)
    {
        WeissManager manager = weissManager;
        DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();

        rootNode = new TreeNode("Weiss");
        treeModel = new DefaultTreeModel(rootNode);

        tree = new JTree(treeModel);
        tree.addTreeSelectionListener(new TreeSelectionListener()
        {

            private JButton getAgentSelectBtn()
            {
                return manager.getAgentSelectBtn();
            }

            @Override
            public void valueChanged(TreeSelectionEvent e)
            {
                TreeNode treeNode = (TreeNode) tree.getSelectionPath().getLastPathComponent();

                switch (treeNode.getLevel())
                {
                    case 0:
                        this.getAgentSelectBtn().setText("New Router");
                        break;
                    case 1:
                        this.getAgentSelectBtn().setText("New Portal");
                        break;
                    case 2:
                        this.getAgentSelectBtn().setText("New Agent");
                        break;
                    case 3:
                        this.getAgentSelectBtn().setText("View Agent");
                        break;
                    default:
                        this.getAgentSelectBtn().setText("Select an Item");
                        break;
                }
            }
        });

        tree.setEditable(false);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(true);
        tree.setCellRenderer(new WeissTreeCellRenderer());

        return tree;
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
        } 
        else
        {
            parentNode = (TreeNode) parentPath.getLastPathComponent();
        }
        if(!(child instanceof Router))
            child.setSuperAgent(parentNode.getAgentRef());

        return addNode(parentNode, child, true, image);
    }

    private TreeNode addNode(DefaultMutableTreeNode parent,
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

        private final JLabel label;

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
            {
                image = o.getImage();
            } else
            {
                image = new ImageIcon("Images/weiss20px.png");
            }
            label.setIcon(image);

            if (selected)
            {
                label.setForeground(Color.red);
            } else
            {
                label.setForeground(Color.black);
            }

            if (o.getName() != null)
            {
                label.setText(o.getName());
            } else
            {
                label.setText("Weiss");
            }

            return label;
        }
    }

}
