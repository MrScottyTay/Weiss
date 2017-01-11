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
package weiss.core.agent;

import weiss.management.nodeMonitor.Monitorable;
import weiss.core.message.Message;
import weiss.core.message.UserMessage;
import weiss.core.message.SysMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import weiss.core.message.RouterMessage;

/**
 * * Class used for handling messages from both
 * {@link weiss.core.agent.MetaAgent MetaAgent} classes and
 * {@link weiss.core.agent.Router Router} classes, and routing them to the correct
 * destination. The class implements {@link weiss.core.agent.MetaAgent MetaAgent},
 * which is the basis for all of our MAS classes.
 * <p>
 * When the object receives a message, it checks it's own
 * {@link Portal#routingTable Routing Table} for the value found in the
 * {@link weiss.core.message.Message#to To} field. If the object is found, it passes
 * the message on to the selected object. If not, the object is passed to its
 * assigned {@link weiss.core.agent.Router Router}.
 *
 *
 * @author Adam Young, Teesside University Sch. of Computing
 * @author Scott Taylor, Teesside University Sch.of Computing
 */
public class Portal extends MetaAgent implements Runnable, Monitorable
{
    /**
     * The routing table is a HashMap of MetaAgents, storing sub-agents for 
     * reference later.
     */
    protected final Map<String, MetaAgent> routingTable;

    /**
     * Constructor for the Portal class.
     *
     * @param name String of name variable.
     * @param superAgent MetaAgent pointer of Router.
     */
    public Portal(String name, MetaAgent superAgent)
    {
        super(name, superAgent);
        this.routingTable = new HashMap<>();
    }
    /**
     *  Constructor for the Portal class.
     * 
     * @param name String of name variable.
     * @param superAgent MetaAgent pointer of Router.
     * @param scope Integer detailing scope of Router.
     */
    public Portal(String name, MetaAgent superAgent, int scope)
    {
        super(name, superAgent, scope);
        this.routingTable = new HashMap<>();
    }

    //--------------------------------------------------------------------------
    //MESSAGE HANDLING
    //--------------------------------------------------------------------------

    /**
     * Handler for the UserMessage type. The method checks the routing table for the
     * specified Agent, and if present it pushes it to the assigned subAgent. Otherwise,
     * the message is passed to the superAgent. If no superAgent is assigned, an error message
     * is sent back to the original sender.
     * @param msg A UserMessge object passed from the message handler
     */
    @Override
    protected void userMsgHandler(UserMessage msg)
    {
        if (routingTable.containsKey(msg.getTo())) 
        {
            this.pushToSubAgent(msg);
        }
            else   
            {
                if (getSuperAgent() != null)
                    this.pushToSuperAgent(msg);
                else
                {
                    MetaAgent errorTarget = routingTable.get(msg.getFrom());
                    SysMessage error = new SysMessage(this.getName(), errorTarget.getName(), "noAgent");
                    pushToSubAgent(error);
                }
            }    
    }
    
    /**
     * Method to get the relevant MetaAgent, and then attempt to push the message 
     * onto it's linked blocking queue.
     * @param msg A Message object to push to the subAgent.
     */
    protected void pushToSubAgent(Message msg)
    {
        MetaAgent agent = (MetaAgent) routingTable.get(msg.getTo()); 
        try
        {
            agent.put(msg); 
        } catch (InterruptedException ex)
        {
            Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void msgHandler(Message msg)
    {
        super.updateNodeMonitor(msg);

        if (msg instanceof SysMessage)
        {
            this.sysMsgHandler((SysMessage) msg); 
        }
        else if(msg instanceof RouterMessage)
        {
            this.routerMsgHandler((RouterMessage) msg); 
        }
        else
        {
            this.userMsgHandler((UserMessage) msg); 
        }
    }
    
    /**
     * Method to handle RouterMessages. If the message reaches a portal, an error
     * is thrown, as the message should stop at the router level.
     * @param msg A RouterMessage to be handled.
     */
    protected void routerMsgHandler(RouterMessage msg)
    {
        throw new IllegalArgumentException("Router message passed to portal");
    }
    
    /**
     * Method to handle SysMessages. The main implementation of this is to handle
     * MetaAgent registration/de-registration.
     * @param msg A SysMessage object passed from the message handler.
     */
    protected void sysMsgHandler(SysMessage msg)
    {
        switch (msg.getMsg())   
        {
            case "reg":
                this.registration(msg);
                break;
            case "dereg":
                this.deregistration(msg);
                break;
        }
    }
    
    //--------------------------------------------------------------------------
    //REGISTRATION
    //--------------------------------------------------------------------------
    
    /**
     * Method to register a subAgent to this MetaAgent. The method adds the passed
     * MetaAgent to the routingTable, and then pushes it to the superAgent.
     *
     * @param msg A SysMessage object
     */
    private void registration(SysMessage msg)
    {
        routingTable.put(msg.getAgent().getName(), msg.getAgent());
        SysMessage sMsg = new SysMessage(msg.getAgent().getName(), getSuperAgent().getName(), "reg", this);
        pushToSuperAgent(sMsg);
    }
    
    private void deregistration(SysMessage msg)
    {
        //To do
    }
}
