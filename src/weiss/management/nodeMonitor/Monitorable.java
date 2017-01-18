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
package weiss.management.nodeMonitor;

/**
 * Interface to implement the required hooks for the Client class
 *
 * @author Adam Young, Teesside University Sch. of Computing
 */
public interface Monitorable
{

    /**
     * Method to add a node monitor to the implementing class.
     *
     * @param nodeMonitor A nodeMonitor object.
     */
    public void addNodeMonitor(NodeMonitor nodeMonitor);

    /**
     * Method to remove a node monitor from a implementing class.
     */
    public void removeNodeMonitor();

    /**
     * Method to check if a node monitor has been registered to the implementing
     * class.
     *
     * @return True if the node monitor is present, false if not.
     */
    public boolean hasNodeMonitor();
}
