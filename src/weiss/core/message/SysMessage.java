/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiss.core.message;

import weiss.core.agent.MetaAgent;

/** Class extending the {@link Message Message} class, to be used for system message transmission.
 * This type of message can be read by all MetaAgents, and contains instructions rather than text.
 * <p>
 * The alternative of this message type is {@link UserMessage UserMessage}.
 *
 * @author Adam Young, Teesside University Sch. of Computing
 * @author Scott Taylor, Teesside University Sch. of Computing
 */
public class SysMessage extends Message
{
    MetaAgent agent;
    
    /**
     * Constructor for the SysMessage class.
     * @param f String of message sender.
     * @param t String of message receiver.
     * @param m String of message contents.
     * @param a
     */
    public SysMessage(String f, String t, String m, MetaAgent a)
    {
        super(f, t, m);
        agent = a;
        //Needs additional fields adding.
    }
    
    public SysMessage(String f, String t, String m)
    {
        super(f, t, m);
    }
    
    //--------------------------------------------------------------------------
    //GETTERS
    public MetaAgent getAgent()
    {
        return agent;
    }
    
    @Override
    public String toString()
    {
        return super.toString() + "\nAgent: " + agent.getName();
    }
}
