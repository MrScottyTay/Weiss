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
package weiss.projectTest;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import weiss.core.agent.Agent;
import weiss.core.agent.MetaAgent;
import weiss.core.agent.Portal;
import weiss.core.agent.Router;
import weiss.management.nodeMonitor.NodeMonitor;

/**
 *
 * @author Adam Young
 */
public class NodeMonitorTest
{

    @Test
    public void nodeMonitorTest()
    {
        NodeMonitor node = new NodeMonitor("test");

        MetaAgent portal1 = new Portal("P1", null);
        portal1.start();
        portal1.addNodeMonitor(node);

        Agent agent1 = new Agent("A1", portal1, 2);
        agent1.start();

        Agent agent2 = new Agent("A2", portal1, 2);
        agent2.start();

        agent1.sendMessage("A2", "Hello World");
        assertEquals(node.getLastMessage().getFrom(), "A1");
    }
}
