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

import weiss.core.message.Message;
import weiss.core.message.RouterMessage;
import weiss.core.message.UserMessage;
import weiss.core.message.ReplyMessage;
import weiss.core.message.SysMessage;
import weiss.manager.NodeMonitor;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 * An abstract class detailing the construction of a MetaAgent object, to be implemented
 * by the end user.
 * This implementation of a meta agent extends 
 * a <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/LinkedBlockingQueue.html">LinkedBlockingQueue</a>.
 * 
 * @author Scott Taylor, Teesside University Sch. of Computing
 * @author Adam Young, Teesside University Sch. of Computing
 */
public abstract class MetaAgent extends WeissBase implements Runnable, Monitorable
{
    private int scope;  //0 = global, 1 = router-wide, 2 = portal-wide
    private ArrayList<WeissBase> monitors;
    private WeissBase client;
    private MetaAgent superAgent;
    private ImageIcon image;
    
    /**
     * Constructor to initialise a MetaAgetn object.
     * @param name {@link weiss.core.agent.Portal Portal} belonging to MetaAgent.
     * @param superAgent Scope of the MetaAgent.
     * 
     */
    public MetaAgent(String name, MetaAgent superAgent)
    {
        super(name);
        this.monitors = new ArrayList();
        this.client = null;
        this.setSuperAgent(superAgent);
        this.scope = 0;
    }
    /**
     * Constructor to initialise a MetaAgent object, and setting the scope.
     * @param name Name of MetaAgent.
     * @param superAgent {@link weiss.core.agent.Portal Portal} belonging to MetaAgent.
     * @param scope Scope of the MetaAgent.
     */
    public MetaAgent(String name,MetaAgent superAgent, int scope)
    {
        super(name);
        
        this.monitors = new ArrayList();
        this.client = null;
        this.superAgent = superAgent;
        this.scope = scope;        
    }
    
    //--------------------------------------------------------------------------
    //GETTERS
    /**
     * Getter for name variable.
     * @return name String.
     */
    
    /**
     * Getter for {@link weiss.core.agent.Portal Portal} object.
     * @return {@link weiss.core.agent.Portal Portal} pointer.
     */
    public MetaAgent getSuperAgent()
    {
        return superAgent;
    }
    /**
     * Method to set scope of MetaAgent
     * @return Integer relating to the scope of the MetaAgent
     */
    public int getScope()
    {
        return scope;
    }
    
    //--------------------------------------------------------------------------
    //SETTERS
    
    /*?*?*?*?*?*?*?*?*?*
    //Can this be a message type rather than a string concat?
    */
    public void setName(String[] reply)//Reply about name change is handled here and determined whether name can be changed or not
    {
        switch (reply[2])
        {
            case "Approved":
                this.setName(reply[3]);
                break;
            case "Declined":
                break;
        }
    }
    /**
     * Setter for {@link weiss.core.agent.Portal Portal} object.
     * @param superAgent {@link weiss.core.agent.Portal Portal} object.
     */
    public final void setSuperAgent(MetaAgent superAgent)
    {   
        this.superAgent = superAgent;
        if(this.superAgent != null)
            pushToSuperAgent(new SysMessage(this.getName(), superAgent.getName(),
                    "reg", this));
    }
    
    /**
     * Method to set scope of MetaAgent
     * @param scope Integer relating to the scope of the MetaAgent
     */
    public final void setScope(int scope)
    {
        this.scope = scope;
        
        //need to change registration and scope
    }
    
    //--------------------------------------------------------------------------
    //INTERFACE METHODS
    @Override
    public void addNodeMonitor(NodeMonitor nodeMonitor)
    {
        this.monitors.add(nodeMonitor);
    }
    @Override
    public void removeNodeMonitor(NodeMonitor nodeMonitor)
    {
        this.monitors.remove(nodeMonitor);
    }
    public void updateNodeMonitor(Message msg)
    {
        for(WeissBase node : monitors)
        {
            try
            {
                if(node != null)
                    node.put(msg);
                else
                    System.out.println("No node monitor attached...");
            } 
            catch (InterruptedException ex)
            {
                Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    /**
     * Method to add a client that interacts with the MetaAgent. Only one client
     * can be active at any one time.
     * @param client An object of type WeissBase. Current implementation uses
     * {@link weiss.manager.Client Client}.
     */
    @Override
    public void addClient(NodeMonitor client)
    {
        this.client = client;
    }
    @Override
    public void removeClient(NodeMonitor client)
    {
        this.client = null;
    }
    public void updateClient(Message msg)
    {
        try
        {
            if(client != null)
                client.put(msg);
            else
                System.out.println(msg.toString());
        } 
        catch (InterruptedException ex)
        {
            Logger.getLogger(MetaAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public boolean hasClient()
    {
        return this.client != null;
    }
    @Override
    public boolean hasMonitor()
    {
        return !monitors.isEmpty();
    }
    
    //--------------------------------------------------------------------------
    //CLASS SPECIFIC METHODS
    protected void pushToSuperAgent(Message msg)
    {
        try //passes the message to the next MetaAgent in the chain
        {
            if(superAgent != null)
                superAgent.put(msg);    //puts the message onto the router's blocking queue              
        } catch (InterruptedException ex)
        {
            Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void msgHandler(Message msg)
    {
        this.updateNodeMonitor(msg);
        String to = msg.getTo();   //puts the address of the message into a local variable
        String from = msg.getFrom();

        if (msg instanceof SysMessage)
            this.sysMsgHandler((SysMessage) msg); //it gets sent to the handler specifically for SysMessages
        else if(msg instanceof RouterMessage)
            this.RouterMsgHandler((RouterMessage) msg);//gets sent to the handler specifically for RouterMessages
        else if(msg instanceof ReplyMessage)
            this.ReplyMsgHandler((ReplyMessage) msg);//gets sent to the handler specifically for ReplyMEssages
        else
            this.userMsgHandler((UserMessage) msg);
    }

    protected void ReplyMsgHandler(ReplyMessage msg)
    {
        String[] reply = msg.getMsg().split(" ");
        switch(reply[1])
        {
            case "ReturnToSender":
                //handling for an undelivered message
                break;
            case "Name":
                this.setName(reply);
                break;
        }
    }
    
    protected void RouterMsgHandler(RouterMessage msg)
    {
        //Do something
    } 
    public void sendMessage(String to, String message)
    {
        pushToSuperAgent(new UserMessage(this.getName(), to, message));
    }

}
