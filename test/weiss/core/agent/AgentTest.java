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

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import weiss.core.message.Message;
import weiss.core.message.UserMessage;
import weiss.management.client.Client;
import weiss.management.nodeMonitor.NodeMonitor;

/**
 *
 * @author Adam Young
 */
public class AgentTest
{

    public AgentTest()
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
     * Test of userMsgHandler method, of class Agent.
     */
    @Test
    public void testUserMsgHandler()
    {
        System.out.println("userMsgHandler");
        Agent instance = new Agent("Agent1", null);
        UserMessage msg = new UserMessage("admin", instance.getName(), "Test");
        instance.userMsgHandler(msg);
    }

    

    /**
     * Test of sendMessage method, of class Agent.
     */
    @Test
    public void testSendMessage()
    {
        System.out.println("sendMessage");
        String to = "Admin";
        String message = "Hello";

        Portal portal = new Portal("P1", null);
        Agent instance = new Agent("A1", portal);
        instance.sendMessage(to, message);
    }

    @Test
    public void testSendMessage2()
    {
        System.out.println("sendMessage2");
        String to = "Admin";
        String message = null;

        Portal portal = new Portal("P1", null);
        Agent instance = new Agent("A1", portal);
        instance.sendMessage(to, message);
    }

    @Test
    public void testSendMessage3()
    {
        System.out.println("sendMessage3");
        String to = null;
        String message = "Hello";

        Portal portal = new Portal("P1", null);
        Agent instance = new Agent("A1", portal);
        instance.sendMessage(to, message);
    }

    /**
     * Test of hasClient method, of class Agent.
     */
    

    /**
     * Test of getScope method, of class Agent.
     */
    @Test
    public void testGetScope()
    {
        System.out.println("getScope");
        Agent instance = new Agent("A1", null);
        int expResult = 0;
        int result = instance.getScope();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetScope2()
    {
        System.out.println("getScope2");
        Agent instance = new Agent("A1", null, 1);
        int expResult = 1;
        int result = instance.getScope();
        assertEquals(expResult, result);
    }

}
