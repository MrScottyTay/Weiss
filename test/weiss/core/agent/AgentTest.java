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
        Agent instance = new Agent("Agent1", null);
        UserMessage msg = new UserMessage("admin", instance.getName(),"Test");
        instance.userMsgHandler(msg);
    }

    /**
     * Test of addClient method, of class Agent.
     */
    @Test
    public void testAddClient1()
    {
        System.out.println("addClient1");
        Agent instance = new Agent("Agent 1", null);
        instance.addClient(new Client(instance));
        
        assertEquals(instance.hasClient(), true);
    }
    /**
     * Test of removeClient method, of class Agent.
     */
    @Test
    public void testRemoveClient1()
    {
        System.out.println("removeClient1");
        Agent instance = new Agent("Agent 1", null);
        
        //Add new client.
        instance.addClient(new Client(instance));
        instance.removeClient();
        
        assertEquals(instance.hasClient(), false);
    }
    
    @Test
    public void testRemoveClient2()
    {
        System.out.println("removeClient2");
        Agent instance = new Agent("Agent 1", null);

        instance.removeClient();
        
        assertEquals(instance.hasClient(), false);
    }

    /**
     * Test of updateClient method, of class Agent.
     */
    @Test
    public void testUpdateClient1()
    {
        System.out.println("updateClient1");
        
        Agent instance = new Agent("Agent 1", null);
        Message msg = new UserMessage("admin", instance.getName(), "Test");
        instance.updateClient(msg);
        
        assertEquals(instance.hasClient(), false);
    }
    
    @Test
    public void testUpdateClient2()
    {
        System.out.println("updateClient2");
        
        Agent instance = new Agent("Agent 1", null);
        Client client = new Client(instance);
        
        instance.addClient(client);
        Message msg = new UserMessage("admin", instance.getName(), "Test");
        instance.updateClient(msg);

        assertEquals(instance.hasClient(), true);
    }
    
}
