package weiss.agent;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import weiss.message.*;

/**
 * * Class used for handling messages from both
 * {@link weiss.agent.MetaAgent MetaAgent} classes and
 * {@link weiss.agent.Router Router} classes, and routing them to the correct
 * destination. The class implements {@link weiss.agent.MetaAgent MetaAgent},
 * which is the basis for all of our MAS classes.
 * <p>
 * When the object receives a message, it checks it's own
 * {@link Portal#routingTable Routing Table} for the value found in the
 * {@link weiss.Message.Message#to To} field. If the object is found, it passes
 * the message on to the selected object. If not, the object is passed to its
 * assigned {@link weiss.agent.Router Router}.
 *
 *
 * @author Adam Young, Teesside University Sch. of Computing
 * @author Scott Taylor, Teesside University Sch.of Computing
 */
public class Portal extends MetaAgent implements Runnable
{

    protected final Map<String, MetaAgent> routingTable;

    /**
     * Constructor for the Portal class.
     *
     * @param name String of name variable.
     * @param superAgent MetaAgent pointer of Router.
     */
    public Portal(String name, MetaAgent superAgent)
    {
        super(name, superAgent);
        this.routingTable = new HashMap<>();

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
        this.routingTable = new HashMap<>();
    }

    @Override
    protected void sysMsgHandler(SysMessage msg)
    {
        switch (msg.getMsg())
        {
            case "reg":
                this.registration(msg);
                break;
            case "dereg":
                this.deregistration(msg);
                break;
            case "NameCheck":
                pushToSuperAgent(msg);
                break;
        }
    }
    
    @Override
    protected void userMsgHandler(UserMessage msg)
    {
        if (routingTable.containsKey(msg.getTo())) //checks if the routing table has address
        {
            pushToAgent(msg.getTo(), msg);
        }
            else   //if the portal does not have the addressed agent in its routing table... 
            {
                if (superAgent != null)
                    pushToSuperAgent(msg.getTo(), msg);
                else
                {
                    try
                    {
                        (routingTable.get(msg.getFrom())).put(new SysMessage(this.getName(), msg.getFrom(), "noAgent"));
                    } 
                    catch (InterruptedException ex)
                    {
                        Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            
    }
    
    protected void pushToAgent(String address, Message msg)
    {
        MetaAgent agent = (MetaAgent) routingTable.get(address); //gets the addressed agent
        try
        {
            agent.put((UserMessage) msg); //puts the message onto the agent's blocking queue
        } catch (InterruptedException ex)
        {
            Logger.getLogger(Portal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    //--------------------------------------------------------------------------
    //routingTable Management
    //--------------------------------------------------------------------------
    /**
     * Method to register a subAgent to this MetaAgent
     *
     * @param msg A SysMessage object
     */
    private void registration(SysMessage msg)
    {
        MetaAgent agent = msg.getAgent();   //gets the agent from the message        
        if (routingTable.containsKey(agent.getName()))
        {
            agent.msgHandler(new UserMessage(this.getName(), agent.getName(),
                    "You cannot use this name, please try another."));
        } else
        {
            routingTable.put(agent.getName(), agent); //puts the agent with its name as the key into the routingTable
        }
        //if the scope is for router-wide or global AND this registration message did not come from the router
        //it will tell the router to also register this agent
        /*
        if(agent.getScope() <= 1 && !msg.getFrom().equals(superAgent.getName()))
        {
            SysMessage regMsg = new SysMessage(this.getName(), superAgent.getName(),
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
         */
    }

    private void deregistration(SysMessage msg)
    {
        //To do
    }


}
