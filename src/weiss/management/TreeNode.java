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
 *
 * @author Adam Young, Teesside University Sch. of Computing
 */
public class TreeNode extends DefaultMutableTreeNode
{
    private MetaAgent agentRef;
    private ImageIcon image;
    private String name;

    /**
     *
     * @param name
     * @param agent
     * @param image
     */
    public TreeNode(String name, MetaAgent agent, ImageIcon image)
    {
        super(name);
        this.name = name;
        agentRef = agent;
        this.image = image;
    }
    
    /**
     *
     * @param name
     */
    public TreeNode(String name)
    {
        super(name);
    }
    
    /**
     *
     * @return
     */
    public MetaAgent getAgentRef()
    {
        return agentRef;
    }
    
    /**
     *
     * @return
     */
    public ImageIcon getImage()
    {
        return image;
    }
    
    /**
     *
     * @return
     */
    public String getName()
    {
        return name;
    }
}
