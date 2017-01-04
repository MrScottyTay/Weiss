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
package Weiss.Manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import weiss.Message.Message;
import weiss.MetaAgent.MetaAgent;
import weiss.MetaAgent.WeissBase;
import weiss.Message.SysMessage;
import weiss.Message.UserMessage;

/**
 *
 * @author Adam Young, Teesside University Sch. of Computing
 */
public class NodeMonitor extends WeissBase implements Runnable
{
    Thread t;
    Boolean shouldStop;
    JDialog dialog;
    Vector data;
    JTable table; 
    DefaultTableModel tableModel;
    
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
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        panel.setLayout(new BorderLayout());
        
        Vector columnNames = new Vector();
        columnNames.add("From");
        columnNames.add("To");
        columnNames.add("Sent");
        columnNames.add("MsgType");

        table = new JTable(data, columnNames);
        tableModel = (DefaultTableModel) table.getModel();
        
        panel.add(new JScrollPane(table), BorderLayout.CENTER);
        dialog.add(panel);
        dialog.pack();
        
        dialog.setVisible(true);
        
    } 
    
    private Vector insertTableData(Message msg)
    {
        Vector row = new Vector();
        row.add(msg.getFrom());
        row.add(msg.getTo());
        row.add(msg.getTime());
        
        return row;
    }
    
    @Override
    protected void sysMsgHandler(SysMessage msg)
    {
        Vector row = insertTableData(msg);
        row.add("SysMessage");
        
        data.add(row);
        tableModel.fireTableDataChanged();
        dialog.revalidate();
    }

    @Override
    protected void userMsgHandler(UserMessage msg)
    {
        Vector row = insertTableData(msg);
        row.add("UserMessage");
        
        data.add(row);
        tableModel.fireTableDataChanged();
        dialog.revalidate();
    }
}
