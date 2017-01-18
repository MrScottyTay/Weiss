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

import java.util.logging.Level;
import java.util.logging.Logger;
import weiss.core.message.Message;
import weiss.core.message.UserMessage;
import weiss.core.message.SysMessage;
import weiss.core.message.RouterMessage;

/**
 * * Class used for handling messages from
 * {@link weiss.core.agent.Portal Portal} classes, and routing them to the
 * correct destination. Routers are linked together in a pseudo linked list.
 * <p>
 * The Router wraps the original message with its own name, and checks each
 * message it receives for that name.
 * <p>
 * If the name on the message doesn't match the routers name, it checks it's
 * {@link Router#routingTable Routing Table} for the value in the
 * {@link weiss.core.message.Message#to To} field. If found, the message is
 * passed to the selected object, if not, it is passed to the next router.
 *
 * @author Adam Young, Teesside University Sch. of Computing
 * @author Scott Taylor, Teesside University Sch. of Computing
 */
public class Router extends Portal
{

    private final int scope = 1;
    /**
     * Pointer to the last router created, to add Routers to the linked-list.
     */
    protected static volatile MetaAgent lastRouter;

    /**
     * Constructor for the Router class, which calls
     * {@link weiss.core.agent.Router#updateLastRouter() updateLastRouter}.
     *
     * @param name String for the name variable.
     */
    public Router(String name)
    {
        super(name, null);
        this.setSuperAgent(this);
        this.updateLastRouter();
    }

    //--------------------------------------------------------------------------
    //MESSAGE HANDLING
    //--------------------------------------------------------------------------
    /**
     * Method to handle UserMessages. If the target is present in the
     * routingTable, the message is passed to the specific subAgent. Otherwise,
     * the message is packaged into a RouterMessage, and passed to the next
     * Router in the chain.
     *
     * @param msg A UserMessage passed from the message handler.
     */
    @Override
    protected void userMsgHandler(UserMessage msg)
    {
        if (msg.getScope() <= scope)
        {
            if (routingTable.containsKey(msg.getTo()))
            {
                pushToSubAgent(msg);
            }
            else
            {
                if (msg.getScope() == 0)
                {
                    RouterMessage rMsg = new RouterMessage(msg.getFrom(), msg.getTo(), msg, getName());
                    pushToSuperAgent(rMsg);
                }
                else
                {
                    this.pushToSubAgent(new UserMessage(this.getName(),
                            msg.getFrom(), "Your scope privilages are not"
                            + " sufficient."));
                }
            }
        }
        else
        {
            this.pushToSubAgent(new UserMessage(this.getName(),
                    msg.getFrom(), "Your scope privilages are not"
                    + " sufficient."));
        }
    }

    /**
     * Method to handle RouterMessages. If the source was this router, a reply
     * message is sent back to the original sender. Otherwise, If the message is
     * a UserMessage, and the target is in the Router's routingTable, the
     * message is pushed to the correct subAgent. if the target isn't found,
     * it's passed onto the next Router in the chain.
     *
     * @param msg A RouterMessage, passed from the message handler.
     */
    @Override
    protected void routerMsgHandler(RouterMessage msg)
    {
        Message contents = msg.getContents();
        if (!msg.getOrigin().equals(this.getName()))
        {
            if (contents instanceof UserMessage)
            {
                if (routingTable.containsKey(contents.getTo()))
                {
                    pushToSubAgent(contents);
                }
                else
                {
                    pushToSuperAgent(msg);
                }
            }
        }
        else
        {
            this.pushToSubAgent(new UserMessage(this.getName(),
                    contents.getFrom(), "User not found"));
        }
    }

    /**
     * A method to handle registration/de-registration, as well as superAgent
     * assignment after instantiation.
     *
     * @param msg A SysMessage passed from the message handler.
     */
    @Override
    protected void sysMsgHandler(SysMessage msg)//Redo this to use enums
    {
        switch (msg.getMsg())
        {
            case "reg":
                registration(msg);
                break;
            case "dereg":
                deregistration(msg);
                break;
            case "setSuperAgent":
                setSuperAgent(msg.getAgent());
                break;
        }
    }

    //--------------------------------------------------------------------------
    //COMMANDS
    //--------------------------------------------------------------------------
    //--------------------------------------------------------------------------
    //REGISTRATION
    //--------------------------------------------------------------------------
    /**
     * Method to register subAgents to this MetaAgent.
     *
     * @param msg
     */
    private void registration(SysMessage msg)
    {
        routingTable.put(msg.getFrom(), msg.getAgent());
    }
    
    private void deregistration(SysMessage msg)
    {
        if(routingTable.containsKey(msg.getFrom()))
            routingTable.remove(msg.getFrom(), msg.getAgent());
    }
 

    /**
     * Method to add new routers to the linked list, by altering the superAgents
     * of the newest and 2nd newest routers, and adjusting the static variable
     * {@link weiss.core.agent.Router#lastRouter lastRouter}.
     */
    private void updateLastRouter()
    {
        if (lastRouter != null)
        {
            this.setSuperAgent(lastRouter.getSuperAgent());

            try
            {
                lastRouter.put(new SysMessage(this.getName(), lastRouter.getName(),
                        "setSuperAgent", this));
            }
            catch (InterruptedException ex)
            {
                Logger.getLogger(Router.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lastRouter = this;
    }
}
