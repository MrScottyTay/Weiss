package weiss.agent;

import weiss.message.Message;
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
    private int scope;  //0 = global, 1 = router-wide, 2 = portal-wide
    //NodeMonitor monitor;
    
    public MetaAgent(String n, MetaAgent p)
    {
        setName(n);
        setPortal(p);
        setScope(0);
    }
    /**
     * Constructor to initialise a MetaAgent object.
     * @param n Name of MetaAgent.
     * @param p {@link weiss.agent.Portal Portal} belonging to MetaAgent.
     */
    public MetaAgent(String n, MetaAgent p, int s)
    {
        setName(n);
        setPortal(p);
        setScope(s);
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
     * Getter for {@link weiss.agent.Portal Portal} object.
     * @return {@link weiss.agent.Portal Portal} pointer.
     */
    public MetaAgent getPortal()
    {
        return portal;
    }
    public int getScope()
    {
        return scope;
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
     * Setter for {@link weiss.agent.Portal Portal} object.
     * @param p {@link weiss.agent.Portal Portal} object.
     */
    public final void setPortal(MetaAgent p)
    {
        portal = p;
        
        //need to change registration and scope
    }
    public final void setScope(int s)
    {
        scope = s;
        
        //need to change registration and scope
    }
    
    /**
     * Method to handle incoming messages.
     * @param msg Message object.
     */
    abstract public void msgHandler(Message msg);    
}
