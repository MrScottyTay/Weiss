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
package weiss.management.nodeMonitor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import weiss.core.agent.Agent;
import weiss.core.message.Message;
import weiss.core.message.UserMessage;

/**
 *
 * @author Adam Young
 */
public class NodeMonitorTest
{

    public NodeMonitorTest()
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
     * Test of insertTableData method, of class NodeMonitor.
     */
    @Test
    public void testInsertTableData()
    {
        System.out.println("insertTableData");
        Message msg = new UserMessage("Admin", "Test", "Hello");
        NodeMonitor instance = new NodeMonitor("NM1");
        instance.insertTableData(msg);
        assertEquals(instance.getLastMessage().toString(), msg.toString());
    }

    /**
     * Test of getLastMessage method, of class NodeMonitor.
     */
    @Test
    public void testGetLastMessage()
    {
        System.out.println("getLastMessage");
        NodeMonitor instance = new NodeMonitor("NM1");
        Message msg = new UserMessage("Admin", "Test", "Hello");
        instance.insertTableData(msg);

        String expResult = msg.toString();
        String result = instance.getLastMessage().toString();

        assertEquals(expResult, result);
    }

}
