package weiss.Message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/** Class detailing the basic message structure to be used within Weiss. 
 * Uses String variables to provide easier extension with other systems.
 * <p>
 * To be used in classes implementing the {@link weiss.MetaAgent MetaAgent} abstract class.
 * @author Scott Taylor, Teesside University Sch. of Computing
 */
public abstract class Message
{
    private String from;
    private String to;
    private String msg;
    private String timestamp;
    private String id;
    
    /**
     * Constructor to create a generic message object.
     * @param f String of message sender.
     * @param t String of message receiver.
     * @param m String of message contents. 
     */
    public Message(String f, String t, String m)
    {
        from = f;
        to = t;
        msg = m;
        
        //Setting the time of creation
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
        Date now = Calendar.getInstance().getTime();
        timestamp = df.format(now);
        
        //Using UUID as message identifier
        id = UUID.randomUUID().toString();
    }
    
    //--------------------------------------------------------------------------
    //GETTERS
    /**
     * Getter for the from variable.
     * @return String of from variable.
     */
    public String getFrom()
    {
        return from;
    }
    /**
     * Getter for the to variable.
     * @return String of to variable.
     */
    public String getTo()
    {
        return to;
    }
    /**
     * Getter for the message variable.
     * @return String of msg variable.
     */
    public String getMsg()
    {
        return msg;
    }
    /**
     * Getter for the time variable.
     * @return String of time variable.
     */
    public String getTime()
    {
        return timestamp;
    }
    /**
     * Getter for the id variable.
     * @return String of id variable.
     */
    public String getId()
    {
        return id;
    }
    
}
