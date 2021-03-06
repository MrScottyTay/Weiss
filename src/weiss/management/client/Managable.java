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
package weiss.management.client;

import weiss.core.message.UserMessage;

/**
 * Interface to implement the hooks for the nodeMonitor class
 *
 * @author Adam Young, Teesside University Sch. of Computing
 */
public interface Managable
{

    /**
     * Method to add a client to the implementing class.
     *
     * @param client A client object.
     */
    public void addClient(Client client);

    /**
     * Method to remove a client from the implementing class.
     */
    public void removeClient();

    /**
     * Method to check if a client has been registered to the implementing
     * class.
     *
     * @return True if the client is present, false if not.
     */
    public boolean hasClient();
    
    public void updateClient(UserMessage msg);
}
