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
import weiss.core.message.UserMessage;
import weiss.core.message.DecoratedMessage;
import weiss.core.message.ReplyMessage;
import weiss.core.message.SysMessage;
import weiss.core.message.RouterMessage;

/**
 * * Class used for handling messages from {@link weiss.core.agent.Portal Portal}
 * classes, and routing them to the correct destination. Routers are linked
 * together in a pseudo linked list.
 * <p>
 * The Router wraps the original message with its own name, and checks each
 * message it receives for that name.
 * <p>
 * If the name on the message doesn't match the routers name, it checks it's
 * {@link Router#routingTable Routing Table} for the value in the
 * {@link weiss.Message.Message#to To} field. If found, the message is passed to
 * the selected object, if not, it is passed to the next router.
 * <p>
 * If the name does match that of the router, a reply is send to the value in
 * the {@link weiss.Message.Message#from From} field, saying that the message
 * couldn't be sent.
 *
 * @author Adam Young, Teesside University Sch. of Computing
 * @author Scott Taylor, Teesside University Sch. of Computing
 */
public class Router extends Portal implements Runnable
{

    /**
     * Constructor for the Router class
     *
     * @param name String for the name variable.
     * @param nextRouter String pointing to the next Router in the chain.
     */
    public Router(String name, MetaAgent nextRouter)
    {
        super(name, nextRouter);
    }

    
    //--------------------------------------------------------------------------
    //MESSAGE HANDLING
    //--------------------------------------------------------------------------
    @Override
    protected void userMsgHandler(UserMessage msg)
    {
        if (routingTable.containsKey(msg.getTo()))   //if this router knows where the addressed agent is...
        {
            pushToSubAgent(msg);  //push it into the right direction
        } else    //if the router doesn't know about this agent
        {
            //create a router message to ask the other routers to see if they know of the addressed agent
            RouterMessage rMsg = new RouterMessage(msg.getFrom(), msg.getTo(), msg.getMsg(), msg, getName());
            pushToSuperAgent(rMsg); //push it to the next router along the line
        }
    }

    //For Handling Router Messages
    protected void RouterMsgHandler(RouterMessage msg)
    {
        Message contents = msg.getContents();   //getting a local variable of the contents of the RouterMessage
        if (msg.getOrigin().equals(getName()))   //if this message was created by this router...
        {
            String type = null; //for what will be put in the 'String msg' variable in the ReplyMessage

            String[] m = msg.getMsg().split(" ");
            switch (m[1])
            {
                case "User":    //if a message could not find its recipient...
                    type = "ReturnToSender";
                    break;
                case "Name":    //if a name check message has came all the way around without finding an agent with the same name...
                    type = "Name Approved";
                    break;
                case "reg":
                    type = "RegistrationComplete";
                    break;
                case "dereg":
                    type = "DeRegistrationComplete";
                    break;
            }

            ReplyMessage reply = new ReplyMessage(getName(), contents.getFrom(), type, contents);
            pushToSubAgent(reply); //push it into the right direction for the original sender
        } else    //if this is from another router...
        {
            if(contents instanceof UserMessage) //if the content is a UserMessage...
            {
                if (routingTable.containsKey(contents.getTo()))  //if this router knows where the contents needs to go...
                {
                    pushToSubAgent(contents);    //push it into the right direction for the addressed agent
                } else    //if this router doesn't know of the addressed agent...
                {
                    pushToSuperAgent(msg);  //push to the next router along for them to check
                }
            }
            else if(contents instanceof SysMessage) //if the content is a SysMessage...
            {
                SysMessage sMsg = (SysMessage)contents; //create a version of the msg that is specifically a SysMessage
                
                switch(sMsg.getMsg())   //what type of sysMessage is it
                {
                    case "reg": //the version of registration that's needed at this point requires a RouterMessage
                        //so cannot be handled with just the sysMessageHandler or it'll constantly keep going round and round
                        registration(msg);
                        break;
                    default:    //most SysMessages will go through here
                        sysMsgHandler(sMsg);
                        break;
                }
                
                
            }
        }
    }

    @Override
    protected void sysMsgHandler(SysMessage msg)
    {
        String[] command = msg.getMsg().split(" ");
        switch (command[1])
        {
            case "reg":
                registration(msg);
                break;
            case "dereg":
                deregistration(msg);
                break;
            case "NameCheck":
                nameCheck(msg);
                break;
        }
    }

    //--------------------------------------------------------------------------
    //COMMANDS
    //--------------------------------------------------------------------------
    private void nameCheck(Message msg)
    {
        String[] s = msg.getMsg().split(" ");
        
        if (routingTable.containsKey(s[2]))   //if this router knows of an agent with that name already...
        {
            if (msg instanceof RouterMessage)    //if this command came from another router...
            {
                RouterMessage rMsg = (RouterMessage) msg;    //make it explicitly a RouterMessage so this method has access to its methods
                msg = rMsg.getContents();   //unpackage the contents within the RouterMessage and overwrite the msg variable with it
            }
            //create a reply message letting the sender know this
            ReplyMessage reply = new ReplyMessage(getName(), msg.getFrom(), "Name Declined", msg);
            pushToSubAgent(reply);  //send it back
        } else    //if it doesn't know of an agent with that name already...
        {
            if (msg instanceof SysMessage)   //if this command came from an agent
            {
                //create a RouterMessage to ask the other routers to check for the name as well
                DecoratedMessage rMsg = new RouterMessage(msg.getTo(), msg.getFrom(), "Name", msg);
                pushToSuperAgent(rMsg); //send it to the next router
            }
            if (msg instanceof RouterMessage)    //if this command cam from another router
            {
                pushToSuperAgent(msg);  //push it along to the next Router in line
            }
        }
    }

    //--------------------------------------------------------------------------
    //REGISTRATION
    //--------------------------------------------------------------------------
    /**
     * Method to register subAgents to this MetaAgent.
     *
     * @param msg
     */
    private void registration(SysMessage msg)   //when the router gets a registration request from a Portal
    {
        routingTable.put(msg.getAgent().getName(), msg.getAgent());   //registers the agent with its name as the key
        
        //creates a routermessage to tell the other routers to do the same
        RouterMessage rMsg = new RouterMessage(msg.getFrom(), msg.getTo(), "reg", msg, getName());
        pushToSuperAgent(rMsg); //gives the routermessage to the next router in line
    }
    private void registration(RouterMessage msg)    //when the router gets a registration request from a Router
    {
        SysMessage contents = (SysMessage)msg.getContents();    //unpackages the RouterMessage
        routingTable.put(contents.getAgent().getName(), contents.getAgent());  //registers the agent with its name as the key
        
        pushToSuperAgent(msg);  //passes on the RouterMessage to the next in line
    }

    /**
     * Method to de-register subAgents from this MetaAgent.
     *
     * @param msg A SysMessage object.
     */
    private void deregistration(SysMessage msg)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void run()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
