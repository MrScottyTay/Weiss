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

import weiss.core.message.Monitorable;
import weiss.core.message.Message;
import weiss.core.message.UserMessage;
import weiss.core.message.SysMessage;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import weiss.core.message.ReplyMessage;
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
 * {@link weiss.Message.Message#to To} field. If the object is found, it passes
 * the message on to the selected object. If not, the object is passed to its
 * assigned {@link weiss.core.agent.Router Router}.
 *
 *
 * @author Adam Young, Teesside University Sch. of Computing
 * @author Scott Taylor, Teesside University Sch.of Computing
 */
public class Portal extends MetaAgent implements Runnable, Monitorable
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
    //MESSAGE HANDLING
    //--------------------------------------------------------------------------
    @Override
    protected void userMsgHandler(UserMessage msg)
    {
        if (routingTable.containsKey(msg.getTo())) //checks if the routing table has address
        {
            this.pushToSubAgent(msg);
        }
            else   //if the portal does not have the addressed agent in its routing table... 
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
    
    protected void pushToSubAgent(Message msg)
    {
        MetaAgent agent = (MetaAgent) routingTable.get(msg.getTo()); //gets the addressed agent
        try
        {
            agent.put(msg); //puts the message onto the agent's blocking queue
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
            this.sysMsgHandler((SysMessage) msg); //it gets sent to the handler specifically for SysMessages
        }
        else if(msg instanceof RouterMessage)
        {
            this.routerMsgHandler((RouterMessage) msg); //gets sent to the handler specifically for RouterMessages
        }
        else if(msg instanceof ReplyMessage)
        {
            this.replyMsgHandler((ReplyMessage) msg);   //gets sent to the handler specifically for ReplyMessages
        }
        else
        {
            this.userMsgHandler((UserMessage) msg); //gets sent to the handler specifically for UserMessages
        }
    }
    
    protected void routerMsgHandler(RouterMessage msg)
    {
        //a Router Message should never reach a portal, this is here so that msgHandler doesn't need to be rewritten in Router
        //this may change though
    }
    
    protected void sysMsgHandler(SysMessage msg)
    {
        String[] s = msg.getMsg().split(" ");   //splits the msg up into words, future proofing in case commands become more complicated than just one word
        
        switch (s[0])   //looks at the first word which will ALWAYS show what kind of command it is
        {
            case "reg":
                this.registration(msg);
                break;
            case "dereg":
                this.deregistration(msg);
                break;
            case "NameCheck":
                SysMessage sMsg = new SysMessage(msg.getFrom(), getSuperAgent().getName(), msg.getMsg(), msg.getAgent());
                this.pushToSuperAgent(sMsg);
                break;
        }
    }
    
    //--------------------------------------------------------------------------
    //REGISTRATION
    //--------------------------------------------------------------------------
    
    /**
     * Method to register a subAgent to this MetaAgent
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
    
    //--------------------------------------------------------------------------
    //MetaAgent Creation
    //--------------------------------------------------------------------------
    
    private void insertMetaAgent(MetaAgent a)
    {
        a.setSuperAgent(this);
        routingTable.put(a.getName(), a);
    }
    
    //The End-User could create a creation method of their own Agents that extend MetaAgent here
    //use the newPortal() and newRouter() methods in Router.java as an example of this
}
