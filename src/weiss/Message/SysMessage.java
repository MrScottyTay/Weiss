/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiss.Message;

/** Class extending the {@link Message Message} class, to be used for system message transmission.
 * This type of message can be read by all MetaAgents, and contains instructions rather than text.
 * <p>
 * The alternative of this message type is {@link UserMessage UserMessage}.
 *
 * @author Adam Young
 */
public class SysMessage extends Message
{
    /**
     * Constructor for the SysMessage class.
     * @param f String of message sender.
     * @param t String of message receiver.
     * @param m String of message contents.
     */
    public SysMessage(String f, String t, String m)
    {
        super(f, t, m);
        //Needs additional fields adding.
    }
    
}
