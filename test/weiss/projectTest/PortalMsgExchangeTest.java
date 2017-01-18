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

import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import weiss.core.agent.Agent;
import weiss.core.agent.MetaAgent;
import weiss.core.agent.Portal;
import weiss.core.message.Message;
import weiss.core.message.RouterMessage;
import weiss.core.message.SysMessage;
import weiss.core.message.UserMessage;
import weiss.management.nodeMonitor.NodeMonitor;

/**
 *
 * @author Adam Young
 */
public class PortalMsgExchangeTest
{

    public PortalMsgExchangeTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }

    /**
     * Test of userMsgHandler method, of class Portal.
     */
    @Test
    public void testPortalMsgExchange()
    {
        System.out.println("portalMsgExchange");
        MetaAgent portal1 = new Portal("P1", null);

        portal1.start();

        Agent agent1 = new Agent("A1", portal1);
        agent1.start();

        Agent agent2 = new Agent("A2", portal1);
        agent2.start();

        agent1.sendMessage("A2", "Hello World");
    }

    @Test
    public void testPortalMsgExchange2()
    {
        System.out.println("portalMsgExchange2");
        MetaAgent portal1 = new Portal("P1", null);
        portal1.start();

        Agent agent1 = new Agent("A1", portal1);
        agent1.start();

        Agent agent2 = new Agent("A2", portal1);
        agent2.start();

        Agent agent3 = new Agent("A3", portal1);
        agent3.start();

        agent1.sendMessage("A3", "Hello World");
    }
}
