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
package weiss.management;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
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
public class WeissManagerTest
{

    public WeissManagerTest()
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
     * Test of getAgentSelectBtn method, of class WeissManager.
     */
    @Test
    public void testGetAgentSelectBtn()
    {
        System.out.println("getAgentSelectBtn");
        WeissManager instance = new WeissManager();
        JButton expResult = instance.getAgentSelectBtn();
        JButton result = instance.getAgentSelectBtn();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNodeMonitorBtn method, of class WeissManager.
     */
    @Test
    public void testGetNodeMonitorBtn()
    {
        System.out.println("getNodeMonitorBtn");
        WeissManager instance = new WeissManager();
        JButton expResult = instance.getNodeMonitorBtn();
        JButton result = instance.getNodeMonitorBtn();
        assertEquals(expResult, result);;
    }

    /**
     * Test of getScopeBox method, of class WeissManager.
     */
    @Test
    public void testGetScopeBox()
    {
        System.out.println("getScopeBox");
        WeissManager instance = new WeissManager();
        JComboBox expResult = instance.getScopeBox();
        JComboBox result = instance.getScopeBox();
        assertEquals(expResult, result);
    }

}
