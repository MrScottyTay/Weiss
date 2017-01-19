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
package weiss.management.client;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import weiss.core.agent.Agent;
import weiss.core.message.Message;
import weiss.core.message.UserMessage;
import weiss.management.AgentImpl;

/**
 *
 * @author Adam Young
 */
public class ClientTest
{

    public ClientTest()
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
     * Test of updateClient method, of class Client.
     */
    @Test
    public void testUpdateClient()
    {
        System.out.println("updateClient");
        UserMessage usrMsg = new UserMessage("Admin", "Test", "Hello");

        AgentImpl agent = new AgentImpl("A1", null);
        Client instance = new Client(agent);
        agent.addClient(instance);

        instance.updateClient(usrMsg);
    }

}
