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

    private MetaAgent agent;

    /**
     * Constructor for the SysMessage class.
     *
     * @param f String of message sender.
     * @param t String of message receiver.
     * @param m String of message contents.
     * @param a MetaAgent to be used in routing tables.
     */
    public SysMessage(String f, String t, String m, MetaAgent a)
    {
        super(f, t, m);
        agent = a;
    }

    /**
     * Constructor for the SysMessage class.
     *
     * @param f String of message sender.
     * @param t String of message receiver.
     * @param m String of message contents.
     */
    public SysMessage(String f, String t, String m)
    {
        super(f, t, m);
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

    @Override
    public String toString()
    {
        return super.toString() + "\nAgent: " + agent.getName();
    }
}
