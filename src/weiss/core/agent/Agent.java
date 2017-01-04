/* 
 * Copyright (C) 2017 Adam Young
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package weiss.core.agent;


import weiss.core.message.Message;
import weiss.core.message.SysMessage;
import weiss.core.message.UserMessage;

/**
 *
 * @author Adam Young, Teesside University Sch. of Computing
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
