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

import weiss.MetaAgent.Agent;
import weiss.MetaAgent.Router;
import weiss.MetaAgent.Portal;
import weiss.MetaAgent.MetaAgent;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Adam Young, Teesside University Sch. of Computing
 */
public class WeissManager extends JFrame
{

    private TreeNode nodeSelected;
    private static JButton metaAgentSelectBtn;
    private JTextField metaAgentInputField;
    private TreePane treePane;
    private JTree tree;

    public WeissManager()
    {
        treePane = new TreePane();
        tree = treePane.getTree();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(1000, 600));
        this.add(buildPanel());
        this.setTitle("Weiss Manager");

        this.setVisible(true);
    }

    public JPanel buildPanel()
    {
        metaAgentInputField = new JTextField(10);

        JPanel panel = new JPanel(new BorderLayout());
        JPanel leftPane = new JPanel(new BorderLayout());
        JPanel leftTopPane = new JPanel();
        JPanel leftBottomPane = new JPanel();
        JPanel centerPanel = new JPanel();

        metaAgentSelectBtn = new JButton("Select an Item");
        metaAgentSelectBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {

                TreeNode treeNode = (TreeNode) tree.getSelectionPath().getLastPathComponent();
                MetaAgent treeSelection = treeNode.getAgentRef();

                if (!metaAgentInputField.getText().isEmpty())
                {
                    switch (treeNode.getLevel())
                    {
                        case 0:
                            treePane.addNode(new Router(metaAgentInputField.getText(), null),
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
                        case 3:
                            NodeMonitor agentView = new AgentView();
                            agentView.createGUI(treeSelection);
                            treeSelection.addClient(agentView);
                            agentView.start();

                            break;
                        default:
                            break;
                    }
                }
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

                NodeMonitor node = new NodeMonitor();
                
                node.createGUI(treeSelection);
                treeSelection.addNodeMonitor(node);
                node.start();
                
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

        return panel;
    }

    public static JButton getAgentSelectBtn()
    {
        return metaAgentSelectBtn;
    }

}
