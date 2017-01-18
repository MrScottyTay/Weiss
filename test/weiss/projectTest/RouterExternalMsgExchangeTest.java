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
public class RouterExternalMsgExchangeTest
{

    @Test
    public void testRouterExtMsgExchange()
    {
        System.out.println("routerExtMsgExchange");
        MetaAgent router1 = new Router("R1");
        router1.start();

        MetaAgent router2 = new Router("R2");
        router2.start();

        MetaAgent portal1 = new Portal("P1", router1);
        portal1.start();

        MetaAgent portal2 = new Portal("P2", router2);
        portal2.start();

        Agent agent1 = new Agent("A1", portal1);
        agent1.start();

        Agent agent2 = new Agent("A2", portal2);
        agent2.start();

        agent1.sendMessage("A2", "Hello World");
    }

    @Test
    public void testRouterExtMsgExchange2()
    {
        System.out.println("routerExtMsgExchange");
        MetaAgent router1 = new Router("R1");
        router1.start();

        MetaAgent router2 = new Router("R2");
        router2.start();

        MetaAgent router3 = new Router("R3");
        router3.start();

        MetaAgent portal1 = new Portal("P1", router1);
        portal1.start();

        MetaAgent portal2 = new Portal("P2", router3);
        portal2.start();

        Agent agent1 = new Agent("A1", portal1);
        agent1.start();

        Agent agent2 = new Agent("A2", portal2);
        agent2.start();

        agent1.sendMessage("A2", "Hello World");
    }
}
