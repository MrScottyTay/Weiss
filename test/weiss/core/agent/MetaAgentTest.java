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
import weiss.core.message.SysMessage;
import weiss.core.message.UserMessage;
import weiss.management.nodeMonitor.NodeMonitor;

/**
 *
 * @author Adam Young
 */
public class MetaAgentTest
{

    public MetaAgentTest()
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
     * Test of run method, of class MetaAgent.
     */
    @Test
    public void testRun()
    {
        System.out.println("run");
        MetaAgent instance = new Portal("Portal 1", null);
        instance.start();
        instance.addNodeMonitor(new NodeMonitor(instance.getName()));

        assertEquals(instance.hasNodeMonitor(), true);
    }

    /**
     * Test of start method, of class MetaAgent.
     */
    @Test
    public void testStart()
    {
        System.out.println("start");
        MetaAgent instance = new Portal("Portal 1", null);
        instance.start();
        instance.addNodeMonitor(new NodeMonitor(instance.getName()));

        assertEquals(instance.hasNodeMonitor(), true);
    }

    /**
     * Test of getName method, of class MetaAgent.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        MetaAgent instance = new Agent("Agent 1", null);
        String expResult = "Agent 1";
        String result = instance.getName();
    }

    @Test
    public void testGetName2()
    {
        System.out.println("getName2");
        MetaAgent instance = new Agent(null, null);
        String expResult = null;
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSuperAgent method, of class MetaAgent.
     */
    @Test
    public void testGetSuperAgent()
    {
        System.out.println("getSuperAgent");
        MetaAgent expResult = new Portal("Portal 1", null);
        MetaAgent instance = new Agent("Agent 1", expResult);

        MetaAgent result = instance.getSuperAgent();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetSuperAgent2()
    {
        System.out.println("getSuperAgent2");
        MetaAgent expResult = null;
        MetaAgent instance = new Agent("Agent 1", expResult);

        MetaAgent result = instance.getSuperAgent();
        assertEquals(expResult, result);
    }

    /**
     * Test of setSuperAgent method, of class MetaAgent.
     */
    @Test
    public void testSetSuperAgent()
    {
        System.out.println("setSuperAgent");

        MetaAgent superAgent = new Portal("P1", null);
        MetaAgent instance = new Agent("Agent 1", superAgent);
        instance.setSuperAgent(superAgent);

        assertEquals(instance.getSuperAgent(), superAgent);
    }

    @Test
    public void testSetSuperAgent2()
    {
        System.out.println("setSuperAgent2");

        MetaAgent superAgent = null;
        MetaAgent instance = new Agent("Agent 1", superAgent);
        instance.setSuperAgent(superAgent);

        assertEquals(instance.getSuperAgent(), superAgent);
    }

    /**
     * Test of msgHandler method, of class MetaAgent.
     */
    @Test
    public void testMsgHandler()
    {
        System.out.println("msgHandler");
        Message msg = new UserMessage("Admin", "Agent 1", "Hello World!");
        MetaAgent instance = new Agent("Agent 1", null);
        instance.msgHandler(msg);

        assertEquals(instance.peek(), null);
    }

    /**
     * Test of pushToSuperAgent method, of class MetaAgent.
     */
    @Test
    public void testPushToSuperAgent()
    {
        System.out.println("pushToSuperAgent");

        MetaAgent router = new Router("R1");
        MetaAgent instance = new Portal("P1", router);

        Message msg = new SysMessage("P1", "R1", "setSuperAgent", instance);
        instance.pushToSuperAgent(msg);
    }

    @Test
    public void testPushToSuperAgent2()
    {
        System.out.println("pushToSuperAgent");

        MetaAgent router = new Router("R1");
        MetaAgent instance = new Portal("P1", router);

        instance.pushToSuperAgent(null);
    }

    /**
     * Test of addNodeMonitor method, of class MetaAgent.
     */
    @Test
    public void testAddNodeMonitor()
    {
        System.out.println("addNodeMonitor");
        MetaAgent instance = new Router("R1");
        instance.addNodeMonitor(new NodeMonitor(instance.getName()));

        assertEquals(instance.hasNodeMonitor(), true);
    }

    @Test
    public void testAddNodeMonitor2()
    {
        System.out.println("addNodeMonitor2");
        MetaAgent instance = new Router("R1");
        instance.addNodeMonitor(null);

        assertEquals(instance.hasNodeMonitor(), false);
    }

    /**
     * Test of removeNodeMonitor method, of class MetaAgent.
     */
    @Test
    public void testRemoveNodeMonitor()
    {
        System.out.println("removeNodeMonitor");
        MetaAgent instance = new Router("R1");
        instance.addNodeMonitor(new NodeMonitor(instance.getName()));
        instance.removeNodeMonitor();

        assertEquals(instance.hasNodeMonitor(), false);
    }

    @Test
    public void testRemoveNodeMonitor2()
    {
        System.out.println("removeNodeMonitor2");
        MetaAgent instance = new Router("R1");
        instance.removeNodeMonitor();

        assertEquals(instance.hasNodeMonitor(), false);
    }

    /**
     * Test of updateNodeMonitor method, of class MetaAgent.
     */
    @Test
    public void testUpdateNodeMonitor()
    {
        System.out.println("updateNodeMonitor");
        Message msg = new UserMessage("Admin", "A1", "Hello");
        MetaAgent instance = new Agent("A1", null);

        NodeMonitor nm = new NodeMonitor(instance.getName());
        instance.addNodeMonitor(nm);
        instance.updateNodeMonitor(msg);

        assertEquals(nm.getLastMessage().toString(), msg.toString());
    }

    @Test
    public void testUpdateNodeMonitor2()
    {
        System.out.println("updateNodeMonitor2");
        Message msg = new UserMessage("Admin", "A1", "Hello");
        MetaAgent instance = new Agent("A1", null);

        instance.updateNodeMonitor(msg);
    }

    /**
     * Test of userMsgHandler method, of class MetaAgent.
     */
    @Test
    public void testUserMsgHandler()
    {
        System.out.println("userMsgHandler");
        UserMessage msg = new UserMessage("Admin", "Test", "Hello");
        MetaAgent instance = new MetaAgentImpl("A1");
        instance.userMsgHandler(msg);
        assertEquals(instance.getSuperAgent(), instance);
    }

    /**
     * Test of hasNodeMonitor method, of class MetaAgent.
     */
    @Test
    public void testHasNodeMonitor()
    {
        System.out.println("hasNodeMonitor");
        MetaAgent instance = new MetaAgentImpl("A1");
        boolean expResult = false;
        boolean result = instance.hasNodeMonitor();
        assertEquals(expResult, result);
    }

    @Test
    public void testHasNodeMonitor2()
    {
        System.out.println("hasNodeMonitor2");
        MetaAgent instance = new MetaAgentImpl("A1");
        instance.addNodeMonitor(new NodeMonitor(instance.getName()));
        boolean expResult = true;
        boolean result = instance.hasNodeMonitor();
        assertEquals(expResult, result);
    }

    public class MetaAgentImpl extends MetaAgent
    {

        public MetaAgentImpl(String name)
        {
            super(name, null);
        }

        @Override
        public void userMsgHandler(UserMessage msg)
        {
            this.setSuperAgent(this);
        }
    }

}
