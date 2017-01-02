
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
