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
import weiss.core.message.UserMessage;
import weiss.management.AgentImpl;

/**
 *
 * @author Adam Young
 */
public class ManagableTest
{

    public ManagableTest()
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
     * Test of addClient method, of class Managable.
     */
    @Test
    public void testAddClient()
    {
        System.out.println("addClient");

        Managable instance = new ManagableImpl();
        Client client = new Client(new AgentImpl("A1", null));
        instance.addClient(client);
        assertEquals(instance.hasClient(), true);
    }

    /**
     * Test of removeClient method, of class Managable.
     */
    @Test
    public void testRemoveClient()
    {
        System.out.println("removeClient");
        Managable instance = new ManagableImpl();
        instance.removeClient();
        assertEquals(instance.hasClient(), false);
    }

    /**
     * Test of hasClient method, of class Managable.
     */
    @Test
    public void testHasClient()
    {
        System.out.println("hasClient");
        Managable instance = new ManagableImpl();
        boolean expResult = false;
        boolean result = instance.hasClient();
        assertEquals(expResult, result);
    }

    @Test
    public void testHasClient2()
    {
        System.out.println("hasClient2");
        Managable instance = new ManagableImpl();
        boolean expResult = true;
        instance.addClient(new Client(new AgentImpl("A1", null)));
        boolean result = instance.hasClient();
        assertEquals(expResult, result);
    }

    public class ManagableImpl implements Managable
    {

        private Client client;

        @Override
        public void addClient(Client client)
        {
            this.client = client;
        }

        @Override
        public void removeClient()
        {
            client = null;
        }

        @Override
        public boolean hasClient()
        {
            return client != null;
        }

        @Override
        public void updateClient(UserMessage msg)
        {
            if(client != null)
                client.updateClient(msg);
        }
    }

}
