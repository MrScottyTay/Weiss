
package weiss.agent;

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
     * Constructor for the Portal class.
     * @param name String of name variable.
     * @param superAgent MetaAgent pointer of Router. 
     */
    public Portal(String name, MetaAgent superAgent)
    {
        //Routing table needs implementing.
        super(name, superAgent);
    }
    /**
     * 
     * @param name String of name variable.
     * @param superAgent MetaAgent pointer of Router. 
     * @param scope Integer detailing scope of Router.
     */
    public Portal(String name, MetaAgent superAgent, int scope)
    {
        super(name, superAgent, scope);
    }
    
    /**
     * Method detailing the handling of messages within a Portal.
     * @param msg A Message variable.
     */
    @Override
    public void msgHandler(Message msg)
    {
        String address = msg.getTo();   //puts the address of the message into a local variable
        if(address.equals(getName()))
        {
            if(msg instanceof SysMessage)   //if the message is a SysMessage
                sysMsgHandler((SysMessage)msg); //it gets sent to the handler specifically for SysMessages
        }
        else
        {
            if(routingTable.containsKey(address)) //checks if the routing table has address
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
            else   //if the portal does not have the addressed agent in its routing table... 
            {
                try //passes the message to the next MetaAgent in the chain
                {
                    superAgent.put(msg);    //puts the message onto the router's blocking queue
                }
                catch (InterruptedException ex)
                {
                    Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    /**
     * Method used to handle system messages
     * @param msg A SysMessage variable.
     */
    private void sysMsgHandler(SysMessage msg)
    {
        if(msg.getMsg().equals("Registration"))
            registration(msg);
    }
    
    
    //--------------------------------------------------------------------------
    //Operations
    
    /**
     * Method to register a subAgent to this MetaAgent
     * @param msg A SysMessage object 
     */
    private void registration(SysMessage msg)
    {
        MetaAgent agent = msg.getAgent();   //gets the agent from the message
        String name = agent.getName(); //gets the name of the agent
        
        if(routingTable.containsKey(agent.getName()))
        {
            agent.msgHandler(new UserMessage(this.getName(), agent.getName(),
                    "You cannot use this name, please try another."));
        }
        else
            routingTable.put(agent.getName(), agent); //puts the agent with its name as the key into the routingTable
        
        //if the scope is for router-wide or global AND this registration message did not come from the router
        //it will tell the router to also register this agent
        if(agent.getScope() <= 1 && !msg.getFrom().equals(superAgent.getName()))
        {
            SysMessage regMsg = new SysMessage(getName(), superAgent.getName(),
                    "registration", agent); //creates a new registration SysMessage for the router
            try
            {
                superAgent.put(regMsg); //puts the registration message onto the router's blocking queue
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
