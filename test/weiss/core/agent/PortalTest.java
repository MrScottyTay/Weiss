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
        UserMessage msg = new UserMessage("Admin", "A2", "Hello");
        
        Portal portal = new Portal("P1", null);
        Agent agent2 = new Agent("A2", portal);
        
        portal.userMsgHandler(msg);
    }

    /**
     * Test of pushToSubAgent method, of class Portal.
     */
    @Test
    public void testPushToSubAgent()
    {
        System.out.println("pushToSubAgent");

        Message msg = new UserMessage("A2", "A1", "Hello");
        Portal instance = new Portal("P1", null);
        Agent agent = new Agent("A1", instance);
        
        instance.pushToSubAgent(msg);
    }

    /**
     * Test of msgHandler method, of class Portal.
     */
    @Test
    public void testMsgHandler()
    {
        System.out.println("msgHandler");
        
        Message msg = new UserMessage("A2", "A1", "Hello");
        Portal instance = new Portal("P1", null);
        Agent agent = new Agent("A1", instance);
        
        instance.msgHandler(msg);
    }

    /**
     * Test of routerMsgHandler method, of class Portal.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testRouterMsgHandler()
    {
        System.out.println("routerMsgHandler");
        RouterMessage msg = new RouterMessage("R1", "R2","Hello",
                new UserMessage("Admin", "A1", "Hello"), "testClass");
        Portal instance = new Portal("P1", null);
        instance.routerMsgHandler(msg);
        
    }

    /**
     * Test of sysMsgHandler method, of class Portal.
     */
    @Test
    public void testSysMsgHandler()
    {
        System.out.println("sysMsgHandler");
        SysMessage msg = new SysMessage("Admin", "P1", "reg", new Portal("P2", null));
        Portal instance = new Portal("P1", null);
        instance.sysMsgHandler(msg);
    }
}
