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
import weiss.core.agent.NodeMonitor;
import weiss.manager.WeissManager;
import weiss.core.agent.Router;

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
        router.addNodeMonitor(new NodeMonitor(router));
        router.start();
        
        MetaAgent portal = new Portal("P1", router);
        portal.addNodeMonitor(new NodeMonitor(portal));
        portal.start();

        Agent agent1 = new Agent("Fred", portal);
        agent1.addNodeMonitor(new NodeMonitor(agent1));
        agent1.start();
        
        Agent agent2 = new Agent("Bill", portal);
        agent2.addNodeMonitor(new NodeMonitor(agent2));
        agent2.start();
        
        //----------------------------------------------------------------------
        agent1.sendMessage("Bill", "Hello World!");
        */
    }
}
