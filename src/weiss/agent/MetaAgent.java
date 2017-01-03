package weiss.agent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import weiss.message.*;

/**
 * An abstract class detailing the construction of a MetaAgent object, to be implemented
 * by the end user.
 * This implementation of a meta agent extends 
 * a <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/LinkedBlockingQueue.html">LinkedBlockingQueue</a>.
 * 
 * @author Scott Taylor, Teesside University Sch. of Computing
 * @author Adam Young, Teesside University Sch. of Computing
 */
public abstract class MetaAgent extends LinkedBlockingQueue implements Runnable
{
    private final Thread t;
    private boolean shouldStop;
    
    private String name;
    protected MetaAgent superAgent;
    private int scope;  //0 = global, 1 = router-wide, 2 = portal-wide
    protected Map<String, NodeMonitor> nodeMonitorMap; 
    
    
    
    /**
     * Constructor to initialise a MetaAgetn object.
     * @param name {@link weiss.agent.Portal Portal} belonging to MetaAgent.
     * @param superAgent Scope of the MetaAgent.
     */
    public MetaAgent(String name, MetaAgent superAgent)
    {
        this.nodeMonitorMap = new HashMap<>();
        this.name = name;
        this.superAgent = superAgent;
        this.scope = 0;
        this.shouldStop = false;
        
        t = new Thread(this);
    }
    /**
     * Constructor to initialise a MetaAgent object, and setting the scope.
     * @param name Name of MetaAgent.
     * @param superAgent {@link weiss.agent.Portal Portal} belonging to MetaAgent.
     * @param scope Scope of the MetaAgent.
     */
    public MetaAgent(String name, MetaAgent superAgent, int scope)
    {
        this.nodeMonitorMap = new HashMap<>();
        this.name = name;
        this.superAgent = superAgent;
        this.scope = scope;
        this.shouldStop = false;
        
        t = new Thread(this);
    }
    
    //--------------------------------------------------------------------------
    //GETTERS
    //--------------------------------------------------------------------------
    /**
     * Getter for name variable.
     * @return name String.
     */
    public String getName()
    {
        return name;
    }
    /**
     * Getter for {@link weiss.agent.Portal Portal} object.
     * @return {@link weiss.agent.Portal Portal} pointer.
     */
    public MetaAgent getPortal()
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
    //--------------------------------------------------------------------------
    /**
     * Setter for name variable.
     * @param n name String.
     */
    public final void setName(String n)//sends a message to check if its desired name is available
    {
        String[] spaceCheck = n.split(" ");
        if(spaceCheck.length > 1)
        {
            SysMessage msg = new SysMessage(getName(), n, "NameCheck");
            pushToSuperAgent(msg);
        }
        else
        {
            //error invalid name
        }
    }
    public void setName(String[] reply)//Reply about name change is handled here and determined whether name can be changed or not
    {
        switch (reply[2])
        {
            case "Approved":
                this.name = reply[3];
                break;
            case "Declined":
                break;
        }
    }
    /**
     * Setter for {@link weiss.agent.Portal Portal} object.
     * @param superAgent {@link weiss.agent.Portal Portal} object.
     */
    public final void setSuperAgent(MetaAgent superAgent)
    {   
        this.superAgent = superAgent;

        //need to change scope
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
    //Message Handlers
    //--------------------------------------------------------------------------
    
    protected void msgHandler(Message msg)
    {
        //this.updateNodeMonitor(msg);
        String to = msg.getTo();   //puts the address of the message into a local variable
        String from = msg.getFrom();

        if (to.equals(this.getName()) && msg instanceof SysMessage)
            this.sysMsgHandler((SysMessage) msg); //it gets sent to the handler specifically for SysMessages
        else if(to.equals(this.getName()) && msg instanceof RouterMessage)
            this.RouterMsgHandler((RouterMessage) msg);//gets sent to the handler specifically for RouterMessages
        else if(to.equals(this.getName()) && msg instanceof ReplyMessage)
            this.ReplyMsgHandler((ReplyMessage) msg);//gets sent to the handler specifically for ReplyMEssages
        else
            this.userMsgHandler((UserMessage) msg);
    }

    protected abstract void sysMsgHandler(SysMessage msg);
    
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
    
    protected abstract void userMsgHandler(UserMessage msg);

    public void sendMessage(String to, String message)
    {
        try
        {
            superAgent.put(new UserMessage(this.getName(), to, message));
        } 
        catch (InterruptedException ex)
        {
            Logger.getLogger(MetaAgent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        protected void pushToSuperAgent(Message msg)
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
    //Threading Operations
    //--------------------------------------------------------------------------
    public void start()
    {
        t.start();
    }
    
    public void stop()
    {
        shouldStop = true;
    }
    
    @Override
    public void run()
    {
        while(!shouldStop)
        {
            try
            {
                Message msg = (Message) this.take();
                msgHandler(msg);
            } 
            catch (InterruptedException ex)
            {
                Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
    }
    
    //--------------------------------------------------------------------------
    //Other Operations
    //--------------------------------------------------------------------------
    protected void updateNodeMonitor(Message msg)
    {
        for(String key : this.nodeMonitorMap.keySet())
        {
            try
            {
                nodeMonitorMap.get(key).put(msg);
            } 
            catch (InterruptedException ex)
            {
                Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public String toString()
    {
        return this.getName();
    }
}
