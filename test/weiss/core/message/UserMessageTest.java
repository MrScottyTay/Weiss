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
public class UserMessageTest
{

    public UserMessageTest()
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
     * Test of getScope method, of class UserMessage.
     */
    @Test
    public void testGetScope()
    {
        System.out.println("getScope");
        UserMessage instance = new UserMessage("Admin", "Test", "Hello");
        int expResult = 0;
        int result = instance.getScope();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetScope2()
    {
        System.out.println("getScope2");
        UserMessage instance = new UserMessage("Admin", "Test", "Hello", 1);
        int expResult = 1;
        int result = instance.getScope();
        assertEquals(expResult, result);
    }

}
