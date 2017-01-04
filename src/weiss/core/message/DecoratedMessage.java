package weiss.core.message;

/**
 * @author Scott Taylor, Teesside University Sch. of Computing
 */
public abstract class DecoratedMessage extends Message
{
    private Message contents;

    public DecoratedMessage(String f, String t, String m, Message c)
    {
        super(f, t, m);
        contents = c;
    }
    
    //--------------------------------------------------------------------------
    //Getters
    //--------------------------------------------------------------------------
    public Message getContents()
    {
        return contents;
    }

}
