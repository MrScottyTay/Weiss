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


import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import weiss.core.agent.MetaAgent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Extended class to allow treeNodes to store a reference to a {@link weiss.core.agent.MetaAgent MetaAgent}, for 
 * GUI management in {@link weiss.management.TreePane TreePane}. Also allows custom images and names to be changed at will.
 * @author Adam Young, Teesside University Sch. of Computing
 */
public class TreeNode extends DefaultMutableTreeNode
{
    private MetaAgent agentRef;
    private ImageIcon image;
    private String name;
    
    /**
     * Constructor to create a TreeNode, to be used in the {@link weiss.management.TreePane TreePane}.
     * @param name String variable to set the treeNode name.
     * @param agent MetaAgent reference to attach to the treeNode.
     * @param image ImageIcon to assign to the treeNode.
     */
    public TreeNode(String name, MetaAgent agent, ImageIcon image)
    {
        super(name);
        this.name = name;
        agentRef = agent;
        this.image = image;
    }
    
    /**
     * Generic constructor to create a basic treeNode.
     * @param name String variable to set the treeNode name.
     */
    public TreeNode(String name)
    {
        super(name);
    }
    
    /**
     * Method to pull the attached {@link weiss.core.agent.MetaAgent MetaAgent} from the treeNode
     * @return A MetaAgent to be assigned to the treeNode
     */
    public MetaAgent getAgentRef()
    {
        return agentRef;
    }
    
    /**
     * Method to return the assigned image from the treeNode.
     * @return An ImageIcon of the assigned image.
     */
    public ImageIcon getImage()
    {
        return image;
    }
    
    /**
     * Method to return the name of the treeNode.
     * @return A String variable of the assigned name.
     */
    public String getName()
    {
        return name;
    }
}
