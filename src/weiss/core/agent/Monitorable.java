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
package weiss.core.agent;

import weiss.manager.NodeMonitor;

/**
 *
 * @author Adam Young, Teesside University Sch. of Computing
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
