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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
public class MessageTest
{

    public MessageTest()
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
     * Test of getFrom method, of class Message.
     */
    @Test
    public void testGetFrom()
    {
        System.out.println("getFrom");
        Message instance = new MessageImpl("Admin", "Test", "Hello World");
        String expResult = "Admin";
        String result = instance.getFrom();
        assertEquals(expResult, result);

    }

    public void testGetFrom2()
    {
        System.out.println("getFrom2");
        Message instance = new MessageImpl(null, "Test", "Hello World");
        String expResult = null;
        String result = instance.getFrom();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTo method, of class Message.
     */
    @Test
    public void testGetTo()
    {
        System.out.println("getTo");
        Message instance = new MessageImpl("Admin", "Test", "Hello World");
        String expResult = "Test";
        String result = instance.getTo();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetTo2()
    {
        System.out.println("getTo2");
        Message instance = new MessageImpl("Admin", null, "Hello World");
        String expResult = null;
        String result = instance.getTo();
        assertEquals(expResult, result);
    }

    /**
     * Test of getMsg method, of class Message.
     */
    @Test
    public void testGetMsg()
    {
        System.out.println("getMsg");
        MessageImpl instance = new MessageImpl("Admin", "Test", "Hello World");
        String expResult = "Hello World";
        String result = instance.getMsg();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetMsg2()
    {
        System.out.println("getMsg2");
        MessageImpl instance = new MessageImpl("Admin", "Test", null);
        String expResult = null;
        String result = instance.getMsg();
        assertEquals(expResult, result);
    }

    /**
     * Test of getTime method, of class Message.
     */
    @Test
    public void testGetTime()
    {
        System.out.println("getTime");
        Message instance = new MessageImpl("Admin", "Test", "Hello World");

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date now = Calendar.getInstance().getTime();
        String expResult = df.format(now);

        String result = instance.getTime();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Message.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        Message instance = new MessageImpl("Admin", "Test", "Hello World");

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date now = Calendar.getInstance().getTime();
        String timestamp = df.format(now);

        String expResult = "\nFrom: Admin\nTo: Test\nTime Sent: " + timestamp;
        String result = instance.toString();
        assertEquals(expResult, result);
    }

    public class MessageImpl extends Message
    {
        private String message;
        
        public MessageImpl(String from, String to, String message)
        {
            super(from, to);
            this.message = message;
        }
        
        public String getMsg()
        {
            return message;
        }
    }

}
