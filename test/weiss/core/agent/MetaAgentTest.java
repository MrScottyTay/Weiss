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
        MetaAgent instance = null;
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of start method, of class MetaAgent.
     */
    @Test
    public void testStart()
    {
        System.out.println("start");
        MetaAgent instance = null;
        instance.start();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class MetaAgent.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        MetaAgent instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSuperAgent method, of class MetaAgent.
     */
    @Test
    public void testGetSuperAgent()
    {
        System.out.println("getSuperAgent");
        MetaAgent instance = null;
        MetaAgent expResult = null;
        MetaAgent result = instance.getSuperAgent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScope method, of class MetaAgent.
     */
    @Test
    public void testGetScope()
    {
        System.out.println("getScope");
        MetaAgent instance = null;
        int expResult = 0;
        int result = instance.getScope();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSuperAgent method, of class MetaAgent.
     */
    @Test
    public void testSetSuperAgent()
    {
        System.out.println("setSuperAgent");
        MetaAgent superAgent = null;
        MetaAgent instance = null;
        instance.setSuperAgent(superAgent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setScope method, of class MetaAgent.
     */
    @Test
    public void testSetScope()
    {
        System.out.println("setScope");
        int scope = 0;
        MetaAgent instance = null;
        instance.setScope(scope);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of msgHandler method, of class MetaAgent.
     */
    @Test
    public void testMsgHandler()
    {
        System.out.println("msgHandler");
        Message msg = null;
        MetaAgent instance = null;
        instance.msgHandler(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of userMsgHandler method, of class MetaAgent.
     */
    @Test
    public void testUserMsgHandler()
    {
        System.out.println("userMsgHandler");
        UserMessage msg = null;
        MetaAgent instance = null;
        instance.userMsgHandler(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendMessage method, of class MetaAgent.
     */
    @Test
    public void testSendMessage()
    {
        System.out.println("sendMessage");
        String to = "";
        String message = "";
        MetaAgent instance = null;
        instance.sendMessage(to, message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pushToSuperAgent method, of class MetaAgent.
     */
    @Test
    public void testPushToSuperAgent()
    {
        System.out.println("pushToSuperAgent");
        Message msg = null;
        MetaAgent instance = null;
        instance.pushToSuperAgent(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addNodeMonitor method, of class MetaAgent.
     */
    @Test
    public void testAddNodeMonitor()
    {
        System.out.println("addNodeMonitor");
        NodeMonitor nodeMonitor = null;
        MetaAgent instance = null;
        instance.addNodeMonitor(nodeMonitor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeNodeMonitor method, of class MetaAgent.
     */
    @Test
    public void testRemoveNodeMonitor()
    {
        System.out.println("removeNodeMonitor");
        MetaAgent instance = null;
        instance.removeNodeMonitor();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateNodeMonitor method, of class MetaAgent.
     */
    @Test
    public void testUpdateNodeMonitor()
    {
        System.out.println("updateNodeMonitor");
        Message msg = null;
        MetaAgent instance = null;
        instance.updateNodeMonitor(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class MetaAgentImpl extends MetaAgent
    {

        public MetaAgentImpl()
        {
            super("", null);
        }

        public void userMsgHandler(UserMessage msg)
        {
        }
    }
    
}
