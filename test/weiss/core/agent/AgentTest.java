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
        UserMessage msg = null;
        Agent instance = null;
        instance.userMsgHandler(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addClient method, of class Agent.
     */
    @Test
    public void testAddClient()
    {
        System.out.println("addClient");
        Client client = null;
        Agent instance = null;
        instance.addClient(client);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeClient method, of class Agent.
     */
    @Test
    public void testRemoveClient()
    {
        System.out.println("removeClient");
        Agent instance = null;
        instance.removeClient();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateClient method, of class Agent.
     */
    @Test
    public void testUpdateClient()
    {
        System.out.println("updateClient");
        Message msg = null;
        Agent instance = null;
        instance.updateClient(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
