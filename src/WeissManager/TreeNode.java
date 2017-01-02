package WeissManager;


import javax.swing.tree.DefaultMutableTreeNode;
import weiss.agent.MetaAgent;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Adam Young
 */
public class TreeNode extends DefaultMutableTreeNode
{
    private MetaAgent agentRef;
    public TreeNode(String name, MetaAgent agent)
    {
        super(name);
        agentRef = agent;
    }
    
    public TreeNode(String name)
    {
        super(name);
    }
    
    public MetaAgent getAgentRef()
    {
        return agentRef;
    }
}
