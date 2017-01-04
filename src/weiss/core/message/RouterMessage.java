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
public class RouterMessage extends DecoratedMessage
{
    private String origin;
    
    public RouterMessage(String t, String f, String m, Message c, String o)
    {
        super(t, f, m, c);
        origin = o;
    }
    
    public RouterMessage(String t, String f, String m, Message c)
    {
        super(t, f, m, c);
    }
    
    
    //--------------------------------------------------------------------------
    //Getters
    //--------------------------------------------------------------------------
    public String getOrigin()
    {
        return origin;
    }
    
}
