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
import weiss.core.agent.Agent;
import weiss.core.agent.Portal;
import weiss.core.agent.MetaAgent;
import weiss.manager.NodeMonitor;
import weiss.manager.WeissManager;
import java.util.logging.*;
import weiss.core.agent.Router;
import weiss.core.message.SysMessage;

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
        //A demonstration of the middleware in action. One version uses a custom
        //GUI to present the information, the other utilises the CLI.
        
        

        //----------------------------------------------------------------------
        //MANAGER OPERATION

        WeissManager manager = new WeissManager();
                
        /*
        //----------------------------------------------------------------------
        //CLI OPERATION
        MetaAgent router = new Router("R1", null);
        router.start();
        
        MetaAgent portal = new Portal("P1", router);
        portal.start();
        
        NodeMonitor monitor3 = new NodeMonitor(portal.getName());
        portal.addNodeMonitor(monitor3);
        monitor3.start();

        MetaAgent agent1 = new Agent("Fred", portal);
        agent1.start();
        
        MetaAgent agent2 = new Agent("Bill", portal);
        agent2.start();
        
        NodeMonitor monitor1 = new NodeMonitor(agent1.getName());
        agent1.addNodeMonitor(monitor1);
        monitor1.start();
                
        NodeMonitor monitor2 = new NodeMonitor(agent2.getName());
        agent2.addNodeMonitor(monitor2);
        monitor2.start();
        //----------------------------------------------------------------------
        agent1.sendMessage("Bill", "Hello World!");
        */
    }
}
