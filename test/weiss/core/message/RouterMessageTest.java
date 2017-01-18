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
package weiss.core.message;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Adam Young
 */
public class RouterMessageTest
{

    public RouterMessageTest()
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
     * Test of getOrigin method, of class RouterMessage.
     */
    @Test
    public void testGetOrigin()
    {
        System.out.println("getOrigin");
        UserMessage msg = new UserMessage("Greg", "Fred", "Hi");

        RouterMessage instance = new RouterMessage("Admin", "Test", msg, "Router 1");
        String expResult = "Router 1";
        String result = instance.getOrigin();
        assertEquals(expResult, result);
    }

    /**
     * Test of getContents method, of class RouterMessage.
     */
    @Test
    public void testGetContents()
    {
        System.out.println("getContents");

        UserMessage msg = new UserMessage("Greg", "Fred", "Hi");

        RouterMessage instance = new RouterMessage("Admin", "Test", msg, "Router 1");
        Message expResult = msg;
        Message result = instance.getContents();
        assertEquals(expResult, result);
    }

}
