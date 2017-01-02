/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiss.MetaAgent;

import Weiss.Manager.NodeMonitor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import weiss.message.Message;
import weiss.message.SysMessage;
import weiss.message.UserMessage;

/**
 *
 * @author Adam Young
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
