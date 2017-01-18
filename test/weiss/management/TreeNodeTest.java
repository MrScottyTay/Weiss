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

import javax.swing.ImageIcon;
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
public class TreeNodeTest
{

    public TreeNodeTest()
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
     * Test of getAgentRef method, of class TreeNode.
     */
    @Test
    public void testGetAgentRef()
    {
        System.out.println("getAgentRef");
        MetaAgent expResult = new Agent("A1", null);
        TreeNode instance = new TreeNode(null, expResult,
                null);
        MetaAgent result = instance.getAgentRef();
        assertEquals(expResult, result);
    }

    /**
     * Test of getImage method, of class TreeNode.
     */
    @Test
    public void testGetImage()
    {
        System.out.println("getImage");
        ImageIcon expResult = new ImageIcon("src/images/weiss20px.png");

        TreeNode instance = new TreeNode(null, null, expResult);
        ImageIcon result = instance.getImage();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class TreeNode.
     */
    @Test
    public void testGetName()
    {
        System.out.println("getName");
        TreeNode instance = new TreeNode("Test", null,
                null);
        String expResult = "Test";
        String result = instance.getName();
        assertEquals(expResult, result);
    }

}
