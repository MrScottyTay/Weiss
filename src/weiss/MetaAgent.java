package weiss;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author Scott Taylor, Teesside University Sch. of Computing
 */
public abstract class MetaAgent extends LinkedBlockingQueue
{
    String name;
    MetaAgent portal;
    //NodeMonitor monitor;

    MetaAgent(String n, MetaAgent p)
    {
        setName(n);
        //need to check for validity of name;
        
        setPortal(p);
        //need to send messages for registration to portal
    }
    
    //--------------------------------------------------------------------------
    //GETTERS
    public String getName()
    {
        return name;
    }
    public MetaAgent getPortal()
    {
        return portal;
    }
    //--------------------------------------------------------------------------
    //SETTERS
    public final void setName(String n)
    {
        name = n;
        
        //need to check for validity of name
    }
    public final void setPortal(MetaAgent p)
    {
        portal = p;
        
        //need to change registration and scope
    }
    
    abstract public void msgHandler(Message msg);    
}
