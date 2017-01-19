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
package weiss.core.message;

import weiss.core.agent.MetaAgent;

/**
 * Class extending the {@link Message Message} class, to be used for system
 * message transmission. This type of message can be read by all MetaAgents, and
 * contains instructions rather than text.
 * <p>
 * The alternative of this message type is {@link UserMessage UserMessage}.
 *
 * @author Adam Young, Teesside University Sch. of Computing
 * @author Scott Taylor, Teesside University Sch. of Computing
 */
public class SysMessage extends Message
{
    public enum SysType { REGISTER, DEREGISTER, SETSUPER }

    
    private MetaAgent agent;
    private final SysType msgType;

    /**
     * Constructor for the SysMessage class.
     *
     * @param from String of message sender.
     * @param to String of message receiver.
     * @param msgType Enum of message type.
     * @param agent MetaAgent to be used in routing tables.
     */
    public SysMessage(String from, String to, SysType msgType, MetaAgent agent)
    {
        super(from,to);
        this.agent = agent;
        this.msgType = msgType;
    }

    /**
     * Constructor for the SysMessage class.
     *
     * @param from String of message sender.
     * @param to String of message receiver.
     * @param msgType Enum of message type.
     */
    public SysMessage(String from, String to, SysType msgType)
    {
        super(from,to);
        this.msgType = msgType; 
    }

    //--------------------------------------------------------------------------
    //GETTERS
    /**
     * Method to return the agent stored.
     *
     * @return A MetaAgent variable.
     */
    public MetaAgent getAgent()
    {
        return agent;
    }
    
    public SysType getSysType()
    {
        return msgType;
    }

    @Override
    public String toString()
    {
        return super.toString() + "\nAgent: " + agent.getName();
    }
}
