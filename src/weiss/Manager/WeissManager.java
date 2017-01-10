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

import weiss.core.message.NodeMonitor;
import weiss.core.agent.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
/**
 *
 * @author Adam Young, Teesside University Sch. of Computing
 */
public final class WeissManager extends JFrame
{
    private JButton metaAgentSelectBtn;
    private JTextField metaAgentInputField;
    private final ImageIcon icon;
    
    
    
    public WeissManager()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(buildPanel());
        this.setTitle("Weiss");
        this.setSize(new Dimension(255,500));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        
        icon = new ImageIcon("Images/weiss.png");
        this.setIconImage(icon.getImage()); 
        
        this.setVisible(true); 
    }

    public JPanel buildPanel()
    {
        TreePane treePane = new TreePane(this);
        JTree tree = treePane.getTree();
        
        metaAgentInputField = new JTextField(10);
        
        JPanel panel = new JPanel(new BorderLayout());
        JPanel leftPane = new JPanel(new BorderLayout());
        JPanel leftTopPane = new JPanel(new FlowLayout());
        JPanel leftBottomPane = new JPanel();
        JPanel centerPanel = new JPanel();

        metaAgentSelectBtn = new JButton("Select an Item");
        metaAgentSelectBtn.setPreferredSize(new Dimension(120, 20));
        metaAgentSelectBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                TreeNode treeNode = (TreeNode) tree.getSelectionPath().getLastPathComponent();

                if (!metaAgentInputField.getText().isEmpty() && 
                        !metaAgentSelectBtn.getText().equalsIgnoreCase("View Agent"))
                {
                    switch (treeNode.getLevel())
                    {
                        case 0:
                            treePane.addNode(new Router(metaAgentInputField.getText()),
                                    new ImageIcon("Images/router20px.png"));
                            break;
                        case 1:
                            treePane.addNode(new Portal(metaAgentInputField.getText(), null),
                                    new ImageIcon("Images/portal20px.png"));
                            break;
                        case 2:
                            treePane.addNode(new Agent(metaAgentInputField.getText(), null),
                                    new ImageIcon("Images/agent20px.png"));
                            break;
                        default:
                            break;
                    }
                }
                else if(metaAgentSelectBtn.getText().equalsIgnoreCase("View Agent"))
                {
                        Agent treeSelection = (Agent) treeNode.getAgentRef();
                        treeSelection.addClient(new Client(treeSelection));
                }
                metaAgentInputField.setText("");
            }
        });

        JButton metaAgentNodeMonitorBtn = new JButton("Add Node Monitor");
        metaAgentNodeMonitorBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                TreeNode treeNode = (TreeNode) tree.getSelectionPath().getLastPathComponent();
                MetaAgent treeSelection = treeNode.getAgentRef();

                treeSelection.addNodeMonitor(new NodeMonitor(treeSelection));
            }
        });
        leftBottomPane.add(metaAgentNodeMonitorBtn);

        leftTopPane.add(metaAgentSelectBtn);
        leftTopPane.add(metaAgentInputField);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(tree);
        
        leftPane.add(scrollPane, BorderLayout.CENTER);
        leftPane.add(leftTopPane, BorderLayout.NORTH);
        leftPane.add(leftBottomPane, BorderLayout.SOUTH);

        panel.add(leftPane, BorderLayout.WEST);
        panel.add(centerPanel, BorderLayout.CENTER);

        this.getRootPane().setDefaultButton(metaAgentSelectBtn);
        this.pack();
        
        return panel;
    }

    public JButton getAgentSelectBtn()
    {
        return metaAgentSelectBtn;
    }

}
