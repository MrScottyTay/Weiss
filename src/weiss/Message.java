package weiss;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Scott Taylor, Teesside University Sch. of Computing
 */
public abstract class Message
{
    String from;
    String to;
    String msg;
    String timestamp;
    String id;
    
    Message(String f, String t, String m)
    {
        from = f;
        to = t;
        msg = m;
        
        //Setting the time of creation
        DateFormat df = new SimpleDateFormat("dd/mm/yyyy hh:mm:ss");
        Date now = Calendar.getInstance().getTime();
        timestamp = df.format(now);
        
        //!! need id creation
    }
    
    //--------------------------------------------------------------------------
    //GETTERS
    public String getFrom()
    {
        return from;
    }
    public String getTo()
    {
        return to;
    }
    public String getMsg()
    {
        return msg;
    }
    public String getTime()
    {
        return timestamp;
    }
    public String getId()
    {
        return id;
    }
    
}
