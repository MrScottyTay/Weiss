package weiss.core.message;

/**
 * @author Scott Taylor, Teesside University Sch. of Computing
 * 
 */
public class ReplyMessage extends DecoratedMessage
{
    public ReplyMessage(String f, String t, String m, Message c)
    {
        super(f, t, m, c);
    }
}
