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
import weiss.core.agent.Agent;
import weiss.core.agent.MetaAgent;

/**
 *
 * @author Adam Young
 */
public class SysMessageTest
{
    
    public SysMessageTest()
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
     * Test of getAgent method, of class SysMessage.
     */
    @Test
    public void testGetAgent()
    {
        System.out.println("getAgent");
        Agent agent = new Agent("A1", null);
        
        SysMessage instance = new SysMessage("Admin", "Test", "reg", agent);
        MetaAgent expResult = agent;
        MetaAgent result = instance.getAgent();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class SysMessage.
     */
    @Test
    public void testToString()
    {
        System.out.println("toString");
        
        Agent agent = new Agent("A1", null);
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date now = Calendar.getInstance().getTime();
        String timestamp = df.format(now);

        SysMessage instance = new SysMessage("Admin", "Test", "reg", agent);
        String expResult = "From: Admin\nTo: Test\nMessage: reg\nTime Sent: " 
                + timestamp + "\nAgent: " + agent.getName();
        
        String result = instance.toString();
        assertEquals(expResult, result);
    }
    
}
