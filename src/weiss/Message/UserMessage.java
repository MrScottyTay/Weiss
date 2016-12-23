/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiss.Message;

/** Class extending the {@link Message Message} class, to be used for user to user message transmission.
 * This type of message is to be read by user implemented classes, rather than the middleware infrastructure.
 * <p>
 * The alternative of this message type is {@link SysMessage SysMessage}.
 *
 * @author Adam Young
 */
public class UserMessage extends Message
{
    /**
     * Constructor for the UserMessage class.
     * @param f String of message sender.
     * @param t String of message receiver.
     * @param m String of message contents.
     */
    public UserMessage(String f, String t, String m)
    {
        super(f, t, m);
        //Needs additional fields adding.
    }
    
}
