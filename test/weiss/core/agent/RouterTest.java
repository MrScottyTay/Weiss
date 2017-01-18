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
import weiss.core.message.RouterMessage;
import weiss.core.message.SysMessage;
import weiss.core.message.UserMessage;

/**
 *
 * @author Adam Young
 */
public class RouterTest
{

    public RouterTest()
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
     * Test of userMsgHandler method, of class Router.
     */
    @Test
    public void testUserMsgHandler()
    {
        System.out.println("userMsgHandler");
        UserMessage msg = new UserMessage("Admin", "Test", "Hello");
        Router instance = new Router("R1");
        instance.userMsgHandler(msg);
    }

    /**
     * Test of routerMsgHandler method, of class Router.
     */
    @Test
    public void testRouterMsgHandler()
    {
        System.out.println("routerMsgHandler");

        UserMessage userMsg = new UserMessage("Admin", "Test", "Hello");
        Router instance = new Router("R1");
        Router r2 = new Router("R2");
        RouterMessage msg = new RouterMessage("Admin", "Test", userMsg, instance.getName());

        instance.routerMsgHandler(msg);
    }

    /**
     * Test of sysMsgHandler method, of class Router.
     */
    @Test
    public void testSysMsgHandler()
    {
        Portal portal = new Portal("P1", null);
        System.out.println("sysMsgHandler");
        SysMessage msg = new SysMessage("Admin", "Test", "reg", portal);
        Router instance = new Router("R1");
        instance.sysMsgHandler(msg);
    }

}
