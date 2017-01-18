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
public class MsgScopingTest
{

    @Test
    public void portalMsgScopingTest()
    {
        System.out.println("msgScopingTest");

        MetaAgent portal1 = new Portal("P1", null);
        portal1.start();

        Agent agent1 = new Agent("A1", portal1, 0);
        agent1.start();

        Agent agent2 = new Agent("A2", portal1, 0);
        agent2.start();

        agent1.sendMessage("A2", "Hello World");
    }

    @Test
    public void portalMsgScopingTest2()
    {
        System.out.println("msgScopingTest2");

        MetaAgent portal1 = new Portal("P1", null);
        portal1.start();

        Agent agent1 = new Agent("A1", portal1, 1);
        agent1.start();

        Agent agent2 = new Agent("A2", portal1, 1);
        agent2.start();

        agent1.sendMessage("A2", "Hello World");
    }

    @Test
    public void portalMsgScopingTest3()
    {
        System.out.println("msgScopingTest2");

        MetaAgent portal1 = new Portal("P1", null);
        portal1.start();

        Agent agent1 = new Agent("A1", portal1, 2);
        agent1.start();

        Agent agent2 = new Agent("A2", portal1, 2);
        agent2.start();

        agent1.sendMessage("A2", "Hello World");
    }

    @Test
    public void routerMsgScopingTest()
    {
        System.out.println("routerMsgScopingTest");

        MetaAgent router = new Router("R1");
        router.start();

        MetaAgent portal1 = new Portal("P1", router);
        portal1.start();

        MetaAgent portal2 = new Portal("P2", router);
        portal2.start();

        Agent agent1 = new Agent("A1", portal1, 0);
        agent1.start();

        Agent agent2 = new Agent("A2", portal2, 0);
        agent2.start();

        agent1.sendMessage("A2", "Hello World");
    }

    @Test
    public void routerMsgScopingTest2()
    {
        System.out.println("routerMsgScopingTest2");
        MetaAgent router = new Router("R1");
        router.start();

        MetaAgent portal1 = new Portal("P1", router);
        portal1.start();

        MetaAgent portal2 = new Portal("P2", router);
        portal2.start();

        Agent agent1 = new Agent("A1", portal1, 1);
        agent1.start();

        Agent agent2 = new Agent("A2", portal2, 1);
        agent2.start();

        agent1.sendMessage("A2", "Hello World");
    }

    @Test
    public void routerMsgScopingTest3()
    {
        System.out.println("routerMsgScopingTest3");
        MetaAgent router = new Router("R1");
        router.start();

        MetaAgent portal1 = new Portal("P1", router);
        portal1.start();

        MetaAgent portal2 = new Portal("P2", router);
        portal2.start();

        Agent agent1 = new Agent("A1", portal1, 2);
        agent1.start();

        Agent agent2 = new Agent("A2", portal2, 2);
        agent2.start();

        agent1.sendMessage("A2", "Hello World");
    }

    @Test
    public void routerMsgScopingTest4()
    {
        System.out.println("routerMsgScopingTest4");
        MetaAgent router1 = new Router("R1");
        router1.start();

        MetaAgent router2 = new Router("R2");
        router2.start();

        MetaAgent portal1 = new Portal("P1", router1);
        portal1.start();

        MetaAgent portal2 = new Portal("P2", router2);
        portal2.start();

        Agent agent1 = new Agent("A1", portal1, 0);
        agent1.start();

        Agent agent2 = new Agent("A2", portal2, 0);
        agent2.start();

        agent1.sendMessage("A2", "Hello World");
    }

    @Test
    public void routerMsgScopingTest5()
    {
        System.out.println("routerMsgScopingTest5");
        MetaAgent router1 = new Router("R1");
        router1.start();

        MetaAgent router2 = new Router("R2");
        router2.start();

        MetaAgent portal1 = new Portal("P1", router1);
        portal1.start();

        MetaAgent portal2 = new Portal("P2", router2);
        portal2.start();

        Agent agent1 = new Agent("A1", portal1, 1);
        agent1.start();

        Agent agent2 = new Agent("A2", portal2, 1);
        agent2.start();

        agent1.sendMessage("A2", "Hello World");
    }

    @Test
    public void routerMsgScopingTest6()
    {
        System.out.println("routerMsgScopingTest5");
        MetaAgent router1 = new Router("R1");
        router1.start();

        MetaAgent router2 = new Router("R2");
        router2.start();

        MetaAgent portal1 = new Portal("P1", router1);
        portal1.start();

        MetaAgent portal2 = new Portal("P2", router2);
        portal2.start();

        Agent agent1 = new Agent("A1", portal1, 2);
        agent1.start();

        Agent agent2 = new Agent("A2", portal2, 2);
        agent2.start();

        agent1.sendMessage("A2", "Hello World");
    }

}
