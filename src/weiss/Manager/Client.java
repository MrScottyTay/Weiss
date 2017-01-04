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
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import weiss.core.agent.MetaAgent;
import weiss.core.message.*;

/**
 *
 * @author Adam Young, Teesside University Sch. of Computing
 */
public class Client extends NodeMonitor implements Runnable
{
    private JTextArea textArea;
    private MetaAgent agent;
            
    public Client(String name, MetaAgent agent)
    {
        super(name);
        
        this.agent = agent;
        
    }
    @Override
    protected void createGUI(String name)
    {
        JDialog dialog = new JDialog();
        
        dialog.setTitle("Agent Window - " + name);
        dialog.setMinimumSize(new Dimension(500,500));
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel bottomPanel = new JPanel();
        
        //JComboBox agentSelection = new JComboBox();
        JTextField agentSelection = new JTextField();
        textArea = new JTextArea(5, 30);
        
        textArea.setEditable(false);
        JTextField messageField = new JTextField(30);
        
        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                /*
                if(agentSelection.getSelectedItem().toString().equals(null))
                    agent.sendMessage(agentSelection.getSelectedItem().toString(),
                        messageField.getText());
                */
                if(agentSelection.getText() != null)
                    agent.sendMessage(agentSelection.getText(),
                        messageField.getText());
            }  
        });
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(textArea);
        
        bottomPanel.add(messageField, BorderLayout.WEST);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(agentSelection, BorderLayout.NORTH);
        
        dialog.getContentPane().add(mainPanel);
        dialog.getRootPane().setDefaultButton(sendButton);
        
        dialog.pack();
    }
    @Override
    protected void sysMsgHandler(SysMessage msg)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void userMsgHandler(UserMessage usrMsg)
    {
        textArea.append(usrMsg.toString());
    }
}
