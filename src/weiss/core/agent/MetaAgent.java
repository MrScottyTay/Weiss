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

import weiss.core.message.*;
import weiss.manager.NodeMonitor;
import java.util.concurrent.LinkedBlockingQueue;
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
public abstract class MetaAgent extends LinkedBlockingQueue implements Runnable, Monitorable
{
    private String name;
    private int scope;  //0 = global, 1 = router-wide, 2 = portal-wide
    private NodeMonitor monitor;
    private WeissBase client;
    MetaAgent superAgent;
    private ImageIcon image;
    
    /**
     * Constructor to initialise a MetaAgetn object.
     * @param name {@link weiss.core.agent.Portal Portal} belonging to MetaAgent.
     * @param superAgent Scope of the MetaAgent.
     * 
     */
    public MetaAgent(String name, MetaAgent superAgent)
    {
        super();
        this.name = name;
        this.client = null;
        this.setSuperAgent(superAgent);
        this.scope = 0;
        
        /*  this is what I was talking about earlier, and also what simon showed me one on one
            although I'm now thinking, how can we implement a threadpool with this
        Thread thread = new Thread()
        {
          public void run()
          {
              boolean forever = true;
              while(forever == true)
              {
                  msgHandler(take());   //if theres a message in the queue, it will take it and put it into the msghandler
              }
          }
        };
        thread.run();
        */
    }
    /**
     * Constructor to initialise a MetaAgent object, and setting the scope.
     * @param name Name of MetaAgent.
     * @param superAgent {@link weiss.core.agent.Portal Portal} belonging to MetaAgent.
     * @param scope Scope of the MetaAgent.
     */
    public MetaAgent(String name,MetaAgent superAgent, int scope)
    {
        super();
        this.name = name;
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
    public String getName()
    {
        return name;    //returns the name
    }
    public String getSuperAgentName()
    {
        return superAgent.getName();
    }
    /**
     * Getter for {@link weiss.core.agent.Portal Portal} object.
     * @return {@link weiss.core.agent.Portal Portal} pointer.
     */
    public MetaAgent getSuperAgent()
    {
        return superAgent;  //returns the superAgent
    }
    /**
     * Method to set scope of MetaAgent
     * @return Integer relating to the scope of the MetaAgent
     */
    private int getScope()
    {
        return scope;   //returns the scope
    }
    
    //--------------------------------------------------------------------------
    //SETTERS
    
    public void setName(String n)
    {
        SysMessage request = new SysMessage(this.getName(), getSuperAgentName(), "NameCheck " + n);    //creates a SysMessage that will request its superAgent to begin a nameCheck across the system
        pushToSuperAgent(request);  //pushes the request to the superAgent
    }
    /*?*?*?*?*?*?*?*?*?*
    //Can this be a message type rather than a string concat?
    */
    /*!*!*!*!*!*!*!*!*!*
    //We've got too much of a class explosion already.
    */
    public void setName(String[] reply, Message msg)//Reply about name change is handled here and determined whether name can be changed or not
    {
        String[] n = msg.getMsg().split(" ");
        switch (reply[2])   //reply[2] holds whether the name change has been approved or not
        {
            case "Approved":    //if it is approved...
                this.name = n[2];
                break;
            case "Declined":    //if it has been declined...
                //nothing happens currently, but an error, maybe even a hook to the monitor can be executed here.
                break;
        }
    }
    
    /*!*!*!*!*!*!*!*!*!*!*
    //This javadoc needs to be changed as it's no longer just about the portal
    */
    /**
     * Setter for {@link weiss.core.agent.Portal Portal} object.
     * @param superAgent {@link weiss.core.agent.Portal Portal} object.
     */
    public final void setSuperAgent(MetaAgent superAgent)
    {   
        this.superAgent = superAgent;
        if(this.superAgent != null)
        {
            pushToSuperAgent(new SysMessage(this.getName(), getSuperAgentName(), "reg", this));
        }
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
    //MESSAGE HANDLERS
    //--------------------------------------------------------------------------
    protected void msgHandler(Message msg)
    {
        String to = msg.getTo();   //puts the address of the message into a local variable
        String from = msg.getFrom();

        if(msg instanceof ReplyMessage)
        {
            replyMsgHandler((ReplyMessage) msg);   //gets sent to the handler specifically for ReplyMEssages
        }
        else
        {
            userMsgHandler((UserMessage) msg);
        }
    }
    
    abstract protected void userMsgHandler(UserMessage msg);   //EndUser creates a body for this method to make the agent do what it wants to do
    
    protected void replyMsgHandler(ReplyMessage msg)
    {
        String[] reply = msg.getMsg().split(" ");
        switch(reply[1])
        {
            case "ReturnToSender":
                //handling for an undelivered message
                break;
            case "Name":
                this.setName(reply, msg.getContents());
                break;
        }
    }
    
    public void sendMessage(String to, String message)
    {
        pushToSuperAgent(new UserMessage(this.getName(), to, message));
    }
    
    //--------------------------------------------------------------------------
    //INTERFACE METHODS
    //--------------------------------------------------------------------------
    @Override
    public void addNodeMonitor(NodeMonitor nodeMonitor)
    {
        /*!*!*!*!*!*!*!*!*!*!*!*
        //Error happening here
        */
        //this.monitors.add(nodeMonitor);   
    }
    @Override
    public void removeNodeMonitor(NodeMonitor nodeMonitor)
    {
        /*!*!*!*!*!*!*!*!*!*!*!*
        //Error happening here
        */
        //this.monitors.remove(nodeMonitor);
    }
    /*public void updateNodeMonitor(Message msg)
    {
        monitor.stream().forEach((node) ->
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
        });
    }*/
    /**
     * Method to add a client that interacts with the MetaAgent. Only one client
     * can be active at any one time.
     * @param client An object of type WeissBase. Current implementation uses
     * {@link weiss.manager.Client Client}.
     */
    @Override
    public void addClient(NodeMonitor client)
    {
        /*!*!*!*!*!*!*!*!*!*
        //Error happening here
        */
        //this.client = client;
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
    /*@Override
    public boolean hasMonitor()
    {
        return !monitors.isEmpty();
    }*/
    
    //--------------------------------------------------------------------------
    //CLASS SPECIFIC METHODS
    protected void pushToSuperAgent(Message msg)
    {
        try //passes the message to the next MetaAgent in the chain
        {
            if(superAgent != null)
                superAgent.put(msg);    //puts the message onto this agent's parent's (Portal or Router) blocking queue (or the next router in line if this is a router)             
        } catch (InterruptedException ex)
        {
            Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
