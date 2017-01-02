/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package weiss.agent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import weiss.message.Message;

/**
 *
 * @author Adam Young
 */
public class NodeMonitor extends LinkedBlockingQueue implements Runnable
{
    Thread t;
    Boolean shouldStop;
    JDialog dialog;
    JPanel panel;
    Vector data;
    
    public NodeMonitor()
    {
        shouldStop = false;
        
        createGUI();
        
        t = new Thread(this);
    }
    
    private JDialog createGUI()
    { 
        data = new Vector();
        
        dialog = new JDialog();
        dialog.setTitle("Node Monitor");
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
        
        dialog.repaint();
        
        return dialog;
    }
    
    private void updateTable(Message msg)
    {
        Vector row = new Vector();
        row.add(msg.getFrom());
        row.add(msg.getTo());
        row.add(msg.getTime());
        
        data.add(row);
        panel.repaint();
    }
        
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
                updateTable(msg);
                System.out.println("From: " + msg.getFrom() + "\n" +
                                   "To: " + msg.getTo() + "\n" + 
                                   "Sent: " + msg.getTime() + "\n");
            } 
            catch (InterruptedException ex)
            {
                Logger.getLogger(NodeMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
