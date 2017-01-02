/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiss.MetaAgent;

import Weiss.Manager.NodeMonitor;

/**
 *
 * @author Adam Young
 */
public interface Monitorable
{
    public void addNodeMonitor(NodeMonitor nodeMonitor);
    
    public void removeNodeMonitor(NodeMonitor nodeMonitor);
    
    public boolean hasMonitor();
    
    public void addClient(NodeMonitor client);
    
    public void removeClient(NodeMonitor client);
    
    public boolean hasClient();
}
