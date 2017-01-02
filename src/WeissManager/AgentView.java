/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WeissManager;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import weiss.agent.MetaAgent;

/**
 *
 * @author Adam Young
 */
public class AgentView
{
    private static JFrame frame;
    public AgentView()
    {

        frame.setMinimumSize(new Dimension(400,400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
    public static void showWindow(MetaAgent agent)
    {
        JDialog dialog = new JDialog();
        dialog.setTitle("Agent Window - " + agent.getName());
        dialog.setMinimumSize(new Dimension(500,500));
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
        
        JFrame frame = new JFrame();
        JPanel mainPanel = new JPanel(new BorderLayout());
        JPanel bottomPanel = new JPanel();
        
        JComboBox agentSelection = new JComboBox();
        JTextArea textArea = new JTextArea(5, 30);
        JTextField messageField = new JTextField(30);
        JButton sendButton = new JButton("Send");
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.add(textArea);
        
        bottomPanel.add(messageField, BorderLayout.WEST);
        bottomPanel.add(sendButton, BorderLayout.EAST);
        
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        mainPanel.add(agentSelection, BorderLayout.NORTH);
        
        dialog.getContentPane().add(mainPanel);
    }
}
