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

/**
 *
 * @author Adam Young
 */
public class MonitorableTest
{

    public MonitorableTest()
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
     * Test of addNodeMonitor method, of class Monitorable.
     */
    @Test
    public void testAddNodeMonitor()
    {
        System.out.println("addNodeMonitor");
        NodeMonitor nodeMonitor = new NodeMonitor("NM1");
        Monitorable instance = new MonitorableImpl();
        instance.addNodeMonitor(nodeMonitor);

        assertEquals(instance.hasNodeMonitor(), true);
    }

    /**
     * Test of removeNodeMonitor method, of class Monitorable.
     */
    @Test
    public void testRemoveNodeMonitor()
    {
        System.out.println("removeNodeMonitor");
        Monitorable instance = new MonitorableImpl();
        instance.removeNodeMonitor();
        assertEquals(instance.hasNodeMonitor(), false);
    }

    /**
     * Test of hasNodeMonitor method, of class Monitorable.
     */
    @Test
    public void testHasNodeMonitor()
    {
        System.out.println("hasNodeMonitor");
        Monitorable instance = new MonitorableImpl();
        boolean expResult = false;
        boolean result = instance.hasNodeMonitor();
        assertEquals(expResult, result);
    }

    @Test
    public void testHasNodeMonitor2()
    {
        System.out.println("hasNodeMonitor2");
        Monitorable instance = new MonitorableImpl();
        boolean expResult = true;
        instance.addNodeMonitor(new NodeMonitor("NM1"));
        boolean result = instance.hasNodeMonitor();
        assertEquals(expResult, result);
    }

    public class MonitorableImpl implements Monitorable
    {

        private NodeMonitor monitor;

        @Override
        public void addNodeMonitor(NodeMonitor nodeMonitor)
        {
            this.monitor = nodeMonitor;
        }

        @Override
        public void removeNodeMonitor()
        {
            monitor = null;
        }

        @Override
        public boolean hasNodeMonitor()
        {
            return monitor != null;
        }
    }

}
