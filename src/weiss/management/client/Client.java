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
package weiss.management.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import weiss.core.agent.Agent;
import weiss.core.message.*;
import weiss.management.AgentImpl;

/**
 * A simple, standalone GUI client to hook into the client of the MetaAgents.
 *
 * @author Adam Young, Teesside University Sch. of Computing
 */
public final class Client
{

    private JTextArea textArea;
    private final AgentImpl agent;
    private Thread thread;

    /**
     * Constructor to create a Client GUI
     *
     * @param agent The agent the client is being assigned to.
     */
    public Client(AgentImpl agent)
    {
        this.agent = agent;
        this.createGUI(agent.getName());
    }

    /**
     * Method to create the GUI window, using a JDialog.
     *
     * @param name String containing the MetaAgent name.
     */
    private void createGUI(String name)
    {
        JDialog dialog = new JDialog();

        dialog.setTitle("Agent Window - " + name);
        dialog.setMinimumSize(new Dimension(500, 200));
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);

        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel bottomPanel = new JPanel();

        JTextField agentSelection = new JTextField();
        textArea = new JTextArea(5, 30);

        textArea.setEditable(false);
        JTextField messageField = new JTextField(30);

        JButton sendButton = new JButton("Send");
        sendButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if (agentSelection.getText() != null)
                {
                    agent.sendMessage(agentSelection.getText(),
                            messageField.getText());
                }
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

    /**
     * Method to update the client window text area.
     *
     * @param usrMsg The inputted message.
     */
    public void updateClient(UserMessage usrMsg)
    {
        textArea.append(usrMsg.toString());
    }
}
