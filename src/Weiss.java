
import java.util.logging.Level;
import java.util.logging.Logger;
import weiss.agent.NodeMonitor;
import weiss.message.UserMessage;

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
        
        
        NodeMonitor n = new NodeMonitor();
        n.start();
        try
        {
            n.put(new UserMessage("Fred", "You", "Hello"));
        } catch (InterruptedException ex)
        {
            Logger.getLogger(Weiss.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
