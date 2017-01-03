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
import weiss.MetaAgent.Agent;
import weiss.MetaAgent.Portal;
import weiss.MetaAgent.MetaAgent;
import Weiss.Manager.NodeMonitor;
import Weiss.Manager.WeissManager;
import java.util.logging.*;
import weiss.message.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 * @author Scott Taylor, Teesside University Sch. of Computing
 * @author Adam Young, Teesside University Sch. of Computing
 */
public class Weiss {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        WeissManager manager = new WeissManager();
        
        /*
        MetaAgent portal = new Portal("P1", null);
        portal.start();
        
        MetaAgent agent1 = new Agent("Fred", portal);
        agent1.start();
        MetaAgent agent2 = new Agent("Phill", portal);
        agent2.start();
        MetaAgent agent3 = new Agent("Phill", portal);
        
        try
        {
            portal.put(new SysMessage("Admin", "P1", "reg", agent1));
            portal.put(new SysMessage("Admin", "P1", "reg", agent2));
            portal.put(new SysMessage("Admin", "P1", "reg", agent3));
        } 
        catch (InterruptedException ex)
        {
            Logger.getLogger(Weiss.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        agent1.sendMessage("Phill", "Hello World!");
        agent1.sendMessage("Greg", "Hello World!");
        
        
        
        
        NodeMonitor n = new NodeMonitor();
        n.start();
        try
        {
            n.put(new UserMessage("Fred", "You", "Hello"));
        }
        catch (InterruptedException ex)
        {
            Logger.getLogger(Weiss.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
    }
}
