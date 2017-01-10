 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiss.core.message;
/**
 *
 * @author Scott Taylor, Teesside University Sch. of Computing
 */
public class RouterMessage extends Message
{
    private final String origin;
    private final Message wrappedMessage;
    
    public RouterMessage(String from, String to, 
            Message wrappedMessage, String origin)
    {
        super(from, to , null);
        this.wrappedMessage = wrappedMessage;
        this.origin = origin;
    }
    
    
    //--------------------------------------------------------------------------
    //Getters
    //--------------------------------------------------------------------------
    public String getOrigin()
    {
        return origin;
    }
    
    public Message getContents()
    {
        return wrappedMessage;
    }
    
}
