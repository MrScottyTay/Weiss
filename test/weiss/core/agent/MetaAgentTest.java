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
        instance.addNodeMonitor(new NodeMonitor(instance));
        
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
        instance.addNodeMonitor(new NodeMonitor(instance));
        
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
     * Test of sendMessage method, of class MetaAgent.
     */
    @Test
    public void testSendMessage()
    {
        System.out.println("sendMessage");
        String to = "Agent 1";
        String message = "Hello";
        MetaAgent instance = new Agent("Agent 1", null);
    
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

    /**
     * Test of addNodeMonitor method, of class MetaAgent.
     */
    @Test
    public void testAddNodeMonitor()
    {
        System.out.println("addNodeMonitor");
        MetaAgent instance = new Router("R1");
        instance.addNodeMonitor(new NodeMonitor(instance));
       
        assertEquals(instance.hasNodeMonitor(), true);
    }

    /**
     * Test of removeNodeMonitor method, of class MetaAgent.
     */
    @Test
    public void testRemoveNodeMonitor()
    {
        System.out.println("addNodeMonitor");
        MetaAgent instance = new Router("R1");
        instance.addNodeMonitor(new NodeMonitor(instance));
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
        
        NodeMonitor nm = new NodeMonitor(instance);
        instance.addNodeMonitor(nm);
        instance.updateNodeMonitor(msg);
        
        assertEquals(nm.getLastMessage(), msg.toString());
    }

    public class MetaAgentImpl extends MetaAgent
    {

        public MetaAgentImpl()
        {
            super("", null);
        }

        @Override
        public void userMsgHandler(UserMessage msg)
        {
            this.setSuperAgent(this);
        }
    }
    
}
