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


import weiss.core.message.Message;
import weiss.core.message.UserMessage;
import weiss.manager.Client;
import weiss.manager.Managable;

/**
 *
 * @author Adam Young, Teesside University Sch. of Computing
 */
public class Agent extends MetaAgent implements Runnable, Managable
{  
    private Client client;

    public Agent(String name, MetaAgent superAgent, Client client)
    {
        super(name, superAgent);
        
        this.client = client;
    }
    
    public Agent(String name, MetaAgent superAgent)
    {
        super(name, superAgent);
    }

    @Override
    protected void userMsgHandler(UserMessage msg)
    {
        this.updateNodeMonitor(msg);
        this.updateClient(msg);
    }
    
    
    /**
     * Method to add a client that interacts with the MetaAgent. Only one client
     * can be active at any one time.
     * @param client An object of type WeissBase. Current implementation uses
     * {@link weiss.manager.Client Client}.
     */
    

    //--------------------------------------------------------------------------
    //INTERFACE METHODS
    //--------------------------------------------------------------------------
    @Override
    public void addClient(Client client)
    {
       this.client = client;
    }
    @Override
    public void removeClient(Client client)
    {
        this.client = null;
    }
    public void updateClient(Message msg)
    {
        if(client != null)
            client.updateClient(msg);
    }

    
}
