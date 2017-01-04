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
;
import java.util.concurrent.LinkedBlockingQueue;
import weiss.core.message.Message;
import weiss.core.message.SysMessage;
import weiss.core.message.UserMessage;

/**
 *
 * @author Adam Young, Teesside University Sch. of Computing
 */
public abstract class WeissBase extends LinkedBlockingQueue implements Runnable
{
    private final Thread t;
    private Boolean shouldStop;
    private String name;
    
    public WeissBase(String name)
    {
        shouldStop = false;
        this.name = name;
        
        t = new Thread(this);
    }
    
    /**
     * Getter for name variable.
     * 
     * @return String of name.
     */
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name; 
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
                System.out.println("Error taking message");
            }
        }
    }
    
}
