package WeissManager;

import java.awt.BorderLayout;
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
import javax.swing.JTree;
import weiss.agent.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Adam Young
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
                            treePane.addNode(new Router(metaAgentInputField.getText(), null));
                            break;
                        case 1:
                            treePane.addNode(new Portal(metaAgentInputField.getText(), null));
                            break;
                        case 2:
                            treePane.addNode(new Agent(metaAgentInputField.getText(), null));
                            break;
                        case 3:
                            AgentView.showWindow(treeSelection);
                            break;
                        default:
                            break;
                    }
                }
            }
        });

        leftTopPane.add(metaAgentSelectBtn);
        leftTopPane.add(metaAgentInputField);

        leftPane.add(tree, BorderLayout.CENTER);
        leftPane.add(leftTopPane, BorderLayout.NORTH);

        panel.add(leftPane, BorderLayout.WEST);
        panel.add(centerPanel, BorderLayout.CENTER);

        return panel;
    }
    public static JButton getAgentSelectBtn()
    {
        return metaAgentSelectBtn;
    }

}