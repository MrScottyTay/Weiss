/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weiss.Manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import weiss.MetaAgent.MetaAgent;
import weiss.MetaAgent.WeissBase;
import weiss.message.Message;
import weiss.message.SysMessage;
import weiss.message.UserMessage;

/**
 *
 * @author Adam Young
 */
public class NodeMonitor extends WeissBase implements Runnable
{
    Thread t;
    Boolean shouldStop;
    JDialog dialog;
    JPanel panel;
    Vector data;
    
    public NodeMonitor()
    {
        shouldStop = false;
        t = new Thread(this);
    }
    
    public void createGUI(MetaAgent agent)
    { 
        data = new Vector();
        
        dialog = new JDialog();
        dialog.setTitle("Node Monitor - " + agent.getName());
        dialog.setMinimumSize(new Dimension(200,200));
        dialog.setPreferredSize(new Dimension(400,400));
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        
        panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());
        
        Vector columnNames = new Vector();
        columnNames.add("From");
        columnNames.add("To");
        columnNames.add("Sent");

        JTable table = new JTable(data, columnNames); 
        
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        dialog.add(panel);
        dialog.pack();
        
        dialog.setVisible(true);
        
    }  
    
    @Override
    protected void sysMsgHandler(SysMessage msg)
    {
        Vector row = new Vector();
        row.add(msg.getFrom());
        row.add(msg.getTo());
        row.add(msg.getTime());
        
        data.add(row);
        panel.repaint();
    }

    @Override
    protected void userMsgHandler(UserMessage usrMsg)
    {
        System.out.println("Invalid target");
    }
}
