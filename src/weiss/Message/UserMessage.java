/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiss.message;

import java.util.UUID;

/** Class extending the {@link Message Message} class, to be used for user to user message transmission.
 * This type of message is to be read by user implemented classes, rather than the middleware infrastructure.
 * <p>
 * The alternative of this message type is {@link SysMessage SysMessage}.
 *
 * @author Adam Young
 */
public class UserMessage extends Message
{
    private String id;
    /**
     * Constructor for the UserMessage class.
     * @param from String of message sender.
     * @param to String of message receiver.
     * @param message String of message contents.
     */
    
    public UserMessage(String from, String to, String message)
    {
        super(from, to, message);
        id = UUID.randomUUID().toString();
    }
    
    public UserMessage(String from, String to, String message, String id)
    {
        super(from, to, message);
    }
    
    public String getId()
    {
        return id;
    }
    
}
