/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Weiss.Manager;

import weiss.MetaAgent.WeissBase;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import weiss.MetaAgent.MetaAgent;
import weiss.message.Message;
import weiss.message.SysMessage;
import weiss.message.UserMessage;

/**
 *
 * @author Adam Young
 */
public class AgentView extends NodeMonitor implements Runnable
{
    private JTextArea textArea;
    private JDialog dialog;
    JPanel mainPanel;
            
    public AgentView()
    {
        super();
    }
    public void createGUI(MetaAgent agent)
    {
        dialog = new JDialog();
        dialog.setTitle("Agent Window - " + agent.getName());
        dialog.setMinimumSize(new Dimension(500,500));
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        
        mainPanel = new JPanel(new BorderLayout());
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
                if(agentSelection.getText().equals(null))
                    System.out.println("sent message");
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
        mainPanel.setBackground(Color.red);
        textArea.append("Sent: +" + usrMsg.getTime() +"From: " + usrMsg.getFrom() +
                        "    Message: " + usrMsg.getMsg() + "\n");
    }
}
