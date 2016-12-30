
package weiss.agent;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import weiss.message.*;

/** *  Class used for handling messages from both {@link weiss.agent.MetaAgent MetaAgent} classes and {@link weiss.agent.Router Router} classes, 
 * and routing them to the correct destination. The class implements {@link weiss.agent.MetaAgent MetaAgent}, which is the basis for 
 * all of our MAS classes.
 * <p>
 * When the object receives a message, it checks it's own {@link Portal#routingTable Routing Table} for the value found in the {@link weiss.Message.Message#to To} field.
 * If the object is found, it passes the message on to the selected object. If not, the object is passed to its assigned {@link weiss.agent.Router Router}.
 * 
 *
 * @author Adam Young, Teesside University Sch. of Computing
 * @author Scott Taylor, Teesside University Sch.of Computing
 */
public class Portal extends MetaAgent
{
    /**
     * HashMap holding references to MetaAgents connected to itself.
     */
    private HashMap routingTable = new HashMap();
    
    /**
     * Constructor for the Portal class.
     * @param n String of name variable.
     * @param p MetaAgent pointer of Router 
     */
    public Portal(String n, MetaAgent p)
    {
        //Routing table needs implementing.
        super(n, p);
    }
    public Portal(String n, MetaAgent p, int s)
    {
        super(n, p, s);
    }
    
    @Override
    public void msgHandler(Message msg)
    {
        String address = msg.getTo();   //puts the address of the message into a local variable
        if(address.equals(getName()))
        {
            if(msg instanceof SysMessage)   //if the message is a SysMessage
            {
                sysMsgHandler((SysMessage)msg); //it gets sent to the handler specifically for SysMessages
            }
        }
        else
        {
            if(routingTable.containsValue(address)) //checks if the routing table has address
            {
                MetaAgent agent = (MetaAgent)routingTable.get(address); //gets the addressed agent
                try
                {
                    agent.put(msg); //puts the message onto the agent's blocking queue
                }
                catch (InterruptedException ex)
                {
                    Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            //if the portal does not have the addressed agent in its routing table...
            else if(routingTable.containsKey("Router"))   //checks if it has a listing for it's Router (it always should)
            {
                MetaAgent router = (MetaAgent)routingTable.get("Router");   //gets the router
                try
                {
                    router.put(msg);    //puts the message onto the router's blocking queue
                }
                catch (InterruptedException ex)
                {
                    Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else
            {
                //error handling for not being able to pass the message onto its router
                //error may have occured in registration when it was meant to create a listing for the router it is a part of
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
    
    
    //--------------------------------------------------------------------------
    //Operations
    
    private void registration(SysMessage msg)
    {
        MetaAgent agent = msg.getAgent();   //gets the agent from the message
        String n = agent.getName(); //gets the name of the agent
        routingTable.put(n, agent); //puts the agent with its name as the key into the routingTable
        
        MetaAgent router = (MetaAgent)routingTable.get("Router");   //getting the router for use in the next if statement
        
        //if the scope is for router-wide or global AND this registration message did not come from the router
        //it will tell the router to also register this agent
        if(agent.getScope() <= 1 && !msg.getFrom().equals(router.getName()))
        {
            SysMessage regMsg = new SysMessage(getName(), router.getName(), "registration", agent); //creates a new registration SysMessage for the router
            try
            {
                router.put(regMsg); //puts the registration message onto the router's blocking queue
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
