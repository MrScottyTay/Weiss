
package weiss;

import java.util.HashMap;
import weiss.Message.Message;

/** Class used for handling messages from both {@link weiss.MetaAgent MetaAgent} classes and {@link weiss.Router Router} classes, 
 * and routing them to the correct destination. The class implements {@link weiss.MetaAgent MetaAgent}, which is the basis for 
 * all of our MAS classes.
 * <p>
 * When the object receives a message, it checks it's own {@link Portal#routingTable Routing Table} for the value found in the {@link weiss.Message.Message#to To} field.
 * If the object is found, it passes the message on to the selected object. If not, the object is passed to its assigned {@link weiss.Router Router}.
 * 
 *
 * @author Adam Young
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
    
    @Override
    public void msgHandler(Message msg)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
