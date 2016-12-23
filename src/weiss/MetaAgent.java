package weiss;

import weiss.Message.Message;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * An abstract class detailing the construction of a MetaAgent object, to be implemented
 * by the end user.
 * This implementation of a meta agent extends 
 * a <a href="https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/LinkedBlockingQueue.html">LinkedBlockingQueue</a>.
 * 
 * @author Scott Taylor, Teesside University Sch. of Computing
 * @author Adam Young, Teesside University Sch. of Computing
 */
public abstract class MetaAgent extends LinkedBlockingQueue
{
    private String name;
    private MetaAgent portal;
    //NodeMonitor monitor;
    
    /**
     * Constructor to initialise a MetaAgent object.
     * @param n Name of MetaAgent.
     * @param p {@link weiss.Portal Portal} belonging to MetaAgent.
     */
    public MetaAgent(String n, MetaAgent p)
    {
        setName(n);
        //need to check for validity of name;
        
        setPortal(p);
        //need to send messages for registration to portal
    }
    
    //--------------------------------------------------------------------------
    //GETTERS
    /**
     * Getter for name variable.
     * @return name String.
     */
    public String getName()
    {
        return name;
    }
    /**
     * Getter for {@link weiss.Portal Portal} object.
     * @return {@link weiss.Portal Portal} pointer.
     */
    public MetaAgent getPortal()
    {
        return portal;
    }
    //--------------------------------------------------------------------------
    //SETTERS
    /**
     * Setter for name variable.
     * @param n name String.
     */
    public final void setName(String n)
    {
        name = n;
        
        //need to check for validity of name
    }
    /**
     * Setter for {@link weiss.Portal Portal} object.
     * @param p {@link weiss.Portal Portal} object.
     */
    public final void setPortal(MetaAgent p)
    {
        portal = p;
        
        //need to change registration and scope
    }
    
    /**
     * Method to handle incoming messages.
     * @param msg Message object.
     */
    abstract public void msgHandler(Message msg);    
}
