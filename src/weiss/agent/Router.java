
package weiss.agent;

import java.util.HashMap;
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
 */
public class Router extends MetaAgent
{
    /**
     * HashMap holding references to MetaAgents stored in connected portals.
     */
    private HashMap routingTable = new HashMap();
    
    /**
     * Constructor for the Router class
     * @param n String for the name variable.
     * @param p String pointing to the next Router in the chain.
     */
    public Router(String n, MetaAgent p)
    {
        super(n, p);
    }

    //--------------------------------------------------------------------------
    //Message Handlers
    //--------------------------------------------------------------------------
    @Override
    public void msgHandler(Message msg)
    {
        String address = msg.getTo();   //puts the address of the message into a local variable
        if(address.equals(getName()))   //if the address is for this Router...
        {
            if(msg instanceof SysMessage)   //if it is a SysMessage
            {
                sysMsgHandler((SysMessage)msg); //gets sent to the SysMessageHandler
            }
            //if(msg instanceof RouterMessage)
        }
        else
        {
            if(routingTable.containsKey(address))   //if the routingTable contais the addressed 
            {
                MetaAgent agent = (MetaAgent)routingTable.get(address);
                try
                {
                    agent.put(msg);
                }
                catch (InterruptedException ex)
                {
                    Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                //create RouterMessage and send it to next Router in line
            }
        }
        
    }
    
    private void sysMsgHandler(SysMessage msg)
    {
        if(msg.getMsg().equals("Registration"))
        {
            registration(msg);
        }
    }
    
    //private void routerMsgHandler(routerMessage msg)
    
    //--------------------------------------------------------------------------
    //Operations
    //--------------------------------------------------------------------------
    
    private void registration(SysMessage msg)
    {
        MetaAgent agent = msg.getAgent();
        String n = agent.getName();
        routingTable.put(n, agent);
    }
}
