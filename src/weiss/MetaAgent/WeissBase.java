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
package weiss.MetaAgent;

import Weiss.Manager.NodeMonitor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import weiss.Message.Message;
import weiss.Message.SysMessage;
import weiss.Message.UserMessage;

/**
 *
 * @author Adam Young, Teesside University Sch. of Computing
 */
public abstract class WeissBase extends LinkedBlockingQueue implements Runnable
{
    Thread t;
    Boolean shouldStop;
    public WeissBase()
    {
        shouldStop = false;
        
        t = new Thread(this);
    }
    
    protected void msgHandler(Message msg)
    {
        if(msg instanceof SysMessage)
            sysMsgHandler((SysMessage) msg);
        else if(msg instanceof UserMessage)
            userMsgHandler((UserMessage) msg);
    }
    
    protected abstract void sysMsgHandler(SysMessage msg);
    
    protected abstract void userMsgHandler(UserMessage usrMsg);

    public void start()
    {
        t.start();
    }
    
    public void stop()
    {
        shouldStop = true;
    }
    
    @Override
    public void run()
    {
        while(!shouldStop)
        {
            try
            {
                Message msg = (Message) this.take();
                msgHandler(msg);
            } 
            catch (InterruptedException ex)
            {
                Logger.getLogger(NodeMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
