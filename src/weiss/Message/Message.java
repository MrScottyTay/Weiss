package weiss.Message;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/** Class detailing the basic message structure to be used within Weiss. 
 * Uses String variables to provide easier extension with other systems.
 * <p>
 * To be used in classes implementing the {@link weiss.MetaAgent MetaAgent} abstract class.
 * @author Scott Taylor, Teesside University Sch. of Computing
 */
public abstract class Message
{
    private final String from;
    private final String to;
    private final String msg;
    private final String timestamp;
    
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
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date now = Calendar.getInstance().getTime();
        timestamp = df.format(now);
        
        //Using UUID as message identifier
        
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
     * @return String of message variable.
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
    @Override
    public String toString()
    {
        return "From: " + from + "\nTo: " + to + "\nMessage: " + msg + "\nTime Sent: " + timestamp;
    } 
}
