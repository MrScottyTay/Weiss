/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiss.MetaAgent;


import java.util.logging.Level;
import java.util.logging.Logger;
import weiss.message.Message;
import weiss.message.SysMessage;
import weiss.message.UserMessage;

/**
 *
 * @author Adam Young
 */
public class Agent extends MetaAgent implements Runnable
{

    public Agent(String name, MetaAgent superAgent)
    {
        super(name, superAgent);
    }

    //--------------------------------------------------------------------------
    //MESSAGE HANDLING
    @Override
    protected void sysMsgHandler(SysMessage msg)
    {        
        switch (msg.getMsg())
        {
            case "noAgent":
                this.noAgentError(msg);
                break;            
        }
    }
    @Override
    protected void userMsgHandler(UserMessage msg)
    {
        Message message = msg;
        this.updateClient(message);
        
        System.out.println("--Agent " + this.getName() + "--" + "\n" +
                            "From: " + message.getFrom() + "\n" +
                            "To: " + message.getTo() + "\n" +
                            "Sent: " + message.getTime() + "\n" +
                            "Message: " + message.getMsg());
    }

    private void noAgentError(SysMessage msg)
    {
        System.out.println("--Error--\n" + "No agent found, please check your target name and "
                + "try again");
    }
}
