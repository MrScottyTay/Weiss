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
import weiss.management.nodeMonitor.NodeMonitor;
import weiss.core.message.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 * An abstract class detailing the construction of a MetaAgent object, to be
 * implemented by the end user. This implementation of a meta agent extends a
 * <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/LinkedBlockingQueue.html">LinkedBlockingQueue</a>.
 * 
 * 
 * @author Scott Taylor, Teesside University Sch. of Computing
 * @author Adam Young, Teesside University Sch. of Computing
 */
public abstract class MetaAgent extends LinkedBlockingQueue implements Runnable, Monitorable
{
    private String name;
    private int scope;  //0 = global, 1 = router-wide, 2 = portal-wide
    private NodeMonitor monitor;

    private MetaAgent superAgent;
    private ImageIcon image;
    private Thread thread;
    
    /**
     * Constructor to initialise a MetaAgetn object.
     * 
     * @param name {@link weiss.core.agent.Portal Portal} belonging to MetaAgent.
     * @param superAgent Scope of the MetaAgent.
     */
    public MetaAgent(String name, MetaAgent superAgent)
    {
        super();
        this.name = name;
        this.setSuperAgent(superAgent);
        this.scope = 0;
        
        thread = new Thread(this);      
    }
    

    /**
     * Constructor to initialise a MetaAgent object with scope.
     * 
     * @param name Name of MetaAgent.
     * @param superAgent {@link weiss.core.agent.Portal Portal} belonging to MetaAgent.
     * @param scope Scope of the MetaAgent.
     */
    public MetaAgent(String name,MetaAgent superAgent, int scope)
    {
        super();
        this.name = name;
        this.superAgent = superAgent;
        this.scope = scope;        
    }
    @Override
    public void run()
      {
          while(true)
          {
              try
              {
                  msgHandler((Message) take());   //if theres a message in the queue, it will take it and put it into the msghandler
              } 
              catch (InterruptedException ex)
              {
                  Logger.getLogger(MetaAgent.class.getName()).log(Level.SEVERE, null, ex);
              }
          }
      }
    public void start()
    {
        thread.start();
    }

    //--------------------------------------------------------------------------
    //GETTERS

    //--------------------------------------------------------------------------

    /**
     * Getter for name variable.
     * 
     * @return name String.
     */
    public String getName()
    {
        return name;    //returns the name
    }
    
    /**
     * Getter for {@link weiss.core.agent.MetaAgent MetaAgent} superAgent object.
     * 
     * @return {@link weiss.core.agent.MetaAgent superAgent} pointer.
     */
    public MetaAgent getSuperAgent()
    {
        return superAgent;  //returns the superAgent
    }
    
    /**
     * Getter for the scope.
     * 
     * @return Integer relating to the scope of the MetaAgent
     */
    public int getScope()
    {
        return scope;   //returns the scope
    }
    
    //--------------------------------------------------------------------------
    //SETTERS
    //--------------------------------------------------------------------------
    /**
     * Setter for name.
     * 
     * @param n Name to be set.
     */
    public void setName(String n)
    {
        SysMessage request = new SysMessage(this.getName(), getSuperAgent().getName(), "NameCheck " + n);    //creates a SysMessage that will request its superAgent to begin a nameCheck across the system
        pushToSuperAgent(request);  //pushes the request to the superAgent
    }

    /**
     * Method to determine whether a name can be changed.
     * 
     * @param reply Contains the names approval.
     * @param msg Contains the new name.
     */
    public void setName(String[] reply, Message msg)
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
    
    
    /**
     * Setter for {@link weiss.core.agent.MetaAgent MetaAgent} superAgent.
     * 
     * @param superAgent {@link weiss.core.agent.MetaAgent MetaAgent} object.
     */
    public final void setSuperAgent(MetaAgent superAgent)
    {   
        this.superAgent = superAgent;
        if(this.superAgent != null)
        {
            pushToSuperAgent(new SysMessage(this.getName(), getSuperAgent().getName(), "reg", this));
        }
    }
    
    /**
     * Method to set scope of MetaAgent.
     * 
     * @param scope Integer relating to the scope of the MetaAgent.
     */
    public final void setScope(int scope)
    {
        this.scope = scope;
        
        //need to change registration and scope
    }
    
    //--------------------------------------------------------------------------
    //MESSAGE HANDLERS
    //--------------------------------------------------------------------------

   
    /**
     * Method to handle messages. In the generic MetaAgent implementation, messages
     * are pushed as UserMessages. Other implementations sort the type of message,
     * and direct it to the correct handler.
     *
    **/
    protected void msgHandler(Message msg)
    {
        updateNodeMonitor(msg);
        
        //Where is this getting used?
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
    
    /**
     * Abstract method to implement a handler of UserMessages. An implementation
     * can be found at {@link weiss.core.agent.Agent#userMsgHandler(weiss.core.message.UserMessage) this agent}.
     * @param msg A UserMessage
     */
    abstract protected void userMsgHandler(UserMessage msg);   //EndUser creates a body for this method to make the agent do what it wants to do
    
    /**
     * Method to handle replies.
     * 
     * @param msg The reply to be handled.
     */
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
    
    //--------------------------------------------------------------------------
    //CLASS SPECIFIC METHODS

    //-------------------------------------------------------------------------- 
    /**
     * Method to send messages.
     * 
     * @param to the recipient of the message.
     * @param message the message to be sent.
     */
    public void sendMessage(String to, String message)
    {
        pushToSuperAgent(new UserMessage(this.getName(), to, message));
    }
    
    /**
     * Method to push a message onto a parents blocking queue.
     *
     * @param msg The message to be pushed.
     */
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
    
    //--------------------------------------------------------------------------
    //INTERFACE METHODS
    //--------------------------------------------------------------------------

    
    /**
     * Method to add a node monitor.
     *
     * @param nodeMonitor The node monitor to be added.
     */
    @Override
    public void addNodeMonitor(NodeMonitor nodeMonitor)
    {
        this.monitor = nodeMonitor;   
    }
    
    /**
     * Method to remove a node monitor.
     * 
     */
    @Override
    public void removeNodeMonitor()
    {
        this.monitor = null;
    }
    
    /**
     * Method to update the node monitor.
     *
     * @param msg Message containing the table data.
     */
    protected void updateNodeMonitor(Message msg)
    {
        if(this.monitor != null)
            this.monitor.insertTableData(msg);
    }
    
    @Override
    public boolean hasNodeMonitor()
    {
        return this.monitor != null;
    }
}
