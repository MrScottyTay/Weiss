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
import weiss.core.message.RouterMessage;
import weiss.core.message.SysMessage;
import weiss.core.message.UserMessage;

/**
 *
 * @author Adam Young
 */
public class PortalTest
{
    
    public PortalTest()
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
     * Test of userMsgHandler method, of class Portal.
     */
    @Test
    public void testUserMsgHandler()
    {
        System.out.println("userMsgHandler");
        UserMessage msg = null;
        Portal instance = null;
        instance.userMsgHandler(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pushToSubAgent method, of class Portal.
     */
    @Test
    public void testPushToSubAgent()
    {
        System.out.println("pushToSubAgent");
        Message msg = null;
        Portal instance = null;
        instance.pushToSubAgent(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of msgHandler method, of class Portal.
     */
    @Test
    public void testMsgHandler()
    {
        System.out.println("msgHandler");
        Message msg = null;
        Portal instance = null;
        instance.msgHandler(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of routerMsgHandler method, of class Portal.
     */
    @Test
    public void testRouterMsgHandler()
    {
        System.out.println("routerMsgHandler");
        RouterMessage msg = null;
        Portal instance = null;
        instance.routerMsgHandler(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sysMsgHandler method, of class Portal.
     */
    @Test
    public void testSysMsgHandler()
    {
        System.out.println("sysMsgHandler");
        SysMessage msg = null;
        Portal instance = null;
        instance.sysMsgHandler(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
