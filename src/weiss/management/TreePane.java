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
package weiss.management;

import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.event.*;
import javax.swing.tree.*;
import weiss.core.agent.MetaAgent;
import weiss.core.agent.Portal;
import weiss.core.agent.Router;

/**
 *  Class which forms the main UI control of the program. Implements custom renderers,
 * tree models and tree nodes. Implemented by {@link WeissManager WeissManager}.
 * 
 * @author Adam Young, Teesside University Sch. of Computing
 */
public class TreePane {

    private DefaultTreeModel treeModel;
    private TreeNode rootNode;
    private static JTree tree;
    private TreeNode treeNode;
    private WeissManager manager;

    /**
     * A Constructor to create the JTree navigation pane.
     * @param manager The attached manager to pull information from.
     */
    public TreePane(WeissManager manager) {
        this.manager = manager;
        this.buildTree();
    }
    
    /**
     * All-round method to create the JTree required by the program. Implements 
     * TreeSelectionListeners to change button text in WeissManager.
     * @param weissManager The main window to tie the TreePane to.
     * @return A JTree object to implement into a JFrame/JPanel.
     */
    private JTree buildTree( ) {
        DefaultTreeCellRenderer render = new DefaultTreeCellRenderer();

        rootNode = new TreeNode("Weiss");
        treeModel = new DefaultTreeModel(rootNode);

        tree = new JTree(treeModel);
        tree.addTreeSelectionListener(new TreeSelectionListener() {
            
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                TreeNode treeNode = (TreeNode) tree.getSelectionPath().getLastPathComponent();                   
                
                switch (treeNode.getLevel()) {
                    case 0:
                        manager.getAgentSelectBtn().setText("New Router");
                        break;
                    case 1:
                        manager.getAgentSelectBtn().setText("New Portal");
                        break;
                    case 2:
                        manager.getAgentSelectBtn().setText("New Agent");
                        break;
                    case 3:
                        manager.getAgentSelectBtn().setText("View Agent");
                        break;
                    default:
                        manager.getAgentSelectBtn().setText("Select an Item");
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

    /**
     * Method to get the JTree object.
     * @return A constructed JTree object.
     */
    public JTree getTree() {
        return tree;
    }

    /**
     * Method to create a TreeNode for the JTree to use.
     * @param child The MetaAgent to be added to the tree.
     * @param image The image to use for the image.
     * @return A TreeNode object with a MetaAgent assigned.
     */
    public TreeNode addNode(MetaAgent child, ImageIcon image) {
        TreeNode parentNode = null;
        TreePath parentPath = tree.getSelectionPath();

        if (parentPath == null) {
            parentNode = rootNode;
        } else {
            parentNode = (TreeNode) parentPath.getLastPathComponent();
        }
        if (!(child instanceof Router)) {
            child.setSuperAgent(parentNode.getAgentRef());
        }

        return addNode(parentNode, child, true, image);
    }

    /**
     * A method to assign the parent of the node, and the visibility.
     *
     * @param parent The parent TreeNode of the initial child node.
     * @param child The child node passed from the first method.
     * @param shouldBeVisible A boolean value stating the visibility of the node
     * @param image The image icon passed from the first method.
     * @return A constructed TreeNode to use with JTree.
     */
    private TreeNode addNode(DefaultMutableTreeNode parent,
            MetaAgent child,
            boolean shouldBeVisible,
            ImageIcon image) {

        TreeNode childNode
                = new TreeNode(child.getName(), child, image);

        treeModel.insertNodeInto(childNode, parent,
                parent.getChildCount());

        if (shouldBeVisible) {
            tree.scrollPathToVisible(new TreePath(childNode.getPath()));
        }
        child.start();

        return childNode;
    }

    /**
     * A custom TreeCellRenderer to assign custom names, labels and images in
     * an easy to use way.
     */
    private class WeissTreeCellRenderer implements TreeCellRenderer {

        private final JLabel label;

        /**
         * Constructor to instantiate a new JLabel.
         */
        WeissTreeCellRenderer() {
            label = new JLabel();
        }

        /**
         * Method to create the custom tree cell, to assign the image, background/foreground
         * colour and name. The renderer is always running for label changes such as 
         * selection highlighting.
         * @param tree The assigned tree.
         * @param value the passed object (A treeNode).
         * @param selected Boolean for if the node is selected.
         * @param expanded Boolean for if the node is expanded.
         * @param leaf Boolean for if the node is a leaf.
         * @param row Integer for the row of the object.
         * @param hasFocus Boolean for if the node has focus.
         * @return A JLabel used in the JTree node.
         */
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value,
                boolean selected, boolean expanded, boolean leaf, int row,
                boolean hasFocus) {
            treeNode = ((TreeNode) value);
            ImageIcon image;

            if (treeNode.getImage() != null) {
                image = treeNode.getImage();
            } else {
                image = new ImageIcon("Images/weiss20px.png");
            }
            label.setIcon(image);

            if (selected) {
                label.setForeground(Color.red);
            } else {
                label.setForeground(Color.black);
            }

            if (treeNode.getName() != null) {
                label.setText(treeNode.getName());
            } else {
                label.setText("Weiss");
            }
            return label;
        }
    }

}
