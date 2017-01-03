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
package weiss.MetaAgent;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import weiss.message.*;

/**
 * * Class used for handling messages from both
 * {@link weiss.MetaAgent.MetaAgent MetaAgent} classes and
 * {@link weiss.MetaAgent.Router Router} classes, and routing them to the correct
 * destination. The class implements {@link weiss.MetaAgent.MetaAgent MetaAgent},
 * which is the basis for all of our MAS classes.
 * <p>
 * When the object receives a message, it checks it's own
 * {@link Portal#routingTable Routing Table} for the value found in the
 * {@link weiss.Message.Message#to To} field. If the object is found, it passes
 * the message on to the selected object. If not, the object is passed to its
 * assigned {@link weiss.MetaAgent.Router Router}.
 *
 *
 * @author Adam Young, Teesside University Sch. of Computing
 * @author Scott Taylor, Teesside University Sch.of Computing
 */
public class Portal extends MetaAgent implements Runnable
{

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
    //USER MESSAGE HANDLING
    @Override
    protected void userMsgHandler(UserMessage msg)
    {
        if (routingTable.containsKey(msg.getTo())) //checks if the routing table has address
        {
            System.out.println("Sent to sub-agent");
            this.pushToSubAgent(msg.getTo(), msg);
        }
            else   //if the portal does not have the addressed agent in its routing table... 
            {
                if (superAgent != null)
                    this.pushToSuperAgent(msg.getTo(), msg);
                else
                {
                    try
                    {
                        SysMessage error = new SysMessage(this.getName(), msg.getFrom(), "noAgent");
                        routingTable.get(msg.getFrom()).put(error);
                    } 
                    catch (InterruptedException ex)
                    {
                        Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
    }
    
    protected void pushToSubAgent(String address, Message msg)
    {
        MetaAgent agent = (MetaAgent) routingTable.get(address); //gets the addressed agent
        try
        {
            agent.put((UserMessage) msg); //puts the message onto the agent's blocking queue
        } catch (InterruptedException ex)
        {
            Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void pushToSuperAgent(String to, Message msg)
    {
        try //passes the message to the next MetaAgent in the chain
        {
            superAgent.put(msg);    //puts the message onto the router's blocking queue
        } catch (InterruptedException ex)
        {
            Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //--------------------------------------------------------------------------
    //SYSTEM MESSAGE HANDLING
    
    //--------------------------------------------------------------------------
    //REGISTRATION
    @Override
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
    /**
     * Method to register a subAgent to this MetaAgent
     *
     * @param msg A SysMessage object
     */
    private void registration(SysMessage msg)
    {
        this.localRegistration(msg);
        //if the scope is for router-wide or global AND this registration message did not come from the router
        //it will tell the router to also register this agent
        /*
        if(agent.getScope() <= 1 && !msg.getFrom().equals(superAgent.getName()))
        {
            SysMessage regMsg = new SysMessage(this.getName(), superAgent.getName(),
                    "registration", agent); //creates a new registration SysMessage for the router
            try
            {
                superAgent.put(regMsg); //puts the registration message onto the router's blocking queue
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
         */
    }
    private void localRegistration(SysMessage msg)
    {
        MetaAgent agent = msg.getAgent();   //gets the agent from the message        
        if (routingTable.containsKey(agent.getName()))
        {
            agent.msgHandler(new UserMessage(this.getName(), agent.getName(),
                    "You cannot use this name, please try another."));
        } else
        {
            routingTable.put(agent.getName(), agent); //puts the agent with its name as the key into the routingTable
        }
    }
    
    //--------------------------------------------------------------------------
    //DEREGISTRATION
    private void deregistration(SysMessage msg)
    {
        //To do
    }
    private void localDeregistration(SysMessage msg)
    {
        
    }

}
