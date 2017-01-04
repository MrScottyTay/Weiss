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
package weiss.manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.Vector;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import weiss.core.message.Message;
import weiss.core.*;
import weiss.core.agent.MetaAgent;

/**
 *
 * @author Adam Young, Teesside University Sch. of Computing
 */
public class NodeMonitor
{
    private JDialog dialog;
    private Vector data;
    private JTable table; 
    private DefaultTableModel tableModel;
    
    public NodeMonitor(MetaAgent agent)
    {
        this.createGUI(agent.getName());
    }
    
    protected void createGUI(String name)
    { 
        data = new Vector();
        
        dialog = new JDialog();
        dialog.setTitle("Node Monitor - " + name);
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
    
    public void insertTableData(Message msg)
    {
        Vector row = new Vector();
        row.add(msg.getFrom());
        row.add(msg.getTo());
        row.add(msg.getTime());
        
        //Need to add logic for message type
        
        data.add(row);
        tableModel.fireTableDataChanged();
    }
}
