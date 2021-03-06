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

import java.util.ArrayList;
import weiss.management.nodeMonitor.Monitorable;
import weiss.management.nodeMonitor.NodeMonitor;
import weiss.core.message.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import weiss.core.message.SysMessage.SysType;

/**
 * An abstract class detailing the construction of a MetaAgent object, to be
 * implemented by the end user. This implementation of a meta agent extends a
 * <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/LinkedBlockingQueue.html">LinkedBlockingQueue</a>.
 *
 *
 * @author Scott Taylor, Teesside University Sch. of Computing
 * @author Adam Young, Teesside University Sch. of Computing
 */
public abstract class MetaAgent extends LinkedBlockingQueue<Message> implements Runnable, Monitorable
{

    protected String name;
    private NodeMonitor monitor;
    private MetaAgent superAgent;
    private final Thread thread;
    
    
    /**
     * Constructor to initialise a MetaAgetn object.
     *
     * @param name {@link weiss.core.agent.Portal Portal} belonging to
     * MetaAgent.
     * @param superAgent Scope of the MetaAgent.
     */
    public MetaAgent(String name, MetaAgent superAgent)
    {
        super();
        
        this.name = RegManagement.setName(name);
        setSuperAgent(superAgent);

        thread = new Thread(this);
    }

    @Override
    public void run()
    {
        while (true)
        {
            try
            {
                msgHandler(take());
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(MetaAgent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Method to start the assigned thread, accessible from outside the scope of
     * the class.
     */
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
        return name;
    }
    
    

    /**
     * Getter for {@link weiss.core.agent.MetaAgent MetaAgent} superAgent
     * object.
     *
     * @return {@link weiss.core.agent.MetaAgent superAgent} pointer.
     */
    public MetaAgent getSuperAgent()
    {
        return superAgent;
    }

    /**
     * Setter for {@link weiss.core.agent.MetaAgent MetaAgent} superAgent.
     *
     * @param superAgent {@link weiss.core.agent.MetaAgent MetaAgent} object.
     */
    public final void setSuperAgent(MetaAgent superAgent)
    {
        this.superAgent = superAgent;
        if (this.superAgent != null)
        {
            pushToSuperAgent(new SysMessage(getName(), getSuperAgent().getName(), SysType.REGISTER, this));
        }
    }

    //--------------------------------------------------------------------------
    //MESSAGE HANDLERS
    //--------------------------------------------------------------------------
    /**
     * Method to handle messages. In the generic MetaAgent implementation,
     * messages are pushed as UserMessages. Other implementations sort the type
     * of message, and direct it to the correct handler.
     *
     * @param msg The message to be handled.
     */
    protected void msgHandler(Message msg)
    {
        updateNodeMonitor(msg);
        if(msg instanceof UserMessage)
        {
            userMsgHandler((UserMessage) msg);
        }
            
    }

    /**
     * Abstract method to implement a handler of UserMessages. An implementation
     * can be found at
     * {@link weiss.core.agent.Agent#userMsgHandler(weiss.core.message.UserMessage) this agent}.
     *
     * @param msg A UserMessage
     */
    abstract protected void userMsgHandler(UserMessage msg);   //EndUser creates a body for this method to make the agent do what it wants to do
    //--------------------------------------------------------------------------
    //CLASS SPECIFIC METHODS
    //-------------------------------------------------------------------------- 
    /**
     * Method to push a message onto a parents blocking queue.
     *
     * @param msg The message to be pushed.
     */
    public void pushToSuperAgent(Message msg)
    {
        if (msg != null)
        {
            try
            {
                if (superAgent != null)
                {
                    superAgent.put(msg);
                }
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        monitor = null;
    }

    @Override
    public boolean hasNodeMonitor()
    {
        return monitor != null;
    }

    /**
     * Method to update the node monitor.
     *
     * @param msg Message containing the table data.
     */
    protected void updateNodeMonitor(Message msg)
    {
        if (monitor != null)
        {
            monitor.insertTableData(msg);
        }
    }
}
