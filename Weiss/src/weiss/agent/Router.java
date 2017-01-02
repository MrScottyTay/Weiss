
package weiss.agent;

import java.util.logging.Level;
import java.util.logging.Logger;
import weiss.message.*;

/** *  Class used for handling messages from {@link weiss.agent.Portal Portal} classes, and routing them to the correct
 * destination. Routers are linked together in a pseudo linked list.
 * <p>
 * The Router wraps the original message with its own name, and checks each message it receives for that name.
 * <p>
 * If the name on the message doesn't match the routers name, it checks it's {@link Router#routingTable Routing Table} for the value in the {@link weiss.Message.Message#to To} field.
 * If found, the message is passed to the selected object, if not, it is passed to the next router.
 * <p>
 * If the name does match that of the router, a reply is send to the value in the {@link weiss.Message.Message#from From} field, saying that
 * the message couldn't be sent.
 *
 * @author Adam Young, Teesside University Sch. of Computing
 * @author Scott Taylor, Teesside University Sch. of Computing
 */
public class Router extends Portal implements Runnable
{
    
    /**
     * Constructor for the Router class
     * @param name String for the name variable.
     * @param nextRouter String pointing to the next Router in the chain.
     */
    public Router(String name, MetaAgent nextRouter)
    {
        super(name, nextRouter);
    }

    
    //--------------------------------------------------------------------------
    //Operations
    //--------------------------------------------------------------------------
    
    /**
     * Method to register subAgents to this MetaAgent.
     * @param msg 
     */
    private void registration(SysMessage msg)
    {
        
        MetaAgent agent = msg.getAgent();
        routingTable.put(agent.getName(), agent);
    }
    
    /**
     * Method to de-register subAgents from this MetaAgent.
     * @param msg A SysMessage object.
     */
    private void deregistration(SysMessage msg)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
